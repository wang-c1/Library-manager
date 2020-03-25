<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script language="javascript">
	var times = 6;
	clock();
	function clock() {
		window.setTimeout('clock()', 1000);
		times = times - 1;
		time.innerHTML = times;
	}
</script>
<meta http-equiv= "Refresh" content= "5;url=manager_addBookForm ">
</head>
<body>
	<center>
		<hr />
		add successful!刚添加的图书的图片为：
		<s:property value="realPath" />
		<br /> <img src="../../upload/<s:property value="targetFileName"/>" />
		<br />
		<s:property value="targetFileName" />
		<br />
		<br />
		该页面将在<div class="STYLE1" id= "time"> 5 </div>自动跳转
	</center>
</body>
</html>