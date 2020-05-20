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
<title>图书下架</title>

<script type="text/javascript">
	
	function check(bookId){
		alert(bookId);
		var args = {
				"bookId" : bookId
			};
		$.post('manager_offshelvesBook', args, function(data) {
			
			if (data == "1") {
				alert("下架成功");
				window.location='manager_bookList';
			}
			//不可用
			else if (data == "0") {
				alert("下架失败");
			}
			//服务器错误
			else {
				alert("服务器错误!");
			}
		});
	}
	
	function check1(bookId){
		alert(bookId);
		var args = {
				"bookId" : bookId
			};
		$.post('manager_deleteBook', args, function(data) {
			
			alert("删除图书成功!");
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
					<form action="manager_searchForBook">
						<input type="text" name="searchCriteria" placeholder="请输入图书的名称"
							style="height: 30px; width: 200px">
						<button type="submit" class="btn btn-primary">搜索</button>
					</form>
				</div>
				<div style="float: left; width: 100%; height: 85%; margin-top: 10px">
					<table width="100%" border="1"
						class="table table-striped table-bordered" id="itemtable">
						<thead>
							<tr>
								<th width="10%">图书编号</th>
								<th width="15%">ISBN编号</th>
								<th width="15%">名称</th>
								<th width="10%">作者</th>
								<th width="15%">出版社</th>
								<th width="10%">类别</th>
								<th width="5%">价格</th>
								<th width="10%">详细信息</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.pageBean.list" id="book">
							<tr>
								<td><s:property value="#book.bookId"/></td>
								<td><s:property value="#book.bookISBN"/></td>
								<td><s:property value="#book.bookName"/></td>
								<td><s:property value="#book.bookAuthor"/></td>
								<td><s:property value="#book.bookPress"/></td>
								<td><s:property value="#book.type.typeName"/></td>
								<td><s:property value="#book.bookPrice"/></td>
								<td><a href="manager_singleBook?bookId=<s:property value="#book.bookId"/>">详细信息</a></td>
								<s:if test="#book.bookStatus==0">
									<td>
									<font color="red">已下架</font>
									<input type="button" style="width: 40px" value="删除" onclick="check1('<s:property value="#book.bookId"/>');">
									</td>
								</s:if>
								<s:else>
									<td>
									<input type="button" style="width: 40px" value="下架" onclick="check('<s:property value="#book.bookId"/>');">
									<input type="button" style="width: 40px" value="删除" onclick="check1('<s:property value="#book.bookId"/>');">
									</td>
								</s:else>
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
            			<a href="userList">首页</a>
            			&nbsp;&nbsp;&nbsp;
            			<a href="manager_offshelvesBook?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a>
        			</s:else>
        
        			<s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            			<a href="manager_offshelvesBook?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a>
            				&nbsp;&nbsp;&nbsp;
            				<a href="manager_offShelvesBook?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a>
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
