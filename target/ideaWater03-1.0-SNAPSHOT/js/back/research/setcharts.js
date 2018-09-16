option = {//多图表option
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		data: ['PH', '水温', '氨氮', '总磷', '电导率', '溶解氧', '浊度']
	},
	toolbox: {
		show: true,
		feature: {
			mark: {
				show: true
			},
			dataView: {
				show: true,
				readOnly: false
			},
			magicType: {
				show: true,
				type: ['line', 'stack', 'tiled']
			},
			restore: {
				show: true
			},
			saveAsImage: {
				show: true
			}
		}
	},
	calculable: true,
	xAxis: [{
		type: 'category',
		boundaryGap: false,
		data: new Array()
	}],
	yAxis: {
		type: 'value',
		name:'yAxis'
	},
	series: [{
			name: 'PH',
			type: 'line',
			stack: 'ph值',
			data: new Array()
		},
		{
			name: '水温',
			type: 'line',
			stack: 'T(℃)',
			data: new Array()
		},
		{
			name: '氨氮',
			type: 'line',
			stack: 'N(mg/L)',
			data: new Array()
		},
		{
			name: '总磷',
			type: 'line',
			stack: 'P(mg/L)',
			data: new Array()
		},
		{
			name: '电导率',
			type: 'line',
			stack: 'CDT(us/cm)',
			data: new Array()
		},
		{
			name: '溶解氧',
			type: 'line',
			stack: 'DO(mg/L)',
			data: new Array()
		},
		{
			name: '浊度',
			type: 'line',
			stack: 'NTU',
			data: new Array()
		},
	]
};

option02 = { //单图表option
		tooltip: {
			trigger: 'axis'
		},
		legend: {},
		toolbox: {
			show: true,
			feature: {
				mark: {
					show: true
				},
				dataView: {
					show: true,
					readOnly: false
				},
				magicType: {
					show: true,
					type: ['line', 'stack', 'tiled']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		calculable: true,
		xAxis: [{
			type: 'category',
			boundaryGap: false,
			data: new Array()
		}],
		yAxis: {
			type: 'value',
			name:'yAxis'
		},
		series: new Array()
	};

//初始化echarts实例
//var myChart = echarts.init(document.getElementById('placeholder2'));

//使用制定的配置项和数据显示图表
//myChart.setOption(option);