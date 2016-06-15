<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>高德地图-功能概要</title>
   
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
			zoom:13,
            center: [119.3,26.08],
            keyboardEnable:false,
            mapStyle:"normal"
		 });
		
		//点标记的创建
		var marker1 = new AMap.Marker({
        	position: [119.3,26.08],
            map:map
        });
		
		//点标记的创建
		var marker2 = new AMap.Marker({
        	position: [119.342,26.052],
            map:map
        });
		
		//一、覆盖物   Circle圆   Polygon多边形    Polyline折线 
		
		//Circle 圆
		var circle1 = new AMap.Circle({
	        center: [119.3,26.08],
	        radius: 1000,
	        fillOpacity:0.2,
	        strokeWeight:1,
	        map:map
        })
	    
		var info = new AMap.InfoWindow({
            content:"信息窗体<br>这里是三坊七巷",
            offset:new AMap.Pixel(0,-28)
        })
        info.open(map,marker1.getPosition());
		
		var arr=new Array();//经纬度坐标数组 
	    arr.push(new AMap.LngLat("119.336","26.058"));
    	arr.push(new AMap.LngLat("119.348","26.058")); 
		arr.push(new AMap.LngLat("119.348","26.047")); 
   		arr.push(new AMap.LngLat("119.336","26.047")); 
   		//Polygon 多边形
        var polygon=new AMap.Polygon({
            path:arr,    //设置多边形轮廓的节点数组
            strokeColor:"#0000ff", 
            strokeOpacity:0.2, 
            fillOpacity:0.2,
	        strokeWeight:1,
	        map:map
        }); 
		
    	
    	var arr=new Array();//经纬度坐标数组 
	    arr.push(new AMap.LngLat("119.3","26.08"));
    	arr.push(new AMap.LngLat("119.342","26.052")); 
   		//折线  Polyline 
        var polyline=new AMap.Polyline({
            path:arr,    //设置多边形轮廓的节点数组
            strokeColor:"red", 
	        strokeWeight:2,
	        map:map
        }); 
    	
   		//二、图层     实时交通TileLayer.Traffic   卫星 TileLayer.Satellite...
   		/**var defaultLayer = new AMap.TileLayer();
    	var traffic = new AMap.TileLayer.Traffic();
    	var map = new AMap.Map('container',{
        	zoom: 10,
        	layers: [
	            defaultLayer,//默认图层
	            traffic//实时交通图层
       	    ],
       	    center: [119.32,26.06]
    	});*/
    	
    	//三、控件    工具条、比例尺、定位、鹰眼、类别切换等常用的控件
   		
    	AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView'],
        	function(){
           		 map.addControl(new AMap.ToolBar());
           		 map.addControl(new AMap.Scale());
           		 map.addControl(new AMap.OverView({isOpen:true}));
    	});
	  
   		AMap.plugin(["AMap.MapType"],function(){  //添加地图类型切换插件 
	        //地图类型切换  
	        mapType= new AMap.MapType({defaultType:2,showRoad:true});  
	        map.addControl(mapType);  
    	});
   		
   		//服务插件 高德地图为开发者免费提供了各种服务接口，为了方便使用，JSAPI对这些服务接口进行了包装,比如POI搜索、路线规划等很有用的服务
   		//Driving    Transfer  	Walking....
   		//驾车
   		AMap.plugin('AMap.Driving',function(){
      	  var drving = new AMap.Driving({
            map:map
        })
        drving.search([
          {keyword:'福州北站',city:'福州'},
          {keyword:'三坊七巷',city:'福州'}
        ]);
        })
        
        //公交路线查询
	    AMap.plugin(["AMap.LineSearch"], function() {  
	       var lineSearch = new AMap.LineSearch({  
	            pageIndex:1,  
	            city:'福州',  
	            pageSize:1,  
	            extensions:'all'  
	        });  
	        //搜索“128”相关公交线路  
	         lineSearch.search('52'); 
	         alert("开始搜索！！！");
	         AMap.event.addListener(lineSearch, "complete", lineSearch_Callback);  
	         AMap.event.addListener(lineSearch, "error", function(e){alert(e.info);  
	        });  
	    });  
  
        
	</script>

	
	<div id="info"> 
	     <ul id="right_nav" class="right_nav" style="right: 0px; display: block; visibility: visible;">
			<li class="current" title="一、覆盖物">一、覆盖物</li>
			<li title="二、图层">二、图层</li>
			<li title="三、控件">三、控件</li>
			<li title="四、服务插件">四、服务插件</li>
			<li title="五、功能插件">五、功能插件</li>
		</ul>
	</div>
	
</body>
</html>