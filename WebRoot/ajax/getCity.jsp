<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<html>
	<head>
		<script type="text/javascript" 
		src="js/my.js"></script>
		<script type="text/javascript">
			function getCity(v1){
				var xhr = getXhr();
				xhr.open('get','getCity.do?name=' + v1,true);
				xhr.onreadystatechange=function(){
					if(xhr.readyState == 4){
						var txt = xhr.responseText;
						//txt  石家庄,sjz;唐山,ts;邢台,xt
						var strs = txt.split(';');
						//先清空s2
						$('s2').innerHTML = '';
						for(i=0;i<strs.length;i++){
							var str1s = strs[i].split(',');
							/*
								构造一个Option对象
							*/
							var op = new Option(str1s[0],str1s[1]);
							/*
								options是select的一个属性,
							其值是一个数组。数组中的元素是
							Option对象。
							*/
							$('s2').options[i]  = op;
						}
					}
				};
				xhr.send(null);
			}
		</script>
	</head>
	<body style="font-size:30px;font-style:italic;">
		<select id="s1" style="width:120px;" 
		onchange="getCity(this.value);">
			<option value="bj">北京</option>
			<option value="hb">河北</option>
			<option value="sd">山东</option>
		</select>
		<select id="s2" style="width:120px;">
		</select>
	</body>
</html>