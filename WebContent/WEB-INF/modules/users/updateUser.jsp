<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script src="<%=basePath%>/resources/js/jquery/jquery-3.5.1.min.js"></script>
<script src="<%=basePath%>/resources/js/common.js"></script>

<script src="<%=basePath%>/resources/js/jquery/jquery.validate/jquery.validate.js"></script>
<script src="<%=basePath%>/resources/js/jquery/jquery.validate/jquery.validate.method.js"></script>



<style type="text/css">
.form-actions{
     padding-right: 1000px;
}
#messageBox{
 color: red;
}
</style>

</head>



<body>



<script type="text/javascript">

// swal("Here's a message!")

//全部角色
var rolelist="";

//初始化
$(function(){
	initUpdate();
})





function initUpdate(){
	var id=getUrlParam("id");
	if(id==null||id==""){
		alert("参数异常，无法修改！");
		return;
	}
	$("#_userId").val(id);
	//请求数据
	$.ajax({
		url : "<%=basePath%>/GetUserInfo",
		type : "get",
		contentType: "application/json; charset=utf-8",
		dataType : "json",
		data :{"id":id},
		success : function(returnData,status,XMLHttpRequest)
		{	
		   if(resolveResultBeanIsError(returnData,status))
		   {
			  var data=returnData.data;
			  $("#_userName").val(data.userName);
			  
			  $("#_email").val(data.email);

			  //状态redio
			  var status=data.status;
			  if(status==0){
				  $("input[name=status]:eq(0)").attr("checked",'checked'); 
			  }else if(status==1)
			  {
				  $("input[name=status]:eq(1)").attr("checked",'checked'); 

			  }
			  
			   
		   }
		},
		error : function() {
			alert("请求异常！");
		}
	});
	
}

   //提交表单
$(document).ready(function() {
	$("#inputForm").validate({
		submitHandler: function(form){
			updateUser();
			//form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
});
	
	
	function updateUser(){
		var jobj=$("#inputForm").serialize();
		$.ajax({
			url : "<%=basePath%>/UpdateUser",
			type : "POST",
			dataType : "json",
			data :jobj,
			success : function(returnData,status,XMLHttpRequest)
			{	
// 			    resolveResultBeanIsOk(returnData,status);
			    if(status!="success")
			    {
		    	 alert("请求失败："+returnData.message);
			  	 return;
			    }
			    if(!returnData.success)
			  	{
			      alert("操作失败："+returnData.message);
			      return;
			  	}
			  	  alert("操作成功");
// 			  	swal("Good job!", "You clicked the button!", "success");
			  location.href="<%=basePath%>/ShowManager?tips='修改成功'";
			}
		});
		
	}
			

</script>

</head>

<body style="overflow-x: hidden; overflow-y: hidden;">
	  ${tips}
	<br/>
 <div class="userAddClz">
        
      <form id="inputForm" action="" class="form-horizontal" role="form">
    
              <input  name="id"  id="_userId" type="hidden" />
          
            <div class="form-group">
               <label class="col-sm-1 control-label" > <span class="help-inline"><font color="red">*</font> </span>登陆名：   
               </label>
               <div class="col-sd-1">
                  <input class="required input-xlarge" maxlength="20" name="userName"  id="_userName" type="text" readonly="readonly"/>
               </div>
         
            </div>
            
            
            <div class="form-group">
               <label class="col-sm-1 control-label" >密码：       
               </label>
               <div class="col-sd-1">
                  <input  maxlength="16"  placeholder="不填不修改密码" name="password" type="password" id="_password"/>
               </div>
            </div>
            
            
            <div class="form-group">
               <label class="col-sm-1 control-label" >重新输入密码：       
               </label>
               <div class="col-sd-1">
                  <input  maxlength="16" placeholder="不填不修改密码"  name="repeatPassword" type="password" id="_repeatPassword"/>
               </div>
            </div>
            

            
            
              <div class="form-group">
               <label class="col-sm-1 control-label " >
               <span class="help-inline"><font color="red">*</font>状态：</label>
               <div class="col-sd-1">
			      &nbsp;  &nbsp;&nbsp;启用 <input type="radio" name="status" value="0" checked="checked"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禁用<input type="radio" name="status" value="1"/>
               </div>
            </div>
            
            <div class="form-group">
               <label class="col-sm-1 control-label ">邮箱：</label>
               <div class="col-sd-1">
                  <input  type="text"  maxlength="30"  name="email" id="_email" readonly="readonly"/>
               </div>
            </div>
         
            <div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		    </div>    
         </form>
         
    </div>
		
</body>
</html>