<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/jquery-1.11.3.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>

<script type="text/javascript">
	
	function check(orderId){
		var args = {
				"orderId" : orderId
			};
		$.post('manager_handleOrder', args, function(data) {
			
			if (data == "1") {
				alert("订单处理成功");
				window.location='manager_showIsNotDealOrders';
			}
			//不可用
			else if (data == "0") {
				alert("订单处理失败,可能是因为图书剩余量小于购买量,订单已被取消");
			}
			//服务器错误
			else {
				alert("服务器错误!");
			}
		});
	}
</script>


</head>
  
<body>
	<center>
		<jsp:include page="top.jsp"></jsp:include>
		<div id="managePage" style="width: 100%;height: 630px;background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>
			<div id="manageAllOrders">
				<ul class="singleOrders"  style="width: 100%;">
					<li style="padding-top:5px;padding-left: 10px;text-align: left;background-color: #66CC33;">
						<a class="aboutOrders" href="manager_showAllOrders">所有订单</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="aboutOrders" href="manager_showIsDealOrders">已处理订单</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="aboutOrders" href="manager_showIsNotDealOrders">未处理订单</a>
					</li>
				</ul>
				<div style="float: left; width: 100%;height: 85%;margin-top: 10px">
					<table width="100%" border="1"
						class="table table-striped table-bordered" id="itemtable">
						<thead>
							<tr>
								<th width="20%">订单编号</th>
								<th width="20%">订单日期</th>
								<th width="20%">用户ID</th>
								<th width="20%">用户姓名</th>
								<th width="10%">详细信息</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.ordersPageBean.list" id="order">
							<tr>
								<td><s:property value="#order.orderNumber"/></td>
								<td><s:property value="#order.orderTime"/></td>
								<td><s:property value="#order.user.userId"/></td>
								<td><s:property value="#order.user.userName"/></td>
								<td><a href="manager_singleOrder?orderId=<s:property value="#order.orderId"/>">详细信息</a></td>
								<s:if test="#order.orderStatus==0">
									<td><input type="button" value="处理订单" onclick="check('<s:property value="#order.orderId"/>');"></td>
								</s:if>
								<s:elseif test="#order.orderStatus==-1">
									<td><font color="red">订单已取消</font></td>
								</s:elseif>
								<s:else>
									<td>订单已处理</td>
								</s:else>
							</tr>
							</s:iterator>
						</tbody>
					</table>
					共<span style="color:red"> <s:property value="#request.ordersPageBean.allRows"/> </span>条记录
					 <span style="color:red"> <s:property value="#request.ordersPageBean.totalPage"/> </span>页  
            		当前位于第<span style="color:red"> <s:property value="#request.ordersPageBean.currentPage"/> </span>页  
            		<s:if test="#request.ordersPageBean.currentPage == 1">
            			首页&nbsp;&nbsp;&nbsp;上一页
        			</s:if>
        			<s:else>
        				<s:if test="#request.status == 2">
            				<a href="showAllOrders">首页</a>
            				&nbsp;&nbsp;&nbsp;
            				<a href="manager_showAllOrders?page=<s:property value="#request.ordersPageBean.currentPage - 1"/>">上一页</a>
            			</s:if>
            			<s:elseif test="#request.status == 1">
            				<a href="manager_showIsDealOrders">首页</a>
            				&nbsp;&nbsp;&nbsp;
            				<a href="manager_showIsDealOrders?page=<s:property value="#request.ordersPageBean.currentPage - 1"/>">上一页</a>
            			</s:elseif>
            			<s:else>
            				<a href="manager_showIsNotDealOrders">首页</a>
            				&nbsp;&nbsp;&nbsp;
            				<a href="manager_showIsNotDealOrders?page=<s:property value="#request.ordersPageBean.currentPage - 1"/>">上一页</a>
            			</s:else>
        			</s:else>
        
        			<s:if test="#request.ordersPageBean.currentPage != #request.ordersPageBean.totalPage">
        				<s:if test="#request.status == 2">
            				<a href="manager_showAllOrders?page=<s:property value="#request.ordersPageBean.currentPage + 1"/>">下一页</a>
            					&nbsp;&nbsp;&nbsp;
            					<a href="manager_showAllOrders?page=<s:property value="#request.ordersPageBean.totalPage"/>">尾页</a>
            			</s:if>
            			<s:elseif test="#request.status == 1">
            				<a href="manager_showIsDealOrders?page=<s:property value="#request.ordersPageBean.currentPage + 1"/>">下一页</a>
            					&nbsp;&nbsp;&nbsp;
            					<a href="manager_showIsDealOrders?page=<s:property value="#request.ordersPageBean.totalPage"/>">尾页</a>
            			</s:elseif>
            			<s:else>
            				<a href="manager_showIsNotDealOrders?page=<s:property value="#request.ordersPageBean.currentPage + 1"/>">下一页</a>
            					&nbsp;&nbsp;&nbsp;
            					<a href="manager_showIsNotDealOrders?page=<s:property value="#request.ordersPageBean.totalPage"/>">尾页</a>
            			</s:else>
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
