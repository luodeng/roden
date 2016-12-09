<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
 <head>
    <base href="<%=basePath%>">    
    <title>微信自定义菜单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="resources/js/jquery-2.1.4.js"></script>
	<script>
	$(function(){		
		$(".create").click(function(){
			  $.ajax({	
				type: "POST",			
				url: "menu/create",
				data: $("form").serialize(),
				dataType:"json",
				success: function(data){
					console.log(data);
					if(data.status==1){					
						alert(data.msg);
					}else{
						alert(data.msg);
					}
				}
			  })
		});
		$("select[name='name']").change(function(){
			$("input[type='text']").val("");
			if($("select[name=name]").val()==-1)
				return false;
			$.ajax({
				type: "GET",			
				url: "menu/get",
				data: {name:$("select[name=name]").val()},
				dataType:"json",
				success: function(data){
					console.log(data);					
				    if(data.status==1){					    	
				    	var arr=data.data.menu.button;
				    	 for(var i in arr){	 
				    		var type=arr[i].type;
				    		var button=$(".button"+i+" "+".subButton");			    			
				    		$(button).find("input[name=name"+i+"]").val(arr[i].name);
					    	$(button).find("input[name=type"+i+"]").val(arr[i].type);
					    	$(button).find("input[name=key"+i+"]").val(arr[i].key);
					       	$(button).find("input[name=url"+i+"]").val(arr[i].url);
				    		if(type!="view"){
				    			 var subArr=arr[i].sub_button;
					    		 for(var j in subArr){	
					    			var subButton=$(".button"+i+" "+".subButton"+j);				    			
					    			$(subButton).find("input[name=name"+i+j+"]").val(subArr[j].name);
					    			$(subButton).find("input[name=type"+i+j+"]").val(subArr[j].type);
					    			$(subButton).find("input[name=key"+i+j+"]").val(subArr[j].key);
					    			$(subButton).find("input[name=url"+i+j+"]").val(subArr[j].url);
					    		 }
				    		 }	
				    	 }
				    	 $(".subButton>input[name^='url']").change();
				    }else{
						alert(data.msg);
				    }	
				}
			})
		});
		$(".subButton>input[name^='url']").change(function(){
			$(".subButton>input[name^='url']").each(function(i){
				if($(this).val()){
					$(this).parent().siblings().hide();
				}else{
					$(this).parent().siblings().show();
				}
			});	
		});			
	});
	</script>
  </head>
