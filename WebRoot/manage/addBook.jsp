<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图书</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="bootstrap/bootstrap.min.css"
	rel="stylesheet">
<script src="bootstrap/jquery-1.11.3.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<style type="text/css">
.wwFormTable {
	position: absolute;
	margin-top: -200px;
	padding-top: 0px;
}

.errorMessage {
	color: red;
	position: absolute;
	margin-left: 130px;
	margin-top: 5px;
}

table {
	*border-collapse: collapse; /* IE7 and lower */
	border-spacing: 0;
	width: 100%;
}

.bordered {
	border: solid #ccc 1px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 0 1px 1px #ccc;
	-moz-box-shadow: 0 1px 1px #ccc;
	box-shadow: 0 1px 1px #ccc;
}

.bordered tr:hover {
	background: #fbf8e9;
	-o-transition: all 0.1s ease-in-out;
	-webkit-transition: all 0.1s ease-in-out;
	-moz-transition: all 0.1s ease-in-out;
	-ms-transition: all 0.1s ease-in-out;
	transition: all 0.1s ease-in-out;
}

.bordered td, .bordered th {
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

.bordered th {
	background-color: #dce9f9;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc),
		to(#dce9f9));
	background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: linear-gradient(top, #ebf3fc, #dce9f9);
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	border-top: none;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
}

.bordered td:first-child, .bordered th:first-child {
	border-left: none;
}

.bordered th:first-child {
	-moz-border-radius: 6px 0 0 0;
	-webkit-border-radius: 6px 0 0 0;
	border-radius: 6px 0 0 0;
}

.bordered th:last-child {
	-moz-border-radius: 0 6px 0 0;
	-webkit-border-radius: 0 6px 0 0;
	border-radius: 0 6px 0 0;
}

.bordered th:only-child {
	-moz-border-radius: 6px 6px 0 0;
	-webkit-border-radius: 6px 6px 0 0;
	border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
	-moz-border-radius: 0 0 0 6px;
	-webkit-border-radius: 0 0 0 6px;
	border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
	-moz-border-radius: 0 0 6px 0;
	-webkit-border-radius: 0 0 6px 0;
	border-radius: 0 0 6px 0;
}
</style>

<script type="text/javascript">
//校验表单的JavaScript函数
function validate(form)
{
	// 定义错误字符串
	var errStr = "";
	// 依次取出表单中的四个表单域的值
	var bookISBN = form.bookISBN.value;
	var bookName = form.bookName.value;
	var bookAuthor = form.bookAuthor.value;
	var bookPress = form.bookPress.value;
	var bookAmount = form.bookAmount.value;
	var bookPrice = form.bookPrice.value;
	
	if (bookISBN == "" || bookISBN == null)
	{
		errStr += "图书的isbn必须输入";
	}
	if (bookName == "" || bookName == null)
	{
		errStr += "图书的名称必须输入";
	}
	if (bookAuthor == "" || bookAuthor == null)
	{
		errStr += "图书的作者必须输入";
	}
	if (bookPress == "" || bookPress == null)
	{
		errStr += "图书的出版社必须输入";
	}
	if (bookAmount == "" || bookAmount == null)
	{
		errStr += "图书的数量必须输入";
	}else if (/^\d+$/.test(bookAmount)) {
        
    }else {
    	errStr += "图书的数量必须为整数数字";
    }
	
	if (bookPrice == "" || bookPrice == null)
	{
		errStr += "图书的价格必须输入";
	}
	// 如果错误字符串为空，表明客户端校验通过
	if (errStr == "")
	{
		return true;
	}
	// 客户端校验没有通过，通过警告框输出校验失败提示
	else
	{
		alert(errStr);
		return false;
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
			<div style="margin-top: 0px; background-color: yellow; width: 100%; height: 35px; text-align: center; font-size: 24">添加新书</div>
			<div id="addBook" style="width: 85%; magin-top: 100px;"
				align="center">
				<form id="bookInfoForm" method="post" action="manager_addBook"
					enctype="multipart/form-data" onsubmit="return validate(this);">
					<button type="button" style="float: right;margin-left:5px"
						class="btn btn-primary btn-sm">返回</button>
					<button type="submit" style="float: right"
						class="btn btn-primary btn-sm">保存</button>
					<table class="bordered">
						<tr>
							<td>ISBN</td>
							<td><input type="text" name="bookISBN" style="width: 100%"></td>
							<td>名称</td>
							<td><input type="text" name="bookName" style="width: 100%"></td>
							<td>作者</td>
							<td><input type="text" name="bookAuthor" style="width: 100%"></td>

						</tr>
						<tr>
							<td>出版社</td>
							<td><input type="text" name="bookPress" style="width: 100%"></td>
							<td>图片</td>
							<td><input type="file" name="doc" style="width: 100%"></td>
							<td>数量</td>
							<td><input type="text" name="bookAmount"
								style="width: 100%"></td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input type="text" name="bookPrice" style="width: 100%"></td>
							<td>类型</td>
							<td><select style="width: 75%" name="typeId"
								style="width:100%">
									<s:iterator value="#request.bookTypeListInBookAction" id="bookType">
										<option><s:property value="#bookType.typeName"/></option>
									</s:iterator>
							</select></td>
							<td>简介</td>
							<td><textarea name="bookRemark" form="bookInfoForm"
									style="width: 100%">请在此处输入文本...</textarea></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>
