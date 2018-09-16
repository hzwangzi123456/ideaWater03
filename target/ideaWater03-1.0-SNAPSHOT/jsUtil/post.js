function doAjax(urlStr, jsonData, succFun) { //异步
	$.ajax({
		type: "POST",
		dataType: "text",
		url: urlStr,
		traditional: true,
		data: jsonData,
		success: succFun
	});
}

function doAjaxSyn(urlStr, jsonData, succFun) { //同步
	$.ajax({
		type: "POST",
		dataType: "text",
		url: urlStr,
		async: false,
		traditional: true,
		data: jsonData,
		success: succFun
	});
}