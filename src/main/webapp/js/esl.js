/**
 * ESL (Enterprise Standard Loader)
 * Copyright 2013 Baidu Inc. All rights reserved.
 * 
 * @file Browserç«¯æ åå è½½å¨ï¼ç¬¦åAMDè§è
 * @author errorrik(errorrik@gmail.com)
 *         Firede(firede@firede.us)
 */

var define;
var require;

(function(global) {
	// "mod"å¼å¤´çåéæå½æ°ä¸ºåé¨æ¨¡åç®¡çå½æ°
	// ä¸ºæé«åç¼©çï¼ä¸ä½¿ç¨functionæobjectåè£

	/**
	 * æ¨¡åå®¹å¨
	 * 
	 * @inner
	 * @type {Object}
	 */
	var modModules = {};

	var MODULE_STATE_PRE_DEFINED = 1;
	var MODULE_STATE_PRE_ANALYZED = 2;
	var MODULE_STATE_ANALYZED = 3;
	var MODULE_STATE_READY = 4;
	var MODULE_STATE_DEFINED = 5;

	/**
	 * å¨å±requireå½æ°
	 * 
	 * @inner
	 * @type {Function}
	 */
	var actualGlobalRequire = createLocalRequire('');

	/**
	 * è¶æ¶æéå®æ¶å¨
	 * 
	 * @inner
	 * @type {number}
	 */
	var waitTimeout;

	/**
	 * å è½½æ¨¡å
	 * 
	 * @param {string|Array} requireId æ¨¡åidææ¨¡åidæ°ç»ï¼
	 * @param {Function=} callback å è½½å®æçåè°å½æ°
	 * @return {*}
	 */
	function require(requireId, callback) {
		assertNotContainRelativeId(requireId);

		// è¶æ¶æé
		var timeout = requireConf.waitSeconds;
		if(isArray(requireId) && timeout) {
			if(waitTimeout) {
				clearTimeout(waitTimeout);
			}
			waitTimeout = setTimeout(waitTimeoutNotice, timeout * 1000);
		}

		return actualGlobalRequire(requireId, callback);
	}

	/**
	 * å°æ¨¡åæ è¯è½¬æ¢æç¸å¯¹çurl
	 * 
	 * @param {string} id æ¨¡åæ è¯
	 * @return {string}
	 */
	require.toUrl = toUrl;

	/**
	 * è¶æ¶æéå½æ°
	 * 
	 * @inner
	 */
	function waitTimeoutNotice() {
		var hangModules = [];
		var missModules = [];
		var missModulesMap = {};
		var hasError;

		for(var id in modModules) {
			if(!modIsDefined(id)) {
				hangModules.push(id);
				hasError = 1;
			}

			each(
				modModules[id].realDeps || [],
				function(depId) {
					if(!modModules[depId] && !missModulesMap[depId]) {
						hasError = 1;
						missModules.push(depId);
						missModulesMap[depId] = 1;
					}
				}
			);
		}

		if(hasError) {
			throw new Error('[MODULE_TIMEOUT]Hang( ' +
				(hangModules.join(', ') || 'none') +
				' ) Miss( ' +
				(missModules.join(', ') || 'none') +
				' )'
			);
		}
	}

	/**
	 * å°è¯å®ææ¨¡åå®ä¹çå®æ¶å¨
	 * 
	 * @inner
	 * @type {number}
	 */
	var tryDefineTimeout;

	/**
	 * å®ä¹æ¨¡å
	 * 
	 * @param {string=} id æ¨¡åæ è¯
	 * @param {Array=} dependencies ä¾èµæ¨¡ååè¡¨
	 * @param {Function=} factory åå»ºæ¨¡åçå·¥åæ¹æ³
	 */
	function define() {
		var argsLen = arguments.length;
		if(!argsLen) {
			return;
		}

		var id;
		var dependencies;
		var factory = arguments[--argsLen];

		while(argsLen--) {
			var arg = arguments[argsLen];

			if(isString(arg)) {
				id = arg;
			} else if(isArray(arg)) {
				dependencies = arg;
			}
		}

		// åºç°windowä¸æ¯çå¿½
		// eslè®¾è®¡æ¯åä¸ºbrowserç«¯çloader
		// é­åçglobalæ´å¤æä¹å¨äºï¼
		//     defineårequireæ¹æ³å¯ä»¥è¢«æå°ç¨æ·èªå®ä¹å¯¹è±¡ä¸­
		var opera = window.opera;

		// IEä¸éè¿current scriptçdata-require-idè·åå½åid
		if(!id &&
			document.attachEvent &&
			(!(opera && opera.toString() === '[object Opera]'))
		) {
			var currentScript = getCurrentScript();
			id = currentScript && currentScript.getAttribute('data-require-id');
		}

		// å¤çä¾èµå£°æ
		// é»è®¤ä¸º['require', 'exports', 'module']
		dependencies = dependencies || ['require', 'exports', 'module'];
		if(id) {
			modPreDefine(id, dependencies, factory);

			// å¨ä¸è¿çæªæ¥å°è¯å®ædefine
			// defineå¯è½æ¯å¨é¡µé¢ä¸­æä¸ªå°æ¹è°ç¨ï¼ä¸ä¸å®æ¯å¨ç¬ç«çæä»¶è¢«requireè£è½½
			if(tryDefineTimeout) {
				clearTimeout(tryDefineTimeout);
			}
			tryDefineTimeout = setTimeout(modPreAnalyse, 10);
		} else {
			// çºªå½å°å±äº«åéä¸­ï¼å¨loadæreadystatechangeä¸­å¤ç
			wait4PreDefines.push({
				deps: dependencies,
				factory: factory
			});
		}
	}

	define.amd = {};

	/**
	 * è·åç¸åºç¶æçæ¨¡ååè¡¨
	 * 
	 * @inner
	 * @param {number} state ç¶æç 
	 * @return {Array}
	 */
	function modGetByState(state) {
		var modules = [];
		for(var key in modModules) {
			var module = modModules[key];
			if(module.state == state) {
				modules.push(module);
			}
		}

		return modules;
	}

	/**
	 * æ¨¡åéç½®è·åå½æ°
	 * 
	 * @inner
	 * @return {Object} æ¨¡åéç½®å¯¹è±¡
	 */
	function moduleConfigGetter() {
		var conf = requireConf.config[this.id];
		if(conf && typeof conf === 'object') {
			return conf;
		}

		return {};
	}

	/**
	 * é¢å®ä¹æ¨¡å
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @param {Array.<string>} dependencies æ¾å¼å£°æçä¾èµæ¨¡ååè¡¨
	 * @param {*} factory æ¨¡åå®ä¹å½æ°ææ¨¡åå¯¹è±¡
	 */
	function modPreDefine(id, dependencies, factory) {
		if(modExists(id)) {
			return;
		}

		var module = {
			id: id,
			deps: dependencies,
			factory: factory,
			exports: {},
			config: moduleConfigGetter,
			state: MODULE_STATE_PRE_DEFINED,
			hardDeps: {}
		};

		// å°æ¨¡åé¢å­å¥definingéåä¸­
		modModules[id] = module;
	}

	/**
	 * é¢åææ¨¡å
	 * 
	 * é¦åï¼å®æå¯¹factoryä¸­å£°æä¾èµçåææå
	 * ç¶åï¼å°è¯å è½½"èµæºå è½½æéæ¨¡å"
	 * 
	 * éè¦åå è½½æ¨¡åçåå æ¯ï¼å¦ææ¨¡åä¸å­å¨ï¼æ æ³è¿è¡resourceId normalizeå
	 * modAnalyseå®æåç»­çä¾èµåæå¤çï¼å¹¶è¿è¡ä¾èµæ¨¡åçå è½½
	 * 
	 * @inner
	 * @param {Object} modules æ¨¡åå¯¹è±¡
	 */
	function modPreAnalyse() {
		var pluginModuleIds = [];
		var pluginModuleIdsMap = {};
		var modules = modGetByState(MODULE_STATE_PRE_DEFINED);

		each(
			modules,
			function(module) {
				// å¤çå®ééè¦å è½½çä¾èµ
				var realDepends = module.deps.slice(0);
				module.realDeps = realDepends;

				// åæfunction bodyä¸­çrequire
				// å¦æåå«æ¾å¼ä¾èµå£°æï¼ä¸ºæ§è½èèï¼å¯ä»¥ä¸åæfactoryBody
				// AMDè§èçè¯´ææ¯`SHOULD NOT`ï¼æä»¥è¿éè¿æ¯åæäº
				var factory = module.factory;
				var requireRule = /require\(\s*(['"'])([^'"]+)\1\s*\)/g;
				var commentRule = /(\/\*([\s\S]*?)\*\/|([^:]|^)\/\/(.*)$)/mg;
				if(isFunction(factory)) {
					factory.toString()
						.replace(commentRule, '')
						.replace(requireRule, function($0, $1, $2) {
							realDepends.push($2);
						});
				}

				// åæresourceå è½½çplugin module id
				each(
					realDepends,
					function(dependId) {
						var idInfo = parseId(dependId);
						if(idInfo.resource) {
							var plugId = normalize(idInfo.module, module.id);
							if(!pluginModuleIdsMap[plugId]) {
								pluginModuleIds.push(plugId);
								pluginModuleIdsMap[plugId] = 1;
							}
						}
					}
				);

				module.state = MODULE_STATE_PRE_ANALYZED;
			}
		);

		nativeRequire(pluginModuleIds, function() {
			modAnalyse(modules);
		});
	}

	/**
	 * åææ¨¡å
	 * å¯¹ææä¾èµidè¿è¡normalizeåï¼å®æåæï¼å¹¶å°è¯å è½½å¶ä¾èµçæ¨¡å
	 * 
	 * @inner
	 * @param {Array} modules æ¨¡åå¯¹è±¡åè¡¨
	 */
	function modAnalyse(modules) {
		var requireModules = [];

		each(
			modules,
			function(module) {
				if(module.state !== MODULE_STATE_PRE_ANALYZED) {
					return;
				}

				var id = module.id;

				// å¯¹åæ°ä¸­å£°æçä¾èµè¿è¡normalize
				var depends = module.deps;
				var hardDepends = module.hardDeps;
				var hardDependsCount = isFunction(module.factory) ?
					module.factory.length :
					0;

				each(
					depends,
					function(dependId, index) {
						dependId = normalize(dependId, id);
						depends[index] = dependId;

						if(index < hardDependsCount) {
							hardDepends[dependId] = 1;
						}
					}
				);

				// ä¾èµæ¨¡åid normalizeåï¼å¹¶å»é¤å¿è¦çä¾èµãå»é¤çä¾èµæ¨¡åæï¼
				// 1. åé¨æ¨¡åï¼require/exports/module
				// 2. éå¤æ¨¡åï¼dependenciesåæ°ååé¨requireå¯è½éå¤
				// 3. ç©ºæ¨¡åï¼dependenciesä¸­ä½¿ç¨èå¯è½åç©º
				var realDepends = module.realDeps;
				var len = realDepends.length;
				var existsDepend = {};

				while(len--) {
					// æ­¤å¤åä¸é¨åå¾ªç¯å­å¨éå¤normalizeï¼å ä¸ºdepsårealDepsæ¯éå¤ç
					// ä¸ºä¿æé»è¾åçæ¸æ°ï¼å°±ä¸åä¼åäºå
					var dependId = normalize(realDepends[len], id);
					if(!dependId ||
						dependId in existsDepend ||
						dependId in BUILDIN_MODULE
					) {
						realDepends.splice(len, 1);
					} else {
						existsDepend[dependId] = 1;
						realDepends[len] = dependId;

						// å°å®éä¾èµåå¥å è½½åºåä¸­ï¼åç»­ç»ä¸è¿è¡require
						requireModules.push(dependId);
					}
				}

				module.realDepsIndex = existsDepend;
				module.state = MODULE_STATE_ANALYZED;

				modWaitDependenciesLoaded(module);
				modInvokeFactoryDependOn(id);
			}
		);

		nativeRequire(requireModules);
	}

	/**
	 * ç­å¾æ¨¡åä¾èµå è½½å®æ
	 * å è½½å®æåå°è¯è°ç¨factoryå®ææ¨¡åå®ä¹
	 * 
	 * @inner
	 * @param {Object} module æ¨¡åå¯¹è±¡
	 */
	function modWaitDependenciesLoaded(module) {
		var id = module.id;

		module.invokeFactory = invokeFactory;
		invokeFactory();

		// ç¨äºé¿åæ­»ä¾èµé¾çæ­»å¾ªç¯å°è¯
		var checkingLevel = 0;

		/**
		 * å¤æ­ä¾èµå è½½å®æ
		 * 
		 * @inner
		 * @return {boolean}
		 */
		function checkInvokeReadyState() {
			checkingLevel++;

			var isReady = 1;
			var tryDeps = [];

			each(
				module.realDeps,
				function(depId) {
					if(!modIsAnalyzed(depId)) {
						isReady = 0;
					} else if(!modIsDefined(depId)) {
						switch(modHasCircularDependency(id, depId)) {
							case CIRCULAR_DEP_UNREADY:
							case CIRCULAR_DEP_NO:
								isReady = 0;
								break;
							case CIRCULAR_DEP_YES:
								if(module.hardDeps[depId]) {
									tryDeps.push(depId);
								}
								break;
						}
					}

					return !!isReady;
				}
			);

			// åªæå½å¶ä»éå¾ªç¯ä¾èµé½è£è½½äºï¼æå»å°è¯è§¦åç¡¬ä¾èµæ¨¡åçåå§å
			isReady && each(
				tryDeps,
				function(depId) {
					modTryInvokeFactory(depId);
				}
			);

			isReady = isReady && tryDeps.length === 0;
			isReady && (module.state = MODULE_STATE_READY);

			checkingLevel--;
			return isReady;
		}

		/**
		 * åå§åæ¨¡å
		 * 
		 * @inner
		 */
		function invokeFactory() {
			if(module.state == MODULE_STATE_DEFINED ||
				checkingLevel > 1 ||
				!checkInvokeReadyState()
			) {
				return;
			}

			// è°ç¨factoryå½æ°åå§åmodule
			try {
				var factory = module.factory;
				var exports = isFunction(factory) ?
					factory.apply(
						global,
						modGetModulesExports(
							module.deps, {
								require: createLocalRequire(id),
								exports: module.exports,
								module: module
							}
						)
					) :
					factory;

				if(typeof exports != 'undefined') {
					module.exports = exports;
				}

				module.state = MODULE_STATE_DEFINED;
				module.invokeFactory = null;
			} catch(ex) {
				if(/^\[MODULE_MISS\]"([^"]+)/.test(ex.message)) {
					// åºéè¯´æå¨factoryçè¿è¡ä¸­ï¼è¯¥requireçæ¨¡åæ¯éè¦ç
					// æä»¥æå®å å¥ç¡¬ä¾èµä¸­
					module.hardDeps[RegExp.$1] = 1;
					return;
				}

				throw ex;
			}

			modInvokeFactoryDependOn(id);
			modFireDefined(id);
		}
	}

	/**
	 * æ ¹æ®æ¨¡åidæ°ç»ï¼è·åå¶çexportsæ°ç»
	 * ç¨äºæ¨¡ååå§åçfactoryåæ°ærequireçcallbackåæ°çæ
	 * 
	 * @inner
	 * @param {Array} modules æ¨¡åidæ°ç»
	 * @param {Object} buildinModules åå»ºæ¨¡åå¯¹è±¡
	 * @return {Array}
	 */
	function modGetModulesExports(modules, buildinModules) {
		var args = [];
		each(
			modules,
			function(moduleId, index) {
				args[index] =
					buildinModules[moduleId] ||
					modGetModuleExports(moduleId);
			}
		);

		return args;
	}

	var CIRCULAR_DEP_UNREADY = 0;
	var CIRCULAR_DEP_NO = 1;
	var CIRCULAR_DEP_YES = 2;

	/**
	 * å¤æ­sourceæ¯å¦å¤äºtargetçä¾èµé¾ä¸­
	 *
	 * @inner
	 * @return {number}
	 */
	function modHasCircularDependency(source, target, meet) {
		if(!modIsAnalyzed(target)) {
			return CIRCULAR_DEP_UNREADY;
		}

		meet = meet || {};
		meet[target] = 1;

		if(target == source) {
			return CIRCULAR_DEP_YES;
		}

		var module = modGetModule(target);
		var depends = module && module.realDeps;

		if(depends) {
			var len = depends.length;

			while(len--) {
				var dependId = depends[len];
				if(meet[dependId]) {
					continue;
				}

				var state = modHasCircularDependency(source, dependId, meet);
				switch(state) {
					case CIRCULAR_DEP_UNREADY:
					case CIRCULAR_DEP_YES:
						return state;
				}
			}
		}

		return CIRCULAR_DEP_NO;
	}

	/**
	 * è®©ä¾èµèªå·±çæ¨¡åå°è¯åå§å
	 * 
	 * @inner
	 * @param {string} id æ¨¡åid
	 */
	function modInvokeFactoryDependOn(id) {
		for(var key in modModules) {
			var realDeps = modModules[key].realDepsIndex || {};
			realDeps[id] && modTryInvokeFactory(key);
		}
	}

	/**
	 * å°è¯æ§è¡æ¨¡åfactoryå½æ°ï¼è¿è¡æ¨¡ååå§å
	 * 
	 * @inner
	 * @param {string} id æ¨¡åid
	 */
	function modTryInvokeFactory(id) {
		var module = modModules[id];

		if(module && module.invokeFactory) {
			module.invokeFactory();
		}
	}

	/**
	 * æ¨¡åå®ä¹å®æçäºä»¶çå¬å¨
	 * 
	 * @inner
	 * @type {Array}
	 */
	var modDefinedListener = [];

	/**
	 * æ¨¡åå®ä¹å®æäºä»¶çå¬å¨çç§»é¤ç´¢å¼
	 * 
	 * @inner
	 * @type {Array}
	 */
	var modRemoveListenerIndex = [];

	/**
	 * æ¨¡åå®ä¹å®æäºä»¶fireå±çº§
	 * 
	 * @inner
	 * @type {number}
	 */
	var modFireLevel = 0;

	/**
	 * æ´¾åæ¨¡åå®ä¹å®æäºä»¶
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 */
	function modFireDefined(id) {
		modFireLevel++;
		each(
			modDefinedListener,
			function(listener) {
				listener && listener(id);
			}
		);
		modFireLevel--;

		modSweepDefinedListener();
	}

	/**
	 * æ¸çæ¨¡åå®ä¹å®æäºä»¶çå¬å¨
	 * modRemoveDefinedListeneræ¶åªåæ è®°
	 * å¨modFireDefinedæ§è¡æ¸é¤å¨ä½
	 * 
	 * @inner
	 * @param {Function} listener æ¨¡åå®ä¹çå¬å¨
	 */
	function modSweepDefinedListener() {
		if(modFireLevel < 1) {
			modRemoveListenerIndex.sort(
				function(a, b) {
					return b - a;
				}
			);

			each(
				modRemoveListenerIndex,
				function(index) {
					modDefinedListener.splice(index, 1);
				}
			);

			modRemoveListenerIndex = [];
		}
	}

	/**
	 * ç§»é¤æ¨¡åå®ä¹çå¬å¨
	 * 
	 * @inner
	 * @param {Function} listener æ¨¡åå®ä¹çå¬å¨
	 */
	function modRemoveDefinedListener(listener) {
		each(
			modDefinedListener,
			function(item, index) {
				if(listener == item) {
					modRemoveListenerIndex.push(index);
				}
			}
		);
	}

	/**
	 * æ·»å æ¨¡åå®ä¹çå¬å¨
	 * 
	 * @inner
	 * @param {Function} listener æ¨¡åå®ä¹çå¬å¨
	 */
	function modAddDefinedListener(listener) {
		modDefinedListener.push(listener);
	}

	/**
	 * å¤æ­æ¨¡åæ¯å¦å­å¨
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @return {boolean}
	 */
	function modExists(id) {
		return id in modModules;
	}

	/**
	 * å¤æ­æ¨¡åæ¯å¦å·²å®ä¹å®æ
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @return {boolean}
	 */
	function modIsDefined(id) {
		return modExists(id) &&
			modModules[id].state == MODULE_STATE_DEFINED;
	}

	/**
	 * å¤æ­æ¨¡åæ¯å¦å·²åæå®æ
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @return {boolean}
	 */
	function modIsAnalyzed(id) {
		return modExists(id) &&
			modModules[id].state >= MODULE_STATE_ANALYZED;
	}

	/**
	 * è·åæ¨¡åçexports
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @return {*}
	 */
	function modGetModuleExports(id) {
		if(modIsDefined(id)) {
			return modModules[id].exports;
		}

		return null;
	}

	/**
	 * è·åæ¨¡å
	 * 
	 * @inner
	 * @param {string} id æ¨¡åæ è¯
	 * @return {Object}
	 */
	function modGetModule(id) {
		return modModules[id];
	}

	/**
	 * æ·»å èµæº
	 * 
	 * @inner
	 * @param {string} resourceId èµæºæ è¯
	 * @param {*} value èµæºå¯¹è±¡
	 */
	function modAddResource(resourceId, value) {
		modModules[resourceId] = {
			exports: value || true,
			state: MODULE_STATE_DEFINED
		};

		modInvokeFactoryDependOn(resourceId);
		modFireDefined(resourceId);
	}

	/**
	 * åå»ºmoduleåç§°éå
	 * 
	 * @inner
	 * @type {Object}
	 */
	var BUILDIN_MODULE = {
		require: require,
		exports: 1,
		module: 1
	};

	/**
	 * æªé¢å®ä¹çæ¨¡åéå
	 * ä¸»è¦å­å¨å¿åæ¹å¼defineçæ¨¡å
	 * 
	 * @inner
	 * @type {Array}
	 */
	var wait4PreDefines = [];

	/**
	 * å®ææ¨¡åé¢å®ä¹
	 * 
	 * @inner
	 */
	function completePreDefine(currentId) {
		var preDefines = wait4PreDefines.slice(0);

		wait4PreDefines.length = 0;
		wait4PreDefines = [];

		// é¢å®ä¹æ¨¡åï¼
		// æ­¤æ¶å¤ççæ¨¡åé½æ¯å¿ådefineçæ¨¡å
		each(
			preDefines,
			function(module) {
				var id = module.id || currentId;
				modPreDefine(id, module.deps, module.factory);
			}
		);

		modPreAnalyse();
	}

	/**
	 * è·åæ¨¡å
	 * 
	 * @param {string|Array} ids æ¨¡ååç§°ææ¨¡ååç§°åè¡¨
	 * @param {Function=} callback è·åæ¨¡åå®ææ¶çåè°å½æ°
	 * @return {Object}
	 */
	function nativeRequire(ids, callback, baseId) {
		callback = callback || new Function();
		baseId = baseId || '';

		// æ ¹æ® https://github.com/amdjs/amdjs-api/wiki/require
		// It MUST throw an error if the module has not 
		// already been loaded and evaluated.
		if(isString(ids)) {
			if(!modIsDefined(ids)) {
				throw new Error('[MODULE_MISS]"' + ids + '" is not exists!');
			}

			return modGetModuleExports(ids);
		}

		if(!isArray(ids)) {
			return;
		}

		if(ids.length === 0) {
			callback();
			return;
		}

		var isCallbackCalled = 0;
		modAddDefinedListener(tryFinishRequire);
		each(
			ids,
			function(id) {
				if(id in BUILDIN_MODULE) {
					return;
				}

				(id.indexOf('!') > 0 ?
					loadResource :
					loadModule
				)(id, baseId);
			}
		);

		tryFinishRequire();

		/**
		 * å°è¯å®ærequireï¼è°ç¨callback
		 * å¨æ¨¡åä¸å¶ä¾èµæ¨¡åé½å è½½å®æ¶è°ç¨
		 * 
		 * @inner
		 */
		function tryFinishRequire() {
			if(isCallbackCalled) {
				return;
			}

			var visitedModule = {};

			/**
			 * å¤æ­æ¯å¦æææ¨¡åé½å·²ç»å è½½å®æï¼åæ¬å¶ä¾èµçæ¨¡å
			 * 
			 * @inner
			 * @param {Array} modules ç´æ¥æ¨¡åæ è¯åè¡¨
			 * @return {boolean}
			 */
			function isAllInited(modules) {
				var allInited = 1;
				each(
					modules,
					function(id) {
						if(visitedModule[id]) {
							return;
						}
						visitedModule[id] = 1;

						if(BUILDIN_MODULE[id]) {
							return;
						}

						if(!modIsDefined(id) ||
							!isAllInited(modGetModule(id).realDeps)
						) {
							allInited = 0;
							return false;
						}
					}
				);

				return allInited;
			}

			// æ£æµå¹¶è°ç¨callback
			if(isAllInited(ids)) {
				isCallbackCalled = 1;
				modRemoveDefinedListener(tryFinishRequire);

				callback.apply(
					global,
					modGetModulesExports(ids, BUILDIN_MODULE)
				);
			}
		}
	}

	/**
	 * æ­£å¨å è½½çæ¨¡ååè¡¨
	 * 
	 * @inner
	 * @type {Object}
	 */
	var loadingModules = {};

	/**
	 * å è½½æ¨¡å
	 * 
	 * @inner
	 * @param {string} moduleId æ¨¡åæ è¯
	 */
	function loadModule(moduleId) {
		if(loadingModules[moduleId]) {
			return;
		}

		if(modExists(moduleId)) {
			modAnalyse([modGetModule(moduleId)]);
			return;
		}

		loadingModules[moduleId] = 1;

		// åå»ºscriptæ ç­¾
		// 
		// è¿éä¸ææ¥onerrorçéè¯¯å¤ç
		// å ä¸ºé«çº§æµè§å¨å¨devtoolçconsoleé¢æ¿ä¼æ¥é
		// åthrowä¸ä¸ªErrorå¤æ­¤ä¸ä¸¾äº
		var script = document.createElement('script');
		script.setAttribute('data-require-id', moduleId);
		script.src = toUrl(moduleId);
		script.async = true;
		if(script.readyState) {
			script.onreadystatechange = loadedListener;
		} else {
			script.onload = loadedListener;
		}
		appendScript(script);

		/**
		 * scriptæ ç­¾å è½½å®æçäºä»¶å¤çå½æ°
		 * 
		 * @inner
		 */
		function loadedListener() {
			var readyState = script.readyState;
			if(
				typeof readyState == 'undefined' ||
				/^(loaded|complete)$/.test(readyState)
			) {
				script.onload = script.onreadystatechange = null;
				script = null;

				completePreDefine(moduleId);
				delete loadingModules[moduleId];
			}
		}
	}

	/**
	 * å è½½èµæº
	 * 
	 * @inner
	 * @param {string} pluginAndResource æä»¶ä¸èµæºæ è¯
	 * @param {string} baseId å½åç¯å¢çæ¨¡åæ è¯
	 */
	function loadResource(pluginAndResource, baseId) {
		var idInfo = parseId(pluginAndResource);
		var pluginId = idInfo.module;
		var resourceId = idInfo.resource;

		/**
		 * pluginå è½½å®æçåè°å½æ°
		 * 
		 * @inner
		 * @param {*} value resourceçå¼
		 */
		function pluginOnload(value) {
			modAddResource(pluginAndResource, value);
		}

		/**
		 * è¯¥æ¹æ³åè®¸pluginä½¿ç¨å è½½çèµæºå£°ææ¨¡å
		 * 
		 * @param {string} name æ¨¡åid
		 * @param {string} body æ¨¡åå£°æå­ç¬¦ä¸²
		 */
		pluginOnload.fromText = function(id, text) {
			new Function(text)();
			completePreDefine(id);
		};

		/**
		 * å è½½èµæº
		 * 
		 * @inner
		 * @param {Object} plugin ç¨äºå è½½èµæºçæä»¶æ¨¡å
		 */
		function load(plugin) {
			if(!modIsDefined(pluginAndResource)) {
				plugin.load(
					resourceId,
					createLocalRequire(baseId),
					pluginOnload,
					moduleConfigGetter.call({
						id: pluginAndResource
					})
				);
			}
		}

		if(!modIsDefined(pluginId)) {
			nativeRequire([pluginId], load);
		} else {
			load(modGetModuleExports(pluginId));
		}
	}

	/**
	 * requireéç½®
	 * 
	 * @inner
	 * @type {Object}
	 */
	var requireConf = {
		baseUrl: './',
		paths: {},
		config: {},
		map: {},
		packages: [],
		waitSeconds: 0,
		urlArgs: {}
	};

	/**
	 * æ··åå½åéç½®é¡¹ä¸ç¨æ·ä¼ å¥çéç½®é¡¹
	 * 
	 * @inner
	 * @param {string} name éç½®é¡¹åç§°
	 * @param {Any} value ç¨æ·ä¼ å¥éç½®é¡¹çå¼
	 */
	function mixConfig(name, value) {
		var originValue = requireConf[name];
		var type = typeof originValue;
		if(type == 'string' || type == 'number') {
			requireConf[name] = value;
		} else if(isArray(originValue)) {
			each(value, function(item) {
				originValue.push(item);
			});
		} else {
			for(var key in value) {
				originValue[key] = value[key];
			}
		}
	}

	/**
	 * éç½®require
	 * 
	 * @param {Object} conf éç½®å¯¹è±¡
	 */
	require.config = function(conf) {
		// ç®åçå¤å¤éç½®è¿æ¯éè¦æ¯æ
		// æä»¥å®ç°æ´æ¹ä¸ºäºçº§mix
		for(var key in requireConf) {
			if(conf.hasOwnProperty(key)) {
				var confItem = conf[key];
				if(key == 'urlArgs' && isString(confItem)) {
					defaultUrlArgs = confItem;
				} else {
					mixConfig(key, confItem);
				}
			}
		}

		createConfIndex();
	};

	// åå§åæ¶éè¦åå»ºéç½®ç´¢å¼
	createConfIndex();

	/**
	 * åå»ºéç½®ä¿¡æ¯åé¨ç´¢å¼
	 * 
	 * @inner
	 */
	function createConfIndex() {
		requireConf.baseUrl = requireConf.baseUrl.replace(/\/$/, '') + '/';
		createPathsIndex();
		createMappingIdIndex();
		createPackagesIndex();
		createUrlArgsIndex();
	}

	/**
	 * packagesåé¨ç´¢å¼
	 * 
	 * @inner
	 * @type {Array}
	 */
	var packagesIndex;

	/**
	 * åå»ºpackagesåé¨ç´¢å¼
	 * 
	 * @inner
	 */
	function createPackagesIndex() {
		packagesIndex = [];
		each(
			requireConf.packages,
			function(packageConf) {
				var pkg = packageConf;
				if(isString(packageConf)) {
					pkg = {
						name: packageConf.split('/')[0],
						location: packageConf,
						main: 'main'
					};
				}

				pkg.location = pkg.location || pkg.name;
				pkg.main = (pkg.main || 'main').replace(/\.js$/i, '');
				packagesIndex.push(pkg);
			}
		);

		packagesIndex.sort(createDescSorter('name'));
	}

	/**
	 * pathsåé¨ç´¢å¼
	 * 
	 * @inner
	 * @type {Array}
	 */
	var pathsIndex;

	/**
	 * åå»ºpathsåé¨ç´¢å¼
	 * 
	 * @inner
	 */
	function createPathsIndex() {
		pathsIndex = kv2List(requireConf.paths);
		pathsIndex.sort(createDescSorter());
	}

	/**
	 * é»è®¤çurlArgs
	 * 
	 * @inner
	 * @type {string}
	 */
	var defaultUrlArgs;

	/**
	 * urlArgsåé¨ç´¢å¼
	 * 
	 * @inner
	 * @type {Array}
	 */
	var urlArgsIndex;

	/**
	 * åå»ºurlArgsåé¨ç´¢å¼
	 * 
	 * @inner
	 */
	function createUrlArgsIndex() {
		urlArgsIndex = kv2List(requireConf.urlArgs);
		urlArgsIndex.sort(createDescSorter());
	}

	/**
	 * mappingåé¨ç´¢å¼
	 * 
	 * @inner
	 * @type {Array}
	 */
	var mappingIdIndex;

	/**
	 * åå»ºmappingåé¨ç´¢å¼
	 * 
	 * @inner
	 */
	function createMappingIdIndex() {
		mappingIdIndex = [];

		mappingIdIndex = kv2List(requireConf.map);
		mappingIdIndex.sort(createDescSorter());

		each(
			mappingIdIndex,
			function(item) {
				var key = item.k;
				item.v = kv2List(item.v);
				item.v.sort(createDescSorter());
				item.reg = key == '*' ?
					/^/ :
					createPrefixRegexp(key);
			}
		);
	}

	/**
	 * å°`æ¨¡åæ è¯+'.extension'`å½¢å¼çå­ç¬¦ä¸²è½¬æ¢æç¸å¯¹çurl
	 * 
	 * @inner
	 * @param {string} source æºå­ç¬¦ä¸²
	 * @return {string}
	 */
	function toUrl(source) {
		// åç¦» æ¨¡åæ è¯ å .extension
		var extReg = /(\.[a-z0-9]+)$/i;
		var queryReg = /(\?[^#]*)$/i;
		var extname = '.js';
		var id = source;
		var query = '';

		if(queryReg.test(source)) {
			query = RegExp.$1;
			source = source.replace(queryReg, '');
		}

		if(extReg.test(source)) {
			extname = RegExp.$1;
			id = source.replace(extReg, '');
		}

		// æ¨¡åæ è¯åæ³æ§æ£æµ
		if(!MODULE_ID_REG.test(id)) {
			return source;
		}

		var url = id;

		// pathså¤çåå¹é
		var isPathMap;
		each(pathsIndex, function(item) {
			var key = item.k;
			if(createPrefixRegexp(key).test(id)) {
				url = url.replace(key, item.v);
				isPathMap = 1;
				return false;
			}
		});

		// packageså¤çåå¹é
		if(!isPathMap) {
			each(
				packagesIndex,
				function(packageConf) {
					var name = packageConf.name;
					if(createPrefixRegexp(name).test(id)) {
						url = url.replace(name, packageConf.location);
						return false;
					}
				}
			);
		}

		// ç¸å¯¹è·¯å¾æ¶ï¼éå baseUrl
		if(!/^([a-z]{2,10}:\/)?\//i.test(url)) {
			url = requireConf.baseUrl + url;
		}

		// éå  .extension å query
		url += extname + query;

		var isUrlArgsAppended;

		/**
		 * ä¸ºurléå urlArgs
		 * 
		 * @inner
		 * @param {string} args urlArgsä¸²
		 */
		function appendUrlArgs(args) {
			if(!isUrlArgsAppended) {
				url += (url.indexOf('?') > 0 ? '&' : '?') + args;
				isUrlArgsAppended = 1;
			}
		}

		// urlArgså¤çåå¹é
		each(urlArgsIndex, function(item) {
			if(createPrefixRegexp(item.k).test(id)) {
				appendUrlArgs(item.v);
				return false;
			}
		});
		defaultUrlArgs && appendUrlArgs(defaultUrlArgs);

		return url;
	}

	/**
	 * åå»ºlocal requireå½æ°
	 * 
	 * @inner
	 * @param {number} baseId å½åmodule id
	 * @return {Function}
	 */
	function createLocalRequire(baseId) {
		var requiredCache = {};

		function req(requireId, callback) {
			if(isString(requireId)) {
				var requiredModule;
				if(!(requiredModule = requiredCache[requireId])) {
					requiredModule = nativeRequire(
						normalize(requireId, baseId),
						callback,
						baseId
					);
					requiredCache[requireId] = requiredModule;
				}

				return requiredModule;
			} else if(isArray(requireId)) {
				// åææ¯å¦æresourceä½¿ç¨çpluginæ²¡å è½½
				var unloadedPluginModules = [];
				each(
					requireId,
					function(id) {
						var idInfo = parseId(id);
						var pluginId = normalize(idInfo.module, baseId);
						if(idInfo.resource && !modIsDefined(pluginId)) {
							unloadedPluginModules.push(pluginId);
						}
					}
				);

				// å è½½æ¨¡å
				nativeRequire(
					unloadedPluginModules,
					function() {
						var ids = [];
						each(
							requireId,
							function(id) {
								ids.push(normalize(id, baseId));
							}
						);
						nativeRequire(ids, callback, baseId);
					},
					baseId
				);
			}
		}

		/**
		 * å°[module ID] + '.extension'æ ¼å¼çå­ç¬¦ä¸²è½¬æ¢æurl
		 * 
		 * @inner
		 * @param {string} source ç¬¦åæè¿°æ ¼å¼çæºå­ç¬¦ä¸²
		 * @return {string} 
		 */
		req.toUrl = function(id) {
			return toUrl(normalize(id, baseId));
		};

		return req;
	}

	/**
	 * id normalizeå
	 * 
	 * @inner
	 * @param {string} id éè¦normalizeçæ¨¡åæ è¯
	 * @param {string} baseId å½åç¯å¢çæ¨¡åæ è¯
	 * @return {string}
	 */
	function normalize(id, baseId) {
		if(!id) {
			return '';
		}

		var idInfo = parseId(id);
		if(!idInfo) {
			return id;
		}

		var resourceId = idInfo.resource;
		var moduleId = relative2absolute(idInfo.module, baseId);

		each(
			packagesIndex,
			function(packageConf) {
				var name = packageConf.name;
				var main = name + '/' + packageConf.main;
				if(name == moduleId) {
					moduleId = moduleId.replace(name, main);
					return false;
				}
			}
		);

		moduleId = mappingId(moduleId, baseId);

		if(resourceId) {
			var module = modGetModuleExports(moduleId);
			resourceId = module && module.normalize ?
				module.normalize(
					resourceId,
					function(resId) {
						return normalize(resId, baseId);
					}
				) :
				normalize(resourceId, baseId);

			return moduleId + '!' + resourceId;
		}

		return moduleId;
	}

	/**
	 * ç¸å¯¹idè½¬æ¢æç»å¯¹id
	 * 
	 * @inner
	 * @param {string} id è¦è½¬æ¢çid
	 * @param {string} baseId å½åæå¨ç¯å¢id
	 * @return {string}
	 */
	function relative2absolute(id, baseId) {
		if(/^\.{1,2}/.test(id)) {
			var basePath = baseId.split('/');
			var namePath = id.split('/');
			var baseLen = basePath.length - 1;
			var nameLen = namePath.length;
			var cutBaseTerms = 0;
			var cutNameTerms = 0;

			pathLoop: for(var i = 0; i < nameLen; i++) {
				var term = namePath[i];
				switch(term) {
					case '..':
						if(cutBaseTerms < baseLen) {
							cutBaseTerms++;
							cutNameTerms++;
						} else {
							break pathLoop;
						}
						break;
					case '.':
						cutNameTerms++;
						break;
					default:
						break pathLoop;
				}
			}

			basePath.length = baseLen - cutBaseTerms;
			namePath = namePath.slice(cutNameTerms);

			basePath.push.apply(basePath, namePath);
			return basePath.join('/');
		}

		return id;
	}

	/**
	 * ç¡®å®requireçæ¨¡åidä¸åå«ç¸å¯¹idãç¨äºglobal requireï¼æåé¢é²é¾ä»¥è·è¸ªçéè¯¯åºç°
	 * 
	 * @inner
	 * @param {string|Array} requireId requireçæ¨¡åid
	 */
	function assertNotContainRelativeId(requireId) {
		var invalidIds = [];

		/**
		 * çæµæ¨¡åidæ¯å¦relative id
		 * 
		 * @inner
		 * @param {string} id æ¨¡åid
		 */
		function monitor(id) {
			if(/^\.{1,2}/.test(id)) {
				invalidIds.push(id);
			}
		}

		if(isString(requireId)) {
			monitor(requireId);
		} else {
			each(
				requireId,
				function(id) {
					monitor(id);
				}
			);
		}

		// åå«ç¸å¯¹idæ¶ï¼ç´æ¥æåºéè¯¯
		if(invalidIds.length > 0) {
			throw new Error(
				'[REQUIRE_FATAL]Relative ID is not allowed in global require: ' +
				invalidIds.join(', ')
			);
		}
	}

	/**
	 * æ¨¡åidæ­£å
	 * 
	 * @const
	 * @inner
	 * @type {RegExp}
	 */
	var MODULE_ID_REG = /^[-_a-z0-9\.]+(\/[-_a-z0-9\.]+)*$/i;

	/**
	 * è§£æidï¼è¿åå¸¦æmoduleåresourceå±æ§çObject
	 * 
	 * @inner
	 * @param {string} id æ è¯
	 * @return {Object}
	 */
	function parseId(id) {
		var segs = id.split('!');

		if(MODULE_ID_REG.test(segs[0])) {
			return {
				module: segs[0],
				resource: segs[1] || ''
			};
		}

		return null;
	}

	/**
	 * åºäºmapéç½®é¡¹çidæ å°
	 * 
	 * @inner
	 * @param {string} id æ¨¡åid
	 * @param {string} baseId å½åç¯å¢çæ¨¡åid
	 * @return {string}
	 */
	function mappingId(id, baseId) {
		each(
			mappingIdIndex,
			function(item) {
				if(item.reg.test(baseId)) {

					each(item.v, function(mapData) {
						var key = mapData.k;
						var rule = createPrefixRegexp(key);

						if(rule.test(id)) {
							id = id.replace(key, mapData.v);
							return false;
						}
					});

					return false;
				}
			}
		);

		return id;
	}

	/**
	 * å°å¯¹è±¡æ°æ®è½¬æ¢ææ°ç»ï¼æ°ç»æ¯é¡¹æ¯å¸¦ækåvçObject
	 * 
	 * @inner
	 * @param {Object} source å¯¹è±¡æ°æ®
	 * @return {Array.<Object>}
	 */
	function kv2List(source) {
		var list = [];
		for(var key in source) {
			if(source.hasOwnProperty(key)) {
				list.push({
					k: key,
					v: source[key]
				});
			}
		}

		return list;
	}

	// æè°¢requirejsï¼éè¿currentlyAddingScriptå¼å®¹èæ§ie
	// 
	// For some cache cases in IE 6-8, the script executes before the end
	// of the appendChild execution, so to tie an anonymous define
	// call to the module name (which is stored on the node), hold on
	// to a reference to this node, but clear after the DOM insertion.
	var currentlyAddingScript;
	var interactiveScript;

	/**
	 * è·åå½åscriptæ ç­¾
	 * ç¨äºieä¸defineæªæå®module idæ¶è·åid
	 * 
	 * @inner
	 * @return {HTMLDocument}
	 */
	function getCurrentScript() {
		if(currentlyAddingScript) {
			return currentlyAddingScript;
		} else if(
			interactiveScript &&
			interactiveScript.readyState == 'interactive'
		) {
			return interactiveScript;
		} else {
			var scripts = document.getElementsByTagName('script');
			var scriptLen = scripts.length;
			while(scriptLen--) {
				var script = scripts[scriptLen];
				if(script.readyState == 'interactive') {
					interactiveScript = script;
					return script;
				}
			}
		}
	}

	/**
	 * åé¡µé¢ä¸­æå¥scriptæ ç­¾
	 * 
	 * @inner
	 * @param {HTMLScriptElement} script scriptæ ç­¾
	 */
	function appendScript(script) {
		currentlyAddingScript = script;

		var doc = document;
		(doc.getElementsByTagName('head')[0] || doc.body).appendChild(script);

		currentlyAddingScript = null;
	}

	/**
	 * åå»ºidåç¼å¹éçæ­£åå¯¹è±¡
	 * 
	 * @inner
	 * @param {string} prefix idåç¼
	 * @return {RegExp}
	 */
	function createPrefixRegexp(prefix) {
		return new RegExp('^' + prefix + '(/|$)');
	}

	/**
	 * å¤æ­å¯¹è±¡æ¯å¦æ°ç»ç±»å
	 * 
	 * @inner
	 * @param {*} obj è¦å¤æ­çå¯¹è±¡
	 * @return {boolean}
	 */
	function isArray(obj) {
		return obj instanceof Array;
	}

	/**
	 * å¤æ­å¯¹è±¡æ¯å¦å½æ°ç±»å
	 * 
	 * @inner
	 * @param {*} obj è¦å¤æ­çå¯¹è±¡
	 * @return {boolean}
	 */
	function isFunction(obj) {
		return typeof obj == 'function';
	}

	/**
	 * å¤æ­æ¯å¦å­ç¬¦ä¸²
	 * 
	 * @inner
	 * @param {*} obj è¦å¤æ­çå¯¹è±¡
	 * @return {boolean}
	 */
	function isString(obj) {
		return typeof obj == 'string';
	}

	/**
	 * å¾ªç¯éåæ°ç»éå
	 * 
	 * @inner
	 * @param {Array} source æ°ç»æº
	 * @param {function(Array,Number):boolean} iterator éåå½æ°
	 */
	function each(source, iterator) {
		if(isArray(source)) {
			for(var i = 0, len = source.length; i < len; i++) {
				if(iterator(source[i], i) === false) {
					break;
				}
			}
		}
	}

	/**
	 * åå»ºæ°ç»å­ç¬¦æ°éåºæåºå½æ°
	 * 
	 * @inner
	 * @param {string} property æ°ç»é¡¹å¯¹è±¡å
	 * @return {Function}
	 */
	function createDescSorter(property) {
		property = property || 'k';

		return function(a, b) {
			var aValue = a[property];
			var bValue = b[property];

			if(bValue == '*') {
				return -1;
			}

			if(aValue == '*') {
				return 1;
			}

			return bValue.length - aValue.length;
		};
	}

	// æ´é²å¨å±å¯¹è±¡
	global.define = define;
	global.require = require;
})(this);