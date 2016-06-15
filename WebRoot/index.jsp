<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<html>
	<head></head>
	<body style="font-size:30px;">
		当前系统在线人数是:
		<%=application.getAttribute("count")%>
		<br/>
		<a href="logout">退出系统</a>
	</body>
</html>
