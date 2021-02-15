<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
	String appContext = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ appContext;
	String tips = request.getParameter("tips");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="<%=basePath%>/resources/css/pager.css" rel="stylesheet"
	type="text/css" />
	

<script src=" <%=basePath%>/resources/js/jquery/jquery-3.5.1.min.js"></script>
<script src=" <%=basePath%>/resources/js/common.js"></script>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>

<body>
     ${tips}<br/>
     <a href="<%=basePath%>/ShowIndex">首页</a>
     <br/>
        <form action="" class="breadcrumb form-search " id="_search_form">
             &nbsp;登陆名: <input class="input-xlarge" name="userName"  id="_userName" type="text" />
			 &nbsp;<input id="_search_submit" class="btn btn-primary" type="button" value="查询"/>
		</form>
<br/>
	<div class="panel panel-default">
		<table id="_user_table" class="table table-striped"  b>
			<thead>
				<tr>
					<th width="10%">序号</th>
					<th width="10%">id</th>
					<th width="10%">用户名</th>
					<th width="10%">状态</th>
					<th width="20%">邮箱</th>
					<th width="20%">创建时间</th>
					<th width="20%">操作</th>
				</tr>
			</thead>

			<tbody>
				
			</tbody>

		</table>

    <div class="pagination" id="_pagination">
    
</div>
	</div>
	
	
<script type="text/javascript">

//初始化
$(function() {
	var param=new Object();	
	    param.status="0";
	//初始化数据	
	pageFn("<%=basePath%>/UserList",param,"get");
})


/**分页回调处理**/
function callbak_page(returnDatas)
{
	//alert(JSON.stringify(returnDatas));
	
	var tableHTML = "";
	var trContext = $("#_user_table");
	//$("#_user_table tbody tr").eq(0).nextAll().remove();
	$("#_user_table tbody tr").remove();
	var returnData = returnDatas.data.items;
	
	if(returnData==null){
	  return;
	}
	for (var i = 0; i < returnData.length; i++) {
	var j=i;
	var statusname="启用"
	if(returnData[i].status==1){
		statusname="禁用"
	}
	
	var date = new Date(returnData[i].createTime);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds(); 
	var cdate=Y+M+D+h+m+s; //呀麻碟
	
	//状态处理
	//var sta=returnData[i].status;
	var staName="";
	//<a href="#">查看</a>
	var operation=''
				   +'&nbsp;&nbsp;<a href="<%=basePath%>/ShowUpdateUser?id='+returnData[i].id+'">修改</a>'
				  /*  +'&nbsp;&nbsp;<a href="#" onclick="delUser(this,'+returnData[i].id+')">删除</a>'; */
				   
	tableHTML += "<tr>" + "<td>"
				+ (j + 1)
				+ "</td>"
				+ "<td>"
				+ returnData[i].id
				+ "</td>"
				+ "<td>"
				+ returnData[i].userName
				+ "</td>"										
				+ "<td>"
				+ statusname
				+ "</td>"
				+ "<td>"
				+ returnData[i].email
				+ "</td>"
				+ "<td>"
				+ 	cdate
				+ "</td>"						
				+ "<td>" 
				+operation									
				+"</td>" 
				+ "</tr>"
	
	}
	trContext.append(tableHTML);

}

//查询
$("#_search_submit").click(
	function(){
	 var param=getParam();
	 sendPageRequst("<%=basePath%>/UserList",param);
	   
	  // sendGetReq(getContextPath()+"/sys/user/list",param,"");
});

//获取参数处理
function getParam()
{				
	 var param=new Object();	
	
	 var _userName=$("#_userName").val();
	 if(_userName!=""){
		 param.userName=_userName;
	 }
	 
	return param;			 
}

//删除用户
function delUser(obj,id)
{
	
	var result=confirm("确定要删除用户吗？");
	
	

	if(!result){
		return;
	}
	
	if(id==""){
		alert("参数错误");
		return;
	}
// 	alert(id);
	$.ajax({
		url : getContextPath()+"/sys/user/del",
		type : "POST",
		//contentType: "application/json; charset=utf-8",
		dataType : "json",
		//data :{productVoJson:JSON.stringify(objVo)},
		data :{"id":id},
		success : function(returnData,status,XMLHttpRequest)
		{	
			if(resolveResultBeanIsOk(returnData,status)){
				$this=$(obj);
				var delTr=$this.parent().parent();
				$(delTr).remove();
			}
		},
		error : function() {
			alert("异常！");
		}
	});
}




</script>


</body>
</html>