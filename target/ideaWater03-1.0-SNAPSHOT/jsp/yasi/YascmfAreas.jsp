<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>YascmfAreas.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   	  <p>prefix <input type="text" name="id" /></p>
	  <p>province<input type="text" name="name" /></p>
	  <p>city<input type="text" name="salary" /></p>
	  <p>county<input type="text" name="age" /></p>
	  <p>extra<input type="text" name="age" /></p>
	  <p>created_at<input type="text" name="age" /></p>
	  <p>updated_at<input type="text" name="age" /></p>
	  <button id="button" type="button">提交</button>
  
  	<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsUtil/PublicBefore.js"></script>
	<script>
		$(function(){
			var $parr = $("input");
			$("#button").click(function(){
				doAjax("<%=basePath%>/YascmfAreasController/test.do",
				{prefix:$($parr[0]).val(),province:$($parr[1]).val(),city:$($parr[2]).val(),county:$($parr[3]).val(),
				extra:$($parr[4]).val(),createdAt:$($parr[5]).val(),updatedAt:$($parr[6]).val()},
				function(data){
					var redata = strToJson(data);
					if(redata.isSucced != null && redata.isSucced == false ) {
						console.log(redata.msg);//打印失败信息
						return;
					}
					for(var i = 0;i < redata.length;i ++) {
						console.log(redata[i]);
					}
				});
			});
		});
	</script>
  </body>
</html>
