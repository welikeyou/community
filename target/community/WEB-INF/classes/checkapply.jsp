<%@ page import="com.java.model.Join_Apply" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8"
%>



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

	<script type="text/javascript" src="/js/checkapply2.js"></script>
	<script src="/js/prefixfree.min.js"></script>
</head>
<body>
<!--弹出层时背景层DIV-->
<div id="fade" class="black_overlay"></div>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
<jsp:include page="/common/comTittle.jsp"/>
<form action="/Upadate_Join.do" method="post" id="mainForm">
<div class="mainbox">

			<table class="uncheck">
				<!-- 表头 -->
				<thead>
				<tr>
					<th colspan="6">未审核入团申请</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="uckall" name="ubox" onclick="ucheckall()" />all</td>
					<td>申请人学号</td><td>申请人</td><td>申请时间</td>
				</tr>
				</thead>
				<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%
					List<Join_Apply> uncheckedJoin=(List<Join_Apply>) request.getAttribute("unchecked");
					if(uncheckedJoin==null){
					}else{
						for(Join_Apply unchecked:uncheckedJoin){
							int i=1;
							System.out.println(unchecked.getResult());
				%>
				<tr class="establish-apply-uck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box1" value="<%=unchecked.getUnique()%>"/></td>
					<td onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentNum()%></td>
					<td onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentName()%></td>
					<td onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getTime()%></td>
					<input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getStudentNum()%>">
					<input type="hidden" name="<%=unchecked.getUnique()%>"  value="<%=unchecked.getAssociationNum()%>">
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
						<button type="button" class="table-button" id="green" onclick="update('approveJoinForBox')">同意</button>
						<button type="button" class="table-button" id="red" onclick="update('refuseJoinForBox')">拒绝</button>
						<input type="hidden" name="method" value="" id="methodMain">
					</td>
				</tr>
				</tbody>
			</table>

			<table class="checked">
				<!-- 表头 -->
				<thead>
				<tr>
					<th colspan="6">已审核入团申请</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="ckall" name="ubox" onclick="checkall()" />all</td>
					<td>申请人学号</td><td>申请人</td><td>申请时间</td>
				</tr>
				</thead>
				<tbody class="scrolltbody">
				<!-- 已审核申请列表 -->
				<%
					List<Join_Apply> checkedJoin=(List<Join_Apply>) request.getAttribute("checked");
					if(checkedJoin==null){
					}else{
						for(Join_Apply checked:checkedJoin){
							int i=1;
				%>
				<!-- 循环体 -->
				<tr class="establish-apply-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box2" value="<%=checked.getUnique()%>"/><%=i%></td>
					<td onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getStudentNum()%></td>
					<td onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getStudentName()%></td>
					<td onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getTime()%></td>
					<input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getStudentNum()%>">
					<input type="hidden" name="<%=checked.getUnique()%>"  value="<%=checked.getAssociationNum()%>">
				</tr>
				<!-- 循环体结束 -->
				<%
							i++;}
					}
				%>
				</tbody>
				<tbody>
				<tr>
					<td colspan="5">
						<button type="button" class="table-button" id="red" onclick="update('deleteJoinForBox')">删除</button>
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
			<td>申请编号</td>						<td name="studentNumDiv"></td>
		</tr>
		<tr>
			<td>申请人</td>							<td name="studentNameDiv"></td>
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
			<td>已加入的社团</td>						<td name="associationsDiv"></td>
		</tr>
		<tr>
			<td>想加入的部门</td>				<td name="departmentDiv"></td>
		</tr>
		<tr>
			<td>申请原因</td>					<td><textarea disabled="true" cols="50" rows="3" style="border:0;background-color:white;" name="reasonDiv"></textarea></td>
		</tr>
		<tr>
			<td>兴趣爱好及特长</td>				<td><textarea disabled="true" cols="50" rows="5" style="border:0;background-color:white;" name="hobbyDiv"></textarea></td>
		</tr>
		<tr>
			<td>联系方式</td>						<td name="contactDiv"></td>
		</tr>
		<tr>
			<td>申请时间</td>						<td name="timeDiv"></td>
		</tr>
		<tr style="border-top: 1px solid #007BD9;">
			<td colspan="3">
				<button  type="button" class="table-button" id="green" onclick="update('approveJoin')">同意</button>
				<button  type="button" class="table-button" id="red" onclick="update('refuseJoin')">拒绝</button>
				<input type="hidden" name="studentNum" value="" id="updateInfo1">
				<input type="hidden" name="associationNum" value="" id="updateInfo2">
			</td>
			<td>
				<button  type="button" class="table-button" style="float: right;" onclick="CloseDiv('details','fade')">返回</button>
			</td>
		</tr>
	</table>
</div>



<!--弹出层时背景层DIV-->
		<div id="details2" class="white_content">
			<div>
				<span onclick="CloseDiv('details2','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
			</div>
			<table class="details">
				<tr><td></td><td></td></tr>
				<tr>
					<td>申请编号</td>						<td name="studentNumDiv"></td>
				</tr>
				<tr>
					<td>申请人</td>							<td name="studentNameDiv"></td>
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
					<td>已加入的社团</td>						<td name="associationsDiv"></td>
				</tr>
				<tr>
					<td>想加入的部门</td>				<td name="departmentDiv"></td>
				</tr>
				<tr>
					<td>申请原因</td>					<td><textarea disabled="true" cols="50" rows="3" style="border:0;background-color:white;" name="reasonDiv"></textarea></td>
				</tr>
				<tr>
					<td>兴趣爱好及特长</td>				<td><textarea disabled="true" cols="50" rows="5" style="border:0;background-color:white;" name="hobbyDiv"></textarea></td>
				</tr>
				<tr>
					<td>联系方式</td>						<td name="contactDiv"></td>
				</tr>
				<tr>
					<td>申请时间</td>						<td name="timeDiv"></td>
				</tr>
				<tr style="border-top: 1px solid #007BD9;">
					<td colspan="3">
						<button type="button"class="table-button" id="red" onclick="update('deleteJoin')">删除</button>
					</td>
					<td>
						<button type="button"class="table-button" style="float: right;" onclick="CloseDiv('details2','fade')">返回</button>
					</td>
				</tr>
			</table>
		</div>
</div></form>
<jsp:include page="/common/comExit.jsp"/>
</body>
</html>