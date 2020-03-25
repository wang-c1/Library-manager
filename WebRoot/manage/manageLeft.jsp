<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="skin/js/frame/menu.js"></script>

</head>
<body>
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>常用操作</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href="manager_addBookForm">添加新书</a></div>
                <div class='flrct'> <a href="manager_addBookForm"><img src='skin/images/frame/gtk-sadd.png' alt='添加图书' title='添加图书'/></a> </div>
              </div>
            </li>
            <li><a href='manager_manageBookType.jsp'>类别管理</a> </li>
            <li><a href='manager_removeBargainBook'>移除特价</a> </li>
            <li><a href='manager_addBargainShow'>添加特价</a> </li>
            <li><a href='manager_bookList'>图书信息 </a> </li>
            <li><a href='manager_userList'>用户信息</a> </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='manager_showAllOrders'>订单管理</a></div>
                <div class='flrct'> <a href='manager_showAllOrders'><img src='skin/images/frame/gtk-del.png' alt='订单管理' title='订单管理'/></a> </div>
              </div>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>统计信息</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='manager_showBookStatisticsForDate'>图书销售统计(类别)</a></li>
            <li><a href='manager_showBooksaleDate'>图书销售数量</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
      <!-- Item 3 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>系统设置</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            
            <li><a href='manager_managerExit'>安全退出</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 3 End -->
	  </td>
  </tr>
</table>
</body>
</html>