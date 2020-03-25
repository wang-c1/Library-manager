<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单信息</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="bootstrap/jquery-1.11.3.min.js"></script>
	<script src="bootstrap/bootstrap.min.js"></script>
</head>
  
<body>
	<center>
    	<jsp:include page="top.jsp"></jsp:include>	
		<div id="managePage" style="width: 100%;height: 80%;background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>
			<div id="singleOrders" style="width:80%" align="center">
			
				<ul class="singleOrdersBook" style="background-color: rgb(23,157,244);width: 80%">
					<li class="singleOrdersHead" style="color: white;text-align: left;padding-left: 10px;padding-top: 5px;padding-bottom: 5px">
						
						收件人信息：
						&nbsp;&nbsp;&nbsp;&nbsp;
					</li>
				</ul>
				<s:action name="singleOrdersAction" executeResult="false"></s:action>
				<ul class="shoppingBookUl" style="margin-top: 10px;margin-bottom: 10px;">
				<li>
					收件人姓名：<s:property value="#request.address.receiverName"/> 
				</li>
				<li>
					收件人电话：<s:property value="#request.address.receiverPhone"/> 
				</li>
				<li>
					收件人地址：<s:property value="#request.address.province"/>
							 <s:property value="#request.address.city"/>
							 <s:property value="#request.address.detailedAddress"/>
				</li>
				</ul>
				
				<ul class="singleOrdersBook" style="background-color: rgb(23,157,244);width: 80%">
					<li class="singleOrdersHead" style="color: white;text-align: left;padding-left: 10px;padding-top: 5px;padding-bottom: 5px">
						总计金额：<s:property value="#request.totalMoneyInOrdersBookAction"/> 元
						&nbsp;&nbsp;&nbsp;&nbsp;
						状态：
						<s:if test='#request.orderStatusInOrdersBookAction == 0'>未处理</s:if>
						<s:else>已处理</s:else>
						&nbsp;&nbsp;&nbsp;&nbsp;
						日期：<s:property value="#request.orderTimeInOrdersBookAction"/>
					</li>
				</ul>
				
				<div style="float: center; width: 80%; overflow: scroll; height: 80%; scrolling: auto;margin-top: 20px">
					<table width="100%" border="1"
						class="table table-striped table-bordered" id="itemtable">
						<thead>
							<tr>
								<th width="20%">图书ISBN</th>
								<th width="20%">图书名称</th>
								<th width="20%">图书价格</th>
								<th width="20%">购买数量</th>
								<th width="20%">出版社</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.ordersBookList" id="ob" status="st">
							<tr>
								<td><s:property value="#ob.book.bookISBN"/></td>
								<td><s:property value="#ob.book.bookName"/></td>
								<s:if test="#ob.book.bargainStatus==0">
									<td><s:property value="#ob.book.bookPrice"/></td>
								</s:if>
								<s:else>
									<td><s:property value="#ob.book.bookNewPrice"/></td>
								</s:else>
								
								<td><s:property value="#ob.bookAmount"/></td>
								<td><s:property value="#ob.book.bookPress"/></td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>
