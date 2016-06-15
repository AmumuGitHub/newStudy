<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<html>
	<head>
		<style>
			#d1{
				width:450px;
				height:280px;
				background-color:black;
				margin-left:400px;
				margin-top:50px;
			}
			#d2{
				color:white;
				background-color:red;
				height:35px;
			}
			table{
				color:white;
				font-size:24px;
			}
		</style>
		<script type="text/javascript" src="js/my.js">
		</script>
		<script type="text/javascript" 
		src="js/prototype-1.6.0.3.js"></script>
		<script type="text/javascript">
			function quoto(){
				setInterval(f1,5000);
			}
			function f1(){
				/*
					异步地向服务器发请求，获得
					股票信息(服务器会返回json字符串)。
				*/
				var xhr = getXhr();
				xhr.open('get','quoto.do',true);
				xhr.onreadystatechange=function(){
					if(xhr.readyState == 4){
						var txt = xhr.responseText;
						//将json字符串转换成javascript对象或者数组
						var arr = txt.evalJSON();
						var html = '';
						for(i=0;i < arr.length;i++){
							var s = arr[i];
							html += '<tr><td>' + s.code 
							+ '</td><td>' + s.name 
							+ '</td><td> '+ s.price
							 + '</td></tr>';
						}
						$('tb1').innerHTML = html;
					}
				};
				xhr.send(null);
			}
		</script>
	</head>
	<body style="font-size:30px;font-style:italic;" 
	onload="quoto();">
		<div id="d1">
			<div id="d2">股票实时行情</div>
			<div id="d3">
				<table width="100%" cellpadding="0" 
				cellspacing="0">
					<thead>
						<tr><td>代码</td><td>名称</td><td>价格</td></tr>
					</thead>
					<tbody id="tb1">
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>