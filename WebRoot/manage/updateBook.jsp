<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改图书</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/jquery-1.11.3.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
	
<script type="text/javascript">
	
	function check(bookId){
		var increment = document.getElementById("increment").value;
		alert(increment);
		if(increment == ""){
			alert("新增量为空");
		}else{
			var args = {
				"bookId" : bookId,"increment":increment
			};
			$.post('manager_updateBook', args, function(data) {
			
				if (data == "1") {
					alert("图书修改成功");
					window.location='manager_singleBook?bookId='+bookId;
				}
				//不可用
				else if (data == "0") {
					alert("图书修改失败");
				}
				//服务器错误
				else {
					alert("服务器错误!");
				}
			});
		}
	}
</script>

  </head>
  
<body>
	<center>
		<jsp:include page="top.jsp"></jsp:include>
		<div id="managePage"
			style="width: 100%; height: 80%; background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>	
			<div id="addBook">
				<h1>修改图书</h1><br/>
				<form action="updateBookAction" method="post" enctype="multipart/form-data">
					<input type="hidden" value="%{#request.singleBook.bookId}">
					名称：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookName"/>"><br/>
					作者：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookAuthor"/>"><br/>
					出版社：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookPress"/>"><br/>
					类别：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.type.typeName"/>"><br/>
					价格：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookPrice"/>"><br/>
					简介：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookRemark"/>"><br/>
					数量：<input type="text" disabled="disabled" value="<s:property value="#request.singleBook.bookAmount"/>"><br/>
					增量：<input type="text" name="increment" id="increment"><br/>
					
					<input type="button" value="保存修改" onclick="check('<s:property value="#request.singleBook.bookId"/>');">
				</form>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>		
	</center>
</body>
</html>
