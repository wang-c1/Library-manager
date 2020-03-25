<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书销售统计（类别）</title>
<script src="./echarts.js"></script>
<script src="./jquery-1.js"></script>
<sx:head />

</head>

<body bgcolor="white">
	<center>
		<jsp:include page="top.jsp"></jsp:include>
		<div id="managePage"
			style="width: 100%; height: 80%; background-color: white;">
			<div id="manageLeft">
				<jsp:include page="manageLeft.jsp"></jsp:include>
			</div>
			<div style="width: 80%" align="center">
				<div style="float: left; margin-top: 3px; margin-right: 30px">
					<%-- <form action="searchForBookInBargain">
						<sx:datetimepicker name="startDate" label="开始时间"
							displayFormat="yyyy年/MM月dd日" />
						<sx:datetimepicker name="endDate" label="结束时间"
							displayFormat="yyyy年/MM月dd日" />
						<button type="submit">显示</button>
					</form><br/> --%>
					<!--img alt="" src="manager_bookStatisticsForDate"-->
					<div id="plate" style="width:600px;height:400px;"></div>
				</div>
			</div>
		</div>
		<jsp:include page="bottom.html"></jsp:include>
	</center>
</body>
<script>
$.ajax({
	url:"pla.json",
	dataType:"json",
	type:'GET',
	success:function (somedata) {
		var dataFir = [];
        var dataSec = [];
        var i = 0;
        for(i = 0;i<somedata.pla.length;i++)
        {
          var vall,namm;
          namm = somedata.pla[i].nam;
          vall = somedata.pla[i].val;
          dataFir[i] = namm;
          dataSec[i] = {
                  value:vall,
                  name:namm
                }
          
          //dataSec[i] = vall;
        }
		var myChart = echarts.init(document.getElementById('plate'));
		var option = {
			    title : {
			        text: '图书销售类别统计玫瑰图',
			        //subtext: '纯属虚构',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        x : 'center',
			        y : 'bottom',
			        data:dataFir
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true,
			                type: ['pie', 'funnel']
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'面积模式',
			            type:'pie',
			            radius : [30, 110],
			            center : ['50%', '50%'],
			            roseType : 'area',
			            data:dataSec
			        }
			    ]
			};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
			//alert("1")
	},
	error:function(){
			alert('获取数据失败！')
	},
});

</script>
</html>
