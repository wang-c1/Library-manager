<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>ͼ����Ϣ</title>
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
				<ul>
					<li class="singleBookName" style="color: black;background-color: yellow;width: 80%">
						<s:property value="#request.singleBook.bookName"/>
					</li>
					<li class="singleBookPicture" style="align:center">
						<img src='../../upload/<s:property value="#request.singleBook.bookPicture"/>' />
					</li>
					<li class="singleBookInfor">
						���ߣ�<a class="aboutBook" href="oneType.jsp?searchType=bookAuthor&searchDescribe=<s:property value="#request.singleBook.bookAuthor"/>"><s:property value="#request.singleBook.bookAuthor"/></a><br/>
						�����磺<a class="aboutBook" href="oneType.jsp?searchType=bookPress&searchDescribe=<s:property value="#request.singleBook.bookPress"/>"><s:property value="#request.singleBook.bookPress"/></a><br/>
						���<a class="aboutBook" href="oneType.jsp?searchType=bookType&searchDescribe=<s:property value="#request.singleBook.type.typeId"/>"><s:property value="#request.singleBook.type.typeName"/></a><br/>
						�ϼ�ʱ�䣺<s:date name="#request.singleBook.bookShelveTime" format="yyyy-MM-dd HH:mm:ss"/><br/>
						ͼ���飺<s:property value="#request.singleBook.bookRemark"/><br/>
						ԭ�ۣ�<font style="text-decoration: line-through;color:red;"><s:property value="#request.singleBook.bookPrice"/></font> Ԫ<br/>
						����������<s:property value="#request.singleBook.bookSales"/>��&nbsp;&nbsp;&nbsp;&nbsp;
						���������<s:property value="#request.singleBook.bookAmount"/>��<br/>
						<a class="bookName" href='manager_showUpdateBook?bookId=<s:property value="#request.singleBook.bookId"/>'>�޸�ͼ����Ϣ</a>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>
