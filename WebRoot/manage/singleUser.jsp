<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户详细信息</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  
<body>
	<center>
		<jsp:include page="top.jsp"></jsp:include>
		<div id="managePage"
			style="width: 100%; height: 80%; background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>
			<div id="manageBook"
				style="width: 85%; background-color: white; margin-top: 0px">
				<h1><s:property value="#request.singleUser.userName"/>&nbsp;&nbsp;&nbsp;&nbsp;的信息</h1>
				<hr style="width: 300px;"/>
				<s:form>
					<s:label label="用户名" value="%{#request.singleUser.userName}" cssStyle="text-align:left;"></s:label>
					<s:label label="昵称" value="%{#request.singleUser.userNickname}"></s:label>
					<s:label label="性别" value="%{#request.singleUser.userSex}"></s:label>
					<s:label label="邮箱" value="%{#request.singleUser.userEmail}"></s:label>
					<s:label label="电话" value="%{#request.singleUser.userPhone}"></s:label>
					<s:label label="备注" value="%{#request.singleUser.userRemark}"></s:label>
				</s:form>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>
