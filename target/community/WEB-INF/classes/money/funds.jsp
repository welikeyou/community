<%@ page import="com.java.model.Fund" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-资金管理</title>
	
	<link rel="stylesheet" type="text/css" href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/checkapply.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">

	<script src="/js/prefixfree.min.js"></script>
	<script type="text/javascript" src="/js/checkapply.js"></script>

	<!-- 必要文件 -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.2/raphael-min.js"></script>
	<!-- 图表文件 -->
	<script src="/chartlib/chart.js"></script>
	<link rel="stylesheet" href="/chartlib/chart.css">
	<script src="/chartlib/morris.js"></script>
	<!-- 标签JS -->
	<link rel="stylesheet" href="/chartlib/morris.css">
</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
<jsp:include page="/common/comTittle.jsp"/>
	<div class="mainbox" style="display: flex;flex-direction: column;">
		<div  style="width: 1130px;">
			<table id="funds">
				<thead>
					<tr>
						<th colspan="7">历史账单</th>
					</tr>
					<tr style="font-weight: 900;padding-right: 5px;">
						<td>序号</td><td>负责人</td><td>支出</td><td>收入</td><td>余额</td><td>日期</td><td>备注</td>
					</tr>
				</thead>
				<tbody class="scrolltbody">
					<!-- 未审核申请列表 -->
					<!-- 循环体 -->
					<form action="/Fund_Manage.do" method="post" id="updateForm">
						<script>
							var n=0;   //折线图数组元素个数
                            var dataArray=new Array(); //data:json数组
						</script>  <%--折线图数组元素的个数--%>
						<%
						double incomeSum=0;
                        double costSum=0;
		List<Fund> list=(List<Fund>) request.getAttribute("fundInfo");
		if(list==null){

		}else{
		    for(Fund f:list){
         int i=1;
         incomeSum+=f.getIncome();
          costSum+=f.getCost();

	%>
						<script>
							//获得json对象数组
							if(n<10){
							dataArray[n]=<%=f.getResult()%>;
							var s=JSON.stringify(dataArray[n]);
							var y=s+"";
							var m=JSON.parse(y);
							dataArray[n]=m;
							n++;}
						</script>
					<tr class="establish-apply-uck">
						<td name="numDisplay"><%=i%></td>
						<td name="personDisplay"><%=f.getPerson()%></td>
						<td name="costDisplay"><%=f.getCost()%></td>
						<td name="incomeDisplay"><%=f.getIncome()%></td>
						<td name="previousMoneyDisplay"><%=f.getPreviousMoney()%></td>
						<td name="dateDisplay"><%=f.getDate()%></td>
						<td name="remarkDisplay"><%=f.getRemark()%></td>
					</tr>
								<%
		i++;}
		}
