<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>高德地图-快速入门</title>
   
	<style type="text/css">
	    .right_nav {
			  display: none;
			  width: 120px;
			  position: fixed;
			  margin: 50px 200px 0px 0px;
			  padding: 0;
			  list-style: none;
			  border-left: 2px solid #d2d2d2;
		}
		.right_nav li{
			    width: 116px; 
			    height: 30px;
			    overflow: hidden; 
			    white-space: nowrap; 
			    text-overflow: ellipsis;
		}
		
		#container {
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			width: 100%;
			height: 100%;
		}
	 </style>
    <!-- 指定容器大小 -->
    <style type="text/css">
    	#container {width:600px; height:600px; margin:auto;}  
    </style>
    <!--引入高德地图JavaScript API文件 -->
    <script src="http://webapi.amap.com/maps?v=1.3&key=ac9d3b6806704109101157fa365fcd6b"></script>
    
</head>
<body>

	 <!-- 创建地图容器 -->
	 <div id="container"></div>  
	 
     <!-- 使用js在容器中填入当前位置 -->
	<script type="text/javascript">
		//设定地图的中心点和级别
		var map = new AMap.Map('container',{
			zoom:10,
            center: [119.3,26.08],
            keyboardEnable:false,
            mapStyle:"normal"
		 });
		
		//点标记的创建和移除
		var marker1 = new AMap.Marker({
        	position: [119.3,26.08],
            map:map
        });
		var marker2 = new AMap.Marker({
        	position: [119.52,25.95],
            map:map
        });
		
		//marker1.setMap();
		
		//信息窗体的创建与设置
		var infowindow1 = new AMap.InfoWindow({
    	 content: '<div><span style="color:blue;">从这里出发?<span></div>',
         offset:new AMap.Pixel(0,0),
         size:new AMap.Size(100,0)
        })
		
		var infowindow2 = new AMap.InfoWindow({
    	 content: '<div><span style="color:blue;">到这里去?<span></div>',
         offset:new AMap.Pixel(0,0),
         size:new AMap.Size(100,0)
        })
		
		//马上打开
		//infowindow1.open(map,new AMap.LngLat(119.3,26.08));
		
		//监听打开
		var clickHandle = AMap.event.addListener(marker2, 'click', function() {
                infowindow2.open(map, marker2.getPosition())
        })
		
        //AMap.event.removeListener(clickHandle);
		
		//使用高级信息窗体
		AMap.plugin('AMap.AdvancedInfoWindow',function(){
  			var advancedInfowindow = new AMap.AdvancedInfoWindow({
   					 content: '<h3 class="title">高德地图</h1>'+
							  '<div class="content">高德是中国领先的数字地图内容、导航和位置服务解决方案提供商。</div>',
    				 offset: new AMap.Pixel(0, -30),
                     asOrigin:false
            });
           advancedInfowindow.open(map,new AMap.LngLat(119.3,26.08));
		})
		
		//在回调函数里我们来进行控件的生成和添加(工具条和比例尺):
		AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
             var toolBar = new AMap.ToolBar();
             var scale = new AMap.Scale();
             map.addControl(toolBar);
             map.addControl(scale);
        })
        
       // map.removeControl(toolBar);
	</script>
	
	<div id="info"> 
	     <ul id="right_nav" class="right_nav" style="right: 0px; display: block; visibility: visible;">
		     <li class="" title="准备页面">准备页面</li>
			 <li title="创建地图" class="">创建地图</li>
			 <li title="添加点标记">添加点标记</li>
			 <li title="添加信息窗体">添加信息窗体</li>
			 <li title="添加工具条和比例尺">添加工具条和比例尺</li>
		</ul>
	</div>
	
</body>
</html>