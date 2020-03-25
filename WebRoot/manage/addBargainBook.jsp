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
<title>添加特价图书</title>
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
	
	function check(bookId,bookPrice){
		var newPrice = document.getElementById("newPrice"+bookId).value;
		
		if(newPrice == ""){
			alert("新价格为空");
		}else if(newPrice >= bookPrice){
			alert("特价价格大于原价");
		}else{
			var args = {
				"bookId" : bookId,"bookNewPrice":newPrice
			};
			$.post('manager_addBargain', args, function(data) {
			
				if (data == "1") {
					alert("特价操作成功");
					window.location='manager_addBargainShow';
				}
				//不可用
				else if (data == "0") {
					alert("该书特价操作失败");
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
			<div id="manageBook"
				style="width: 85%; background-color: white; margin-top: 0px">
				<div class="panel-group" id="accordion">
					
					
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo">添加特价图书</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body" style="height: 100%; width: 100%">
								<div style="float: right; margin-top: 3px; margin-right: 30px">
									<form action="manager_searchForBookInBargain">
										<input type="text" id="name" name="searchCriteriaInBargain" placeholder="请输入图书的名称进行搜索"
											style="height: 30px; width: 200px">
										<button type="submit" class="btn btn-primary">搜索</button>
									</form>
								</div>
								<div style="float: left; width: 100%; overflow: scroll; height: 100%; scrolling: auto">
									
									<table width="100%" border="1"
										class="table table-striped table-bordered" id="itemtable">
										<thead>
											<tr>
												<th width="10%">图书编号</th>
												<th width="20%">ISBN编号</th>
												<th width="20%">名称</th>
												<th width="10%">作者</th>
												<th width="10%">出版社</th>
												<th width="10%">原价(￥)</th>
												<th width="10%">特价(￥)</th>
												<th width="10%">操作</th>
											</tr>
										</thead>
										<tbody>
										<!-- BookBySearchCriteriaList -->
											<s:iterator value="#request.BookBySearchCriteriaList" id="bb">
											<tr>
												<td><s:property value="#bb.bookId"/></td>
												<td><s:property value="#bb.bookISBN"/></td>
												<td><s:property value="#bb.bookName"/></td>
												<td><s:property value="#bb.bookAuthor"/></td>
												<td><s:property value="#bb.bookPress"/></td>
												<td><s:property value="#bb.bookPrice"/></td>
												<td><input type="text" id="newPrice${bookId}" name="newPrice"></td>
												<td><input type="button" value="添加特价" onclick="check('<s:property value="#bb.bookId"/>','<s:property value="#bb.bookPrice"/>');"></td>
											</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

					<script type="text/javascript">
						$(function() {
							$('#collapseTwo').collapse('show')
						});
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