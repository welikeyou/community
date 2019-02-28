<%@ page import="com.java.model.Activity_Apply" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"
%>

<%--审核活动--%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-管理层批准审核</title>
	<link rel="stylesheet" type="text/css" href="/css/checkapply.css"/>
	<link rel="stylesheet" type="text/css"href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
	<script type="text/javascript" src="/js/checkJoin.js"></script>

	<script src="/js/prefixfree.min.js"></script>
</head>
<body>
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay"></div>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>
	<jsp:include page="/common/comTittle.jsp"/>


	<div class="mainbox">
		<form action="/Update_Act.do" method="post" id="mainForm">
		<table class="uncheck">
			<!-- 表头 -->
			<thead>
				<tr>
					<th colspan="6">未审核报名表</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="uckall" name="ubox" onclick="ucheckall()" />all</td>
					<td>活动</td><td>报名人</td><td>报名时间</td>
				</tr>
			</thead>
			<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%
					List<Activity_Apply> uncheckedAcJoin=(List<Activity_Apply>) request.getAttribute("uncheckedAcJoin");
					if(uncheckedAcJoin==null){
					}else{
						for(Activity_Apply unchecked:uncheckedAcJoin){
							int i=1;
							//System.out.println("批量未审核:"+unchecked.getUnique());
				%>
				<tr class="establish-apply-uck">
					<td style="padding-top: 20px;"><input type="checkbox" name="1box"  value="<%=unchecked.getUnique()%>"/></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getTitle()%></p></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentName()%></p></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getTime()%></p></td>
					<input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getStudentNum()%>">
					<input type="hidden" name="<%=unchecked.getUnique()%>"  value="<%=unchecked.getTitle()%>">
					<input type="hidden" name="<%=unchecked.getUnique()%>"  value="<%=unchecked.getAssociationName()%>">
				</tr>
				<%
							i++;
					}
					}
				%>
				<!-- 循环体结束 -->
			</tbody>
			<tbody>
				<tr>
					<td colspan="5">
						<button type="button" class="table-button" id="green" onclick="updateAct('approveActForBox')">同意</button>
						<button type="button" class="table-button" id="red" onclick="updateAct('refuseActForBox')">拒绝</button>
						<input type="hidden" name="method" value="" id="methodMain">
					</td>
				</tr>
			</tbody>
		</table>


		<table class="checked">
			<!-- 表头 -->
			<thead>
				<tr>
					<th colspan="6">已审核报名表</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="ckall" name="ubox" onclick="checkall()" />all</td>
					<td>活动</td><td>报名人</td><td>报名时间</td>
				</tr>
			</thead>
			<tbody class="scrolltbody">
				<!-- 审核申请列表 -->
				<!-- 循环体 -->
				<%
					List<Activity_Apply> checkedAcJoin=(List<Activity_Apply>) request.getAttribute("checkedAcJoin");
					if(checkedAcJoin==null){
					}else{
						for(Activity_Apply checked:checkedAcJoin){
							int i=1;
				%>
				<tr class="establish-apply-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box"  value="<%=checked.getUnique()%>"/></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getTitle()%></p></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getStudentName()%></p></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getTime()%></p></td>
					<input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getStudentNum()%>">
					<input type="hidden" name="<%=checked.getUnique()%>"  value="<%=checked.getTitle()%>">
					<input type="hidden" name="<%=checked.getUnique()%>"  value="<%=checked.getAssociationName()%>">
				</tr>
				<%
							i++;}
					}
				%>
				<!-- 循环体结束 -->
			</tbody>
			<tbody>
				<tr>
					<td colspan="5">
						<button type="button"class="table-button" id="red" onclick="updateAct('deleteActForBox')">删除</button>
					</td>
				</tr>
			</tbody>
		</table>

		<!--弹出层时背景层DIV-->
		<div id="fade" class="black_overlay"></div>
		<div id="details" class="white_content">
			<div>
				<span onclick="CloseDiv('details','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
			</div>
			<table class="details">
				<tr><td></td><td></td></tr>
				<tr>
					<td>报名活动</td>						<td name="titleDiv"></td>
				</tr>
				<tr>
					<td>报名人姓名</td>						<td name="studentNameDiv"></td>
				</tr>
				<tr>
					<td>性别</td>							<td name="sexDiv"></td>
				</tr>
				<tr>
					<td>出生日期</td>						<td name="birthdayDiv"></td>
				</tr>
				<tr>
					<td>学校</td>							<td name="schoolDiv"></td>
				</tr>
				<tr>
					<td>申请原因</td>					<td><textarea disabled="true" cols="50" rows="3" name="reasonDiv"></textarea></td>
				</tr>
				<tr>
					<td>兴趣爱好及特长</td>				<td><textarea disabled="true" cols="50" rows="5" name="hobbyDiv"></textarea></td>
				</tr>
				<tr>
					<td>联系方式</td>						<td  name="contactDiv"></td>
				</tr>
				<tr>
					<td>报名时间</td>						<td name="timeDiv"></td>
				</tr>
				<tr style="border-top: 1px solid #007BD9;">
					<td colspan="3">
						<button type="button" class="table-button" id="green" onclick="updateAct('approveAct')">同意</button>
						<button type="button" class="table-button" id="red" onclick="updateAct('refuseAct')">拒绝</button>
						<input type="hidden" name="studentNum" value="" id="updateInfo1">
						<input type="hidden" name="title" value="" id="updateInfo2">
						<input type="hidden" name="associationName" value="" id="updateInfo3">
					</td>
					<td>
						<button type="button"class="table-button" style="float: right;" onclick="CloseDiv('details','fade')">返回</button>
					</td>
				</tr>
			</table>
		</div>



	<%--<div id="fade" class="black_overlay"></div>--%>
	<div id="details2" class="white_content">
		<div>
			<span onclick="CloseDiv('details2','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
		</div>
		<table class="details">
			<tr><td></td><td></td></tr>
			<tr>
				<td>报名活动</td>						<td name="titleDiv"></td>
			</tr>
			<tr>
				<td>报名人姓名</td>						<td name="studentNameDiv"></td>
			</tr>
			<tr>
				<td>性别</td>							<td name="sexDiv"></td>
			</tr>
			<tr>
				<td>出生日期</td>						<td name="birthdayDiv"></td>
			</tr>
			<tr>
				<td>学校</td>							<td name="schoolDiv"></td>
			</tr>
			<tr>
				<td>申请原因</td>					<td><textarea disabled="true" cols="50" rows="3" name="reasonDiv"></textarea></td>
			</tr>
			<tr>
				<td>兴趣爱好及特长</td>				<td><textarea disabled="true" cols="50" rows="5" name="hobbyDiv"></textarea></td>
			</tr>
			<tr>
				<td>联系方式</td>						<td name="contactDiv"></td>
			</tr>
			<tr>
				<td>报名时间</td>						<td  name="timeDiv"></td>
			</tr>
			<tr style="border-top: 1px solid #007BD9;">
				<td colspan="3">
					<button type="button" class="table-button" id="red" onclick="updateAct('deleteAct')">删除</button>
				</td>
				<td>
					<button type="button" class="table-button" style="float: right;" onclick="CloseDiv('details2','fade')">返回</button>
				</td>
			</tr>
		</table>
	</div>
		</form>
	</div>
	<jsp:include page="/common/comExit.jsp"/>
</body>
</html>