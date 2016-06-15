<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<html>
	<head>
		<style>
			.s1{
				color:red;
			}
		</style>
		<script type="text/javascript" 
		src="<%=request.getContextPath()%>/js/my.js"></script>
		<script type="text/javascript">
			function check_username(){
				//获得ajax对象
				var xhr = getXhr();
				//发送请求
				var uri = 'check_username.do?username='
				 + $F('username');
				xhr.open('get',encodeURI(uri)	,true);
				 xhr.onreadystatechange=function(){
				 	/*
				 		只有xhr的readyState等于4时，
				 		xhr才获得了服务器返回的所有数据。
				 	*/
				 	if(xhr.readyState == 4){
				 		if(xhr.status == 200){
				 			//正确数据
				 			var txt = xhr.responseText;
				 			$('username_msg').innerHTML = txt;
				 		}else{
				 			//发生了错误
				 			$('username_msg').innerHTML = 
				 			'验证用户名出错';
				 		}
				 	}
				 };
				 $('username_msg').innerHTML = '正在检查...';
				xhr.send(null);
			}
			
			function check_username_post(){
				var xhr = getXhr();
				xhr.open('post','check_username.do',true);
				xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded');
				xhr.onreadystatechange=function(){
					if(xhr.readyState == 4){
						var txt = xhr.responseText;
						$('username_msg').innerHTML = txt;
					}
				};
				xhr.send('username=' + $F('username'));
			}
		</script>
	</head>
	<body style="font-size:30px;font-style:italic;">
		<form action="regist.do" method="post">
			<fieldset>
				<legend>注册</legend>
				用户名:<input id="username" name="username" 
				onblur="check_username_post();"/>
				<span class="s1" id="username_msg"></span>
				<br/>
				密码:<input type="password" 
				name="pwd"/><br/>
				<input type="submit" value="确定"/>
			</fieldset>
		</form>
	</body>
</html>