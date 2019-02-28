<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>		
<%@page import="java.util.List"%>
<%@ page import="com.java.model.Create_Apply" %>
<%@ page isELIgnored="false" %>		
<%--<%@ page import="com.java.model.Create_Apply" %>	--%>



<!DOCTYPE html>
<html>
<head>
	<%
	String path = request.getRequestURI();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;		       
	%>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>可乐网-审核</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" href="/css/root2.css" type="text/css"/>
	<link rel="stylesheet" href="/css/topside2.css" type="text/css"/>
	<script type="text/javascript" src="/js/root2.js"></script>
</head>
<body>
	<%--弹出层时背景层DIV-->
	&lt;%&ndash;<div id="fade" class="black_overlay"></div>
	<div class="topside">
		<div class="logo"><img src="/pic/logo_blue.png"></div>
		<div class="sectionlist">
			<div class="head" id="head">
				<img src="/pic/logo.png">
				<p>欢迎你：XXX</p>
			</div>
		</div>--%>
		 <jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>

	<div class="mainbox">
		<form action="/update.do" method="post" id="form" name="form">
			<table class="uncheck">
			<!-- 表头 -->
			<thead>
				<tr>
					<th colspan="6">未审核建团申请</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="uckall" name="ubox" onclick="ucheckall()" />all</td>
					<td>申请人编号</td><td>申请人</td><td>社团名称</td><td>申请时间</td>
				</tr>
			</thead>
			<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
		   <%
		List<Create_Apply> uncheckedAssociations=(List<Create_Apply>)request.getSession().getAttribute("unchecked");
		if(uncheckedAssociations==null){
		    
		}else{
		int i=1;
		for(Create_Apply unchecked:uncheckedAssociations){
		 System.out.println("111:"+unchecked.getResult());
		//request.setAttribute("unchecked",unchecked);
		%>
				<tr class="establish-apply-uck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box1" value="<%=unchecked.getUnique()%>"/><%=i%></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentNum()%></p></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentName()%></p></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getAssociationName()%></p></td>
					<td><p onclick="ShowDiv('details','fade',<%=unchecked.getResult()%>)"><%=unchecked.getTime()%></p></td>

					<!--获取被选中checkBox的studentNum和associationName -->
		         <input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getStudentNum()%>" />
		         <input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getAssociationName()%>"/>
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
						<button type="button"class="table-button" id="green" onclick="view('approveForBox')" >同意</button>
						<button type="button" class="table-button" id="red" onclick="view('refuseForBox')" >拒绝</button>
					</td>
				</tr>
				<input type="hidden" name="method" value="" id="method">
				<input type="hidden" name="studentNum" value="" id="updateState1">
				<input type="hidden" name="associationName" value="" id="updateState2">
			</tbody>
		</table>

		<table class="checked">
			<!-- 表头 -->
			<thead>
				<tr>
					<th colspan="6">已审核建团申请</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="ckall" name="ubox" onclick="checkall()" />all</td>
					<td>学生编号</td><td>申请人</td><td>社团名</td><td>申请时间</td>
				</tr>
			</thead>
			<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%
					List<Create_Apply> checkedAssociations=(List<Create_Apply>)request.getSession().getAttribute("checked");
					if(checkedAssociations==null){ }else{
						for(Create_Apply checked:checkedAssociations){
							System.out.println("result:"+checked.getResult());
				%>
				<tr class="establish-apply-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box2" value="<%=checked.getUnique()%>" /></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getStudentNum()%></p></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getStudentName()%></p></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getAssociationName()%></p></td>
					<td><p onclick="ShowDiv('details2','fade',<%=checked.getResult()%>)"><%=checked.getTime()%></p></td>
					<input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getStudentNum()%>" />
					<input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getAssociationName()%>"/>
				</tr>
				<%
						}
					}
				%>
				<!-- 循环体结束 -->
			</tbody>
			<tbody>
				<tr>
					<td colspan="5">
						<button class="table-button" id="red" type="button" onclick="view('deleteForBox')">删除</button>
					</td>
				</tr>
			</tbody>
		</table>

		<!-- onclick="ShowDiv('MyDiv2','fade')" -->
		<!--弹出层时背景层DIV-->
		<div id="fade" class="black_overlay"></div>
		<div id="details" class="white_content">
			<div>
				<span onclick="CloseDiv('details','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
			</div>
			<table class="details">
				<tr>
					<td>社团名</td>						<td name="associationNameDiv"></td>
					<td colspan="2" class="former">初始成员及简介</td>				
				</tr>
				<tr>
					<td>申请人编号</td>							<td name="studentNumDiv"></td>
					<td name="member1Div" >1.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro1Div"></textarea></td>
				</tr>
				<tr>
					<td>申请人</td>							<td name="studentNameDiv"></td>
				</tr>
				<tr>
					<td>性别</td>							<td name="sexDiv"></td>
					<td name="member2Div">2.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro2Div"></textarea></td>
				</tr>
				<tr>
					<td>出生年月日</td>						<td name="birthdayDiv"></td>
				</tr>
				<tr>
					<td>学校</td>							<td name="schoolDiv">武汉大学</td>
					<td name="member3Div">3.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro3Div"></textarea></td>
				</tr>
				<tr>
					<td>申请创建的原因</td>					<td><textarea disabled="true" cols="50" rows="3"style="background-color:white;border:0"  name="aimDiv"></textarea></td>
				</tr>
				<tr>
					<td>社团未来发展的规划</td>				<td><textarea disabled="true" cols="50" rows="5"style="background-color:white;border:0" name="planDiv"></textarea></td>
					<td name="member4Div">4.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro4Div"></textarea></td>
				</tr>
				<tr>
					<td>联系方式</td>						<td rowspan="2" name="contactDiv"></td>
				</tr>
				<tr>
					<td></td>								
					<td name="member5Div">5.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0" disabled="true" cols="50" rows="5" name="briefintro5Div"></textarea></td>
				</tr>
				<tr>
					<td>申请时间</td>						<td name="timeDiv"></td>
				</tr>
				<tr style="border-top: 1px solid #007BD9;">
					<td colspan="3">
						<button type="button" class="table-button" id="green" onclick=view('refuse')>同意</button>
						<button type="button" class="table-button" id="red" onclick=view('approve')>拒绝</button>
					</td>
					<td>
						<button type="button" class="table-button" style="float: right;" onclick="CloseDiv('details','fade')">返回</button>
					</td>
				</tr>
			</table>
		</div>

			<div id="details2" class="white_content">
				<div>
					<span onclick="CloseDiv('details2','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
				</div>
				<table class="details">
					<tr>
						<td>社团名</td>						<td name="associationNameDiv"></td>
						<td colspan="2" class="former">初始成员及简介</td>
					</tr>
					<tr>
						<td>申请人编号</td>							<td name="studentNumDiv"></td>
						<td name="member1Div" >1.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro1Div"></textarea></td>
					</tr>
					<tr>
						<td>申请人</td>							<td name="studentNameDiv"></td>
					</tr>
					<tr>
						<td>性别</td>							<td name="sexDiv"></td>
						<td name="member2Div">2.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro2Div"></textarea></td>
					</tr>
					<tr>
						<td>出生年月日</td>						<td name="birthdayDiv"></td>
					</tr>
					<tr>
						<td>学校</td>							<td name="schoolDiv">武汉大学</td>
						<td name="member3Div">3.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro3Div"></textarea></td>
					</tr>
					<tr>
						<td>申请创建的原因</td>					<td><textarea disabled="true" cols="50" rows="3"style="background-color:white;border:0"  name="aimDiv"></textarea></td>
					</tr>
					<tr>
						<td>社团未来发展的规划</td>				<td><textarea disabled="true" cols="50" rows="5"style="background-color:white;border:0" name="planDiv"></textarea></td>
						<td name="member4Div">4.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0"disabled="true" cols="50" rows="5" name="briefintro4Div"></textarea></td>
					</tr>
					<tr>
						<td>联系方式</td>						<td rowspan="2" name="contactDiv"></td>
					</tr>
					<tr>
						<td></td>
						<td name="member5Div">5.<!-- 填写初始成员姓名 --></td>		<td rowspan="2"><!-- 填写成员简介 --><textarea style="background-color:white;border:0" disabled="true" cols="50" rows="5" name="briefintro5Div"></textarea></td>
					</tr>
					<tr>
						<td>申请时间</td>						<td name="timeDiv"></td>
					</tr>
					<tr style="border-top: 1px solid #007BD9;">
						<td colspan="3">
							<button type="button"class="table-button" id="red" onclick=view('delete')>删除</button>
						</td>
						<td>
							<button type="button"class="table-button" style="float: right;" onclick="CloseDiv('details2','fade')">返回</button>
						</td>
					</tr>
				</table>
			</div>

		</form>
	</div>
</body>
</html>