%>
					<!-- 循环体结束 -->
				</tbody>
				</form>
				<tbody>
					<tr>
						<td>
							<button class="table-button" id="yellow" style="float:left;" onclick="ShowDiv('update','fade')">历史账务补充</button>
							<button class="table-button" id="red" style="float:right;" onclick="ShowDiv('add','fade')">添加</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="width: 1130px;display: flex;flex-direction: row;">
			<div class="card">
				<div class="headline"></div>
				<h2>历史账务统计图</h2>
				<div id="fundslinechart"></div>
			</div>
			<div class="card">
				<div class="headline"></div>
				<h2>社团资金组成</h2>
				<div id="fundsdonutchart"></div>
			</div>
		</div>		

		<!-- 在此上传数据 -->
		<script type="text/javascript">
            dataArray2=new Array();
            dataArray2[0]= { label: '收入', value: <%=incomeSum%> };
            dataArray2[1]= { label: '支出', value: <%=costSum%> };
            var s=JSON.stringify(dataArray2[0]);
            var y=s+"";
            var m=JSON.parse(y);
            dataArray[0]=m;
            s=JSON.stringify(dataArray2[1]);
            y=s+"";
            m=JSON.parse(y);
            dataArray[1]=m;

            new Morris.Line({
				element: 'fundslinechart',

				  // 在此添加数据 注：每一行数据结束都要打“，”，最后一行除外
				  /*data: [
				  { 'day': '2018-7-2', "income": 150},
				  ],*/

				  //json对象数组
                data:dataArray,

				  xkey: 'day',
				  ykeys: ['income','outcome','rest'],
				  labels: ['收入(￥)','支出(￥)','余额(￥)'],
				  lineColors: ['#28a745','#17a2b8','#007bd9','#dc3545','#fd7e14','#ffc107'],
				  parseTime: false,
				  xLabelAngle: 45
				});
			new Morris.Donut({
				element: 'fundsdonutchart',

				  // 在此添加数据 注：每一行数据结束都要打“，”，最后一行除外
				  data: dataArray2,


				  colors: ['#dc3545','#fd7e14','#ffc107','#28a745','#17a2b8','#007bd9']
				});
			</script>
		</div>
		<!--弹出层时背景层DIV-->
		<div id="fade" class="black_overlay"></div>
		<!-- 添加账务操作 -->
		<div id="add" class="white_content"style="margin-left:8%;margin-top: 7%;">
			<div>
				<span onclick="CloseDiv('add','fade')" style="left: 32%;top:20%"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
			</div>
			<%
				//获得associaton中的原始money（余额）
			double money=(double)request.getAttribute("money");
				//测试

			%>
			<form action="/Fund_Manage.do" method="post" id="addForm" onsubmit="check(this)">
			<table class="details" >
				<tr><th colspan="2">账务添加</th></tr>
				<tr><td>负责人</td><td><input type="text" id="sss" name="person"></td></tr>
				<tr><td>支出</td><td><input type="text" name="cost"></td></tr>
				<tr><td>收入</td><td><input type="text" name="income"></td></tr>

				<tr><td>余额</td><td id="text"><%=money%></td></tr>
				<input type="hidden" name="moneyDiv" value="" id="moneyDiv1">

				<tr><td >日期</td><td><input type="date" name="date"></td></tr>
				<tr><td>备注</td><td><input type="text" name="remark"></td></tr>
				<tr>
					<td colspan="2">
						<button type="button"class="table-button" style="float:left;" id="green" onclick="add('addFund','addForm')/*;CloseDiv('add','fade')*/">确定</button>
						<input type="hidden" name="method" value="" id="addmethod">
						<button type="button" class="table-button" style="float:right;" id="red" onclick="CloseDiv('add','fade')">取消</button>
					</td>
				</tr>
			</table>
			</form>
		</div>

		<!-- 补款扣款操作 -->
	<form action="/Fund_Manage.do" method="post" id="modifyForm" >
		<div id="update" class="white_content" style="margin-left:8%;margin-top: 7%;">
			<div>
				<span onclick="CloseDiv('update','fade')" style="left: 34%;top:20%"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
			</div>
			<table class="details">
				<tr><th colspan="2">历史账务补充</th></tr>
				<tr><td>负责人</td><td><input type="text" id="sss1" name="person"></td></tr>
				<tr><td>多支出补款</td><td><input type="text" name="cost"></td></tr>
				<tr><td>多收入退款</td><td><input type="text" name="income"></td></tr>

				<tr><td>余额</td><td id="text"><%=money%></td></tr>
				<input type="hidden" name="moneyDiv" value="" id="moneyDiv2">

				<tr><td >日期</td><td><input type="date" name="date"></td></tr>
				<tr><td>备注</td><td><input type="text" name="remark"></td></tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="method" value="" id="modifyMethod">
						<button type="button" class="table-button" style="float:left;" id="green" onclick="add('addFund','modifyForm')">确定</button>
						<button type="button" class="table-button" style="float:right;" id="red" onclick="CloseDiv('update','fade')">取消</button>
					</td>
				</tr>
			</table>
		</div>
	</form>

<jsp:include page="/common/comExit.jsp"/>
	</body>
	</html>