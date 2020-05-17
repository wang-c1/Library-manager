<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/jquery-1.11.3.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<title>所有用户信息</title>
<script type="text/javascript">
function check(userId){
	alert("确认删除用户号为:" + userId + "的用户？");
	var args = {
			"userId" : userId
		};
	$.post('manager_deleteUser', args, function(data) {
		
		alert("删除用户成功!");
	});
}
</script>

</head>

<body bgcolor="white">
	<center>
		<jsp:include page="top.jsp"></jsp:include>
		<div id="managePage"
			style="width: 100%; height: 80%; background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>
			<div id="manageBook"
				style="width: 85%; background-color: white; margin-top: 0px">
				<div style="float: right; margin-top: 20px; margin-right: 30px">
					<input type="text" id="name" placeholder="请输入用户的名称"
						style="height: 30px; width: 200px">
					<button type="button" class="btn btn-primary">搜索</button>
				</div>
				<div style="float: left; width: 100%;height: 85%;margin-top: 10px">
					<table width="100%" border="1"
						class="table table-striped table-bordered" id="itemtable">
						<thead>
							<tr>
								<th width="10%">用户编号</th>
								<th width="20%">用户名</th>
								<th width="20%">邮箱</th>
								<th width="10%">昵称</th>
								<th width="10%">性别</th>
								<th width="10%">电话</th>
								<!--<th width="10%">备注</th>-->
								<th width="10%">详细信息</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.pageBean.list" id="user">
							<tr>
								<td><s:property value="#user.userId"/></td>
								<td><s:property value="#user.userName"/></td>
								<td><s:property value="#user.userEmail"/></td>
								<td><s:property value="#user.userNickName"/></td>
								<td><s:property value="#user.userSex"/></td>
								<td><s:property value="#user.userPhone"/></td>
								<!--<td><s:property value="#user.userRemark"/></td>-->
								<td><a href="singleUser?userId=<s:property value="#user.userId"/>">详细信息</a></td>
								<td>
									<input type="button" style="width: 40px" value="删除" onclick="check('<s:property value="#user.userId"/>');">
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
					共<span style="color:red"> <s:property value="#request.pageBean.allRows"/> </span>条记录
					 <span style="color:red"> <s:property value="#request.pageBean.totalPage"/> </span>页  
            		当前位于第<span style="color:red"> <s:property value="#request.pageBean.currentPage"/> </span>页  
            		<s:if test="#request.pageBean.currentPage == 1">
            			首页&nbsp;&nbsp;&nbsp;上一页
        			</s:if>
        			<s:else>
            			<a href="manager_userList">首页</a>
            			&nbsp;&nbsp;&nbsp;
            			<a href="manager_userList?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a>
        			</s:else>
        
        			<s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            			<a href="manager_userList?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a>
            				&nbsp;&nbsp;&nbsp;
            				<a href="manager_userList?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a>
        			</s:if>
        
        			<s:else>
            			下一页&nbsp;&nbsp;&nbsp;尾页
        			</s:else>
				</div>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>
