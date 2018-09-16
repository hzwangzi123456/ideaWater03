<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Emp jsp</title>
  </head>
  
  <body>
	  <form action="">
		  <p>id <input type="text" name="id" /></p>
		  <p>name<input type="text" name="name" /></p>
		  <p>salary<input type="text" name="salary" /></p>
		  <p>age<input type="text" name="age" /></p>
		  <button id="button" type="button">提交</button>
	  </form>
	<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsUtil/PublicBefore.js"></script>
	<script>
		$(function(){
			
			$("#button").click(function(){
				console.log("点击了button");
				var $arr = $("input");
// 				for(var i = 0;i < $arr.length;i ++) {
// 					console.log($arr[i].val());
// 				}	
				doAjax('<%=basePath%>EmpController/insertEmp.do',
// 					{id:11,name:"wzzz",salary:1231,age:43},
					{id:$($arr[0]).val(),name:$($arr[1]).val(),salary:$($arr[2]).val(),age:$($arr[3]).val()},
					function(data){
// 					console.log(data);
// 					console.log(typeof data);	
						var redata = strToJson(data);
						if(redata.isSucced == false) {
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