<body>
<form>
  <div class="menu">  
  		<div class="button0" style="margin-left:100px;">
  			<div class="subButton" style="margin-left:100px;">
	  			<input type="text" name="name0"/>
	  			<input type="text" name="type0"/>
	  			<input type="text" name="key0"/>
	  			<input type="text" name="url0" style="width:1000px;"/>
	  		</div>
  			<div class="subButton0" style="margin-left:200px;">
  			 	<input type="text" name="name00"/>
  				<input type="text" name="type00"/>
  				<input type="text" name="key00"/>
  				<input type="text" name="url00" style="width:1000px;"/>
  			</div>
  			<div class="subButton1" style="margin-left:200px;">
  				<input type="text" name="name01"/>
  				<input type="text" name="type01"/>
  				<input type="text" name="key01"/>
  				<input type="text" name="url01" style="width:1000px;"/>
  			</div>
  			<div class="subButton2" style="margin-left:200px;">
  			 	<input type="text" name="name02"/>
  				<input type="text" name="type02"/>
  				<input type="text" name="key02"/>
  				<input type="text" name="url02" style="width:1000px;"/>
  			</div>
  			<div class="subButton3" style="margin-left:200px;">
  			 	<input type="text" name="name03"/>
  				<input type="text" name="type03"/>
  				<input type="text" name="key03"/>
  				<input type="text" name="url03" style="width:1000px;"/>
  			</div>
  			<div class="subButton4" style="margin-left:200px;">
  			 	<input type="text" name="name04"/>
  				<input type="text" name="type04"/>
  				<input type="text" name="key04"/>
  				<input type="text" name="url04" style="width:1000px;"/>
  			</div>  			
  		</div>	  		
  		<div class="button1" style="margin-left:100px;">
  			<div class="subButton" style="margin-left:100px;">
	  			<input type="text" name="name1"/>
	  			<input type="text" name="type1"/>
	  			<input type="text" name="key1"/>
	  			<input type="text" name="url1" style="width:1000px;"/>
	  		</div>
  			<div class="subButton0" style="margin-left:200px;">
  			 	<input type="text" name="name10"/>
  				<input type="text" name="type10"/>
  				<input type="text" name="key10"/>
  				<input type="text" name="url10" style="width:1000px;"/>
  			</div>
  			<div class="subButton1" style="margin-left:200px;">
  				<input type="text" name="name11"/>
  				<input type="text" name="type11"/>
  				<input type="text" name="key11"/>
  				<input type="text" name="url11" style="width:1000px;"/>
  			</div>
  			<div class="subButton2" style="margin-left:200px;">
  			 	<input type="text" name="name12"/>
  				<input type="text" name="type12"/>
  				<input type="text" name="key12"/>
  				<input type="text" name="url12" style="width:1000px;"/>
  			</div>
  			<div class="subButton3" style="margin-left:200px;">
  			 	<input type="text" name="name13"/>
  				<input type="text" name="type13"/>
  				<input type="text" name="key13"/>
  				<input type="text" name="url13" style="width:1000px;"/>
  			</div>
  			<div class="subButton4" style="margin-left:200px;">
  			 	<input type="text" name="name14"/>
  				<input type="text" name="type14"/>
  				<input type="text" name="key14"/>
  				<input type="text" name="url14" style="width:1000px;"/>
  			</div>  			
  		</div>	
  		<div class="button2" style="margin-left:100px;">
  			<div class="subButton" style="margin-left:100px;">
	  			<input type="text" name="name2"/>
	  			<input type="text" name="type2"/>
	  			<input type="text" name="key2"/>
	  			<input type="text" name="url2" style="width:1000px;"/>
	  		</div>
  			<div class="subButton0" style="margin-left:200px;">
  			 	<input type="text" name="name20"/>
  				<input type="text" name="type20"/>
  				<input type="text" name="key20"/>
  				<input type="text" name="url20" style="width:1000px;"/>
  			</div>
  			<div class="subButton1" style="margin-left:200px;">
  				<input type="text" name="name21"/>
  				<input type="text" name="type21"/>
  				<input type="text" name="key21"/>
  				<input type="text" name="url21" style="width:1000px;"/>
  			</div>
  			<div class="subButton2" style="margin-left:200px;">
  			 	<input type="text" name="name22"/>
  				<input type="text" name="type22"/>
  				<input type="text" name="key22"/>
  				<input type="text" name="url22" style="width:1000px;"/>
  			</div>
  			<div class="subButton3" style="margin-left:200px;">
  			 	<input type="text" name="name23"/>
  				<input type="text" name="type23"/>
  				<input type="text" name="key23"/>
  				<input type="text" name="url23" style="width:1000px;"/>
  			</div>
  			<div class="subButton4" style="margin-left:200px;">
  			 	<input type="text" name="name24"/>
  				<input type="text" name="type24"/>
  				<input type="text" name="key24"/>
  				<input type="text" name="url24" style="width:1000px;"/>
  			</div>  			
  		</div>	  		
  </div>
  <select name="name">
  	<option value="-1">请选择</option>
  	<option value="taobao">车和生活-测试</option>
  	<option value="luodeng">车联生活-测试</option>
  	<option value="test">173351191</option>
  	<option value="">-------------分割线---------------</option>
  	<option value="clw">安煋车联网</option>
  	<option value="shop">车和生活</option>
  	<option value="keeper">车联生活</option>
  </select>
  <input type="button" class="create" value="创建菜单"/>
 </form>
</body>
</html>