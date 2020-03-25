<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/jquery-1.11.3.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<title>图书类别管理</title>
<style type="text/css">
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
	$(function() {
		$("#typeName").blur(
			function() {
				var val = $(this).val();
				val = $.trim(val);
				var $this = $(this);
				if (val != "") {
				//把当前节点后面的所有 p 兄弟节点删除
					$this.nextAll("p").remove();
					var url = "manager_validateTypeName";
					var args = {"typeName" : val};
					$.post(url,args,function(data) {
						//表示可用
						if (data == "1") {
							$this.after("<p id='message' style='color:green;font-size:16'>类型名可用</p>");
							/* alert(document.getElementById('message').innerText); */
						}
						//不可用
						else if (data == "0") {
							$this.after("<p id='message' style='color:red;font-size:16'>类型名已被占用</p>");
							/* alert(document.getElementById('message').innerText); */
						}
						//服务器错误
						else {
							alert("服务器错误!");
						}
				});
			} else {
				$(this).val("");
				$this.focus();
			}
		});
	})

	function addBookType() {
		var val = $("#typeName").val();
		if (val != "") {
			var url = "manager_addBookType";
			var args = {
				"typeName" : val
			};
			$.post(url, args, function(data) {
				//表示可用
				if (data == "1") {
					alert("添加成功");
					window.location.href="manager_manageBookType.jsp";
				}
				//不可用
				else if (data == "0") {
					alert("添加失败");
				}
				//服务器错误
				else {
					alert("服务器错误!");
				}
			});
		} else {
			alert("类型名不可为空");
			$(this).val("");
			$this.focus();
		}
	}
	
	function validate(form){
		// 定义错误字符串
		var errStr = "";
		var typeName = form.typeName.value;
		if(typeName == ""){
			errStr = "类型名不能为空";
		}
		
		if(errStr == ""){
			return true;
		}else{
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
			<div id="manageBook"
				style="width: 85%; background-color: white; margin-top: 0px">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne">图书类别管理 </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body" style="height: 80%; width: 100%">
								<div
									style="float: left; width: 70%; overflow: scroll; height: 100%; scrolling: auto">
									<s:action name="manager_allBookType" executeResult="true"></s:action>
									<table width="100%" border="1"
										class="table table-striped table-bordered" id="itemtable">
										<thead>
											<tr>
												<th width="30%">类型id</th>
												<th width="70%">类型名称</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="#attr.typeList" status="st">
												<tr>
													<td><s:property value="typeId" /></td>
													<td><s:property value="typeName " /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div style="float: left; width: 30%; margin-top: 20px">
									<form name="typeform" class="form-horizontal" method="post" onsubmit="return validate(this);">
										<div class="form-group">
											<label for="typeName" class="col-sm-2 control-label">名称</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="typeName"
													name="typeName" placeholder="请输入类别名称"
													onblur="validateTypeName();">
											</div>
										</div>
										<div class="form-actions" style="margin-top: 50px">
											<button type=button class="btn btn-success btn-large"
												onclick="addBookType();">添加类别</button>
											<button class="btn btn-large">取消操作</button>
										</div>
										<!-- /form-actions -->
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

					<script type="text/javascript">
						$(function() {
							$('#collapseOne').collapse('show')
						});
					</script>
				</div>
			</div>
			<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
</html>