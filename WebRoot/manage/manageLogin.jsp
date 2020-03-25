<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link href="./manage_login_css/bootstrap.min.css" rel="stylesheet" />
<link href="./manage_login_css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="./manage_login_css/font-awesome.css" rel="stylesheet" />
<link href="./manage_login_css/adminia.css" rel="stylesheet" />
<link href="./manage_login_css/adminia-responsive.css" rel="stylesheet" />
<link href="./manage_login_css/pages/login.css" rel="stylesheet" />
<title>Login</title>

<script type="text/javascript">
	function check(form) {
		if (document.forms.loginForm.username.value == "") {
			alert("请输入用户名");
			document.forms.loginForm.username.focus();
			return false;
		}
		if (document.forms.loginForm.password.value == "") {
			alert("请输入密码");
			document.forms.loginForm.password.focus();
			return false;
		}
	}
</script>
<style>
#login-header{width: auto;min-height: 70px;line-height: 70px;}
#login-header{border-bottom: medium solid #39A631;}
h3{font-family:"微软雅黑";font-style: normal;font-size: 2em;font-weight:800 ;color: #39A631;padding-left: 1em;}
#content{width: auto;height:28em;text-align: center;background-image: url(../img/xgxt_login_bg.jpg);background-size: 100% 100%;}
#con_title{background-color: #8EC172;width: auto;height: 3.5em;line-height: 3.5em;text-align: center;}
#con_title_sp{font-family: "微软雅黑";font-size: 1.5em;font-weight: 800;color: #FFF;}
#con{width: 35%;height: 20em;margin-top:4em;}
#con{border: thin solid #8EC172;}
.con_input{margin: 2em 0 1em 0;}
.btn{width: 8em;height: 2em;background-color: #62ab00;border-radius: 4px;border: 0px;color: #fff;font-family:"微软雅黑";font-size: 1em;font-weight: bold;}
.con_input span{font-family: "微软雅黑";font-size: 1em;font-weight: bold;color: #333;}
.con_input input{width: 15em;padding: 0.5em 1em;border: 1px solid #bbb;}
.btn{margin: 1em 0 1em 0;}
</style>
</head>
<body>
	<div id="login-container">
		<div id="login-header">
			<h3>系统登录</h3>
		</div>
		<div id="content">
			<center>
			<div id="con">
				<div id="con_title">
					<span id="con_title_sp">管理员请登陆</span>
				</div>
				<div id="login-content" class="clearfix">
					<s:actionerror/>
					<form action="manager_Login" method="post" name="loginForm">
					<div class="con_input">
						<span>用户名：</span><input type="text" id=user placeholder="用户名" name="managerName"/>
					</div>
					<div class="con_input">
						<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" id=pass placeholder="密码"  name="managerPassword"/>
					</div>
						<!--div class="control-group">
							<label class="control-label" for="managerName">用户账号</label>
							<div class="controls">
								<input type="text" name="managerName" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="managerPassword">登录密码</label>
							<div class="controls">
								<input type="password" name="managerPassword" />
							</div>
						</div-->
						<div class="pull-right">
							<button type="submit" class="btn"
								onclick="return check(this);">登录</button>
						</div>
					</form>
				</div>
			</div>
			</center>
		</div>
		<!-- /login-content -->
	</div>
	<!-- /login-wrapper -->

	<script src="./js/jquery-1.7.2.min.js"></script>
	<script src="./js/bootstrap.js"></script>
</body>
</html>