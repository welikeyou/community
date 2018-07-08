<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.java.model.Create_Apply" %>
<%--
功能:审核申请创建社团的信息
创建者:陆祎洲,董淑媛
修改者:陆祎洲,董淑媛
--%>

<!DOCTYPE html>
<html>
	<head>

		<%
			String path = request.getRequestURI();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
		%>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/mainpages/root.css" type="text/css"/>
	    <script type="text/javascript" src="/mainpages/root.js"></script>
	</head>
	 <body >

	 	 <jsp:include page="/common/AfterLoginTittle.jsp"/>

<form action="/update.do" method="post" id="form" name="form">
<table border="0" class="uncheck">
<tr><td style="float:left">未审核</td><td></td><td></td><td ></td><td ></td><tr>
<tr>
<td><input type="checkbox" id="ckall1" onclick="checkall()" />all</td>
<td></td><td></td><td ></td><td ></td>
</tr>

<%
    List<Create_Apply> uncheckedAssociations=(List<Create_Apply>)request.getSession().getAttribute("unchecked");
    if(uncheckedAssociations==null){
    	
    }else{
    int i=1;
    for(Create_Apply unchecked:uncheckedAssociations){
       /* System.out.println(unchecked.getResult());*/
      //request.setAttribute("unchecked",unchecked);

 %>
<tr >
<td><input type="checkbox" name="box1" value="<%=unchecked.getUnique()%>"/><%=i%></td>
	<td id="applynum"><p onclick="ShowDiv('MyDiv','fade',<%=unchecked.getResult()%>)"><%=unchecked.getAssociationName()%><</p></td>
	<td id="association"><p onclick="ShowDiv('MyDiv','fade',<%=unchecked.getResult()%>)"><%=unchecked.getStudentName()%></p></td>
	<td id="applyname"><p onclick="ShowDiv('MyDiv','fade',<%=unchecked.getResult()%>)"><%=unchecked.getType()%></p></td>
	<td id="applydate"><p onclick="ShowDiv('MyDiv','fade',<%=unchecked.getResult()%>)"><%=unchecked.getTime()%></p></td>
<%--
<td id="applynum"><p ><%=unchecked.getAssociationName()%></p></td>
<td id="association"><p ><%=unchecked.getStudentName()%></p></td>
<td id="applyname"><p ><%=unchecked.getType()%></p></td>
<td id="applydate"><p><%=unchecked.getTime()%></p></td>                                                                                                &lt;%&ndash; name,stuName,num,type,slogan_in,slogan_out,time,contact,advantage,plan,reason&ndash;%&gt;
--%>
	<%--<td ><button type="button"onclick="ShowDiv('MyDiv','fade','<%=unchecked.getAssociationName()%>','<%=unchecked.getStudentName()%>','<%=unchecked.getStudentNum()%>','<%=unchecked.getType()%>','<%=unchecked.getSlogan_in()%>','<%=unchecked.getSlogan_out()%>','<%=unchecked.getTime()%>','<%=unchecked.getContact()%>','<%=unchecked.getAdvantage()%>','<%=unchecked.getPlan()%>','<%=unchecked.getReason()%>')">查看详情</button></td>
	<!--获取被选中checkBox的studentNum和associationName  -->--%>
	<input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getStudentNum()%>" />
	<input type="hidden" name="<%=unchecked.getUnique()%>" value="<%=unchecked.getAssociationName()%>"/>
</tr>
<%    	
   i++;}
      }
%>
<tr  class="button3">
<td></td><td></td><td></td><td></td>
<td>
<button type="button" style="float:right;" onclick="view('refuseForBox')" >拒绝</button>
<button type="button"style="float:right;margin-right:10px" onclick="view('approveForBox')">同意</button>
</td></tr>

</table>
<!--弹出层时背景层DIV-->
<div id="fade" class="black_overlay">
</div>
 <div id="MyDiv" class="white_content">
  <div style="text-align: right; cursor: default; height: 40px;">
  <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('MyDiv','fade')">关闭</span>
  </div>
  <table border="0" style="position: absolute; left: 0;  top: 5%;width:80%">
 
     <tr><td class="former">社团名</td>				<td class="later" name="associationNameDiv"> </td></tr>
     <tr><td class="former">申请人学号</td>			<td class="later"  name="studentNumDiv">      </td></tr>
	 <tr><td class="former">申请人</td>				<td class="later"  name="studentNameDiv">     </td></tr>
	 <tr><td class="former">类型</td>					<td class="later"  name="typeDiv" ></td></tr>
	 <tr><td class="former">对内宣言</td>				<td class="later"  name="slogan_inDiv"></td></tr>
	 <tr><td class="former">对外宣言</td>				<td class="later"  name="slogan_outDiv"></td></tr>
	 <tr><td class="former">申请创建的原因</td>		<td class="later"  name="reasonDiv"></td></tr>
	 <tr><td class="former">社团未来发展的规划</td>	<td class="later"  name="planDiv"></td></tr>
	 <tr><td class="former">你所具备的优势</td>		<td class="later"  name="advantageDiv"></td></tr>
	 <tr><td class="former">联系方式</td>				<td class="later"  name="contactDiv"></td></tr>
	 <tr><td class="former">申请时间</td>				<td class="later"  name="timeDiv"></td></tr>
	 <tr  class="button3">
		<td>
		</td>
		<td>
		 <button type="button" style="float:right;" onclick="CloseDiv('MyDiv','fade')">取消</button>&nbsp;&nbsp;&nbsp;
	     <button type="button" style="float:right;" onclick=view('refuse')>拒绝</button>&nbsp;&nbsp;&nbsp;
		 <button type="button"style="float:right;" onclick=view('approve')>同意</button>
		</td>
	</tr>
<input type="hidden" name="method" value="" id="method">
<input type="hidden" name="studentNum" value="" id="updateState1">
<input type="hidden" name="associationName" value="" id="updateState2">

	 </table>
 </div>



<table border="0" class="checked">
<tr><td style="float:left">已审核</td><td></td><td></td><td ></td><td ></td><tr>
<td><input type="checkbox" id="ckall2" onclick="checkall()" />all</td>
<td></td><td></td><td ></td><td ></td>
</tr>
	<%
		List<Create_Apply> checkedAssociations=(List<Create_Apply>)request.getSession().getAttribute("checked");
		if(uncheckedAssociations==null){ }else{
		for(Create_Apply checked:checkedAssociations){
		   // System.out.println("result:"+checked.getResult());
	%>
<tr >
<td><input type="checkbox" name="box2" value="<%=checked.getUnique()%>" /></td>
	<td id="applynum"><p onclick="ShowDiv('MyDiv2','fade',<%=checked.getResult()%>)"><%=checked.getAssociationName()%></p></td>
	<td id="association"><p onclick="ShowDiv('MyDiv2','fade',<%=checked.getResult()%>)"><%=checked.getStudentName()%></p></td>
	<td id="applyname"><p onclick="ShowDiv('MyDiv2','fade',<%=checked.getResult()%>)"><%=checked.getTime()%></p></td>
	<td id="applydate"><p onclick="ShowDiv('MyDiv2','fade',<%=checked.getResult()%>)"><%=checked.getState()%></p></td>

	<%--<td id="applynum"><p > <%=checked.getAssociationName()%></p></td>
	<td id="association"><p )><%=checked.getStudentName()%></p></td>
	<td id="applyname"><p )><%=checked.getTime()%></p></td>
	<td id="applydate"><p )><%=checked.getState()%></p></td>
	<td ><button type="button"onclick="ShowDiv('MyDiv','fade','<%=checked.getAssociationName()%>','<%=checked.getStudentName()%>','<%=checked.getStudentNum()%>','<%=checked.getType()%>','<%=checked.getSlogan_in()%>','<%=checked.getSlogan_out()%>','<%=checked.getTime()%>','<%=checked.getContact()%>','<%=checked.getAdvantage()%>','<%=checked.getPlan()%>','<%=checked.getReason()%>')">查看详情</button></td>
	--%><input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getStudentNum()%>" />
	<input type="hidden" name="<%=checked.getUnique()%>" value="<%=checked.getAssociationName()%>"/>
</tr>
	<%
		}
		}
	%>
<tr  class="button3">
<td></td><td></td><td></td><td></td>
<td>
<button type="button" onclick="view('deleteForBox')" style="float:right;">删除</button>
</td></tr>
</table>
<!--弹出层时背景层DIV-->
<div id="fade" class="black_overlay">
</div>
 <div id="MyDiv2" class="white_content">
  <div style="text-align: right; cursor: default; height: 40px;">
  <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('MyDiv2','fade')">关闭</span>
  </div>
  <table border="0" style="position: absolute; left: 0;  top: 5%;width:80%">

	  <tr><td class="former">社团名</td>							<td class="later" name="associationNameDiv"></td></tr>
	  <tr><td class="former">申请人学号</td>								<td class="later" name="studentNumDiv"></td></tr>
	  <tr><td class="former">申请人</td>								<td class="later" name="studentNameDiv"></td></tr>
	  <tr><td class="former">类型</td>									<td class="later" name="typeDiv" ></td></tr>
	  <tr><td class="former">对内宣言</td>						<td class="later" name="slogan_inDiv"></td></tr>
	  <tr><td class="former">对外宣言</td>									<td class="later" name="slogan_outDiv"></td></tr>
	  <tr><td class="former">申请创建的原因</td>			<td class="later" name="reasonDiv"></td></tr>
	  <tr><td class="former">社团未来发展的规划</td>	<td class="later" name="planDiv"></td></tr>
	  <tr><td class="former">你所具备的优势</td>			<td class="later" name="advantageDiv"></td></tr>
	  <tr><td class="former">联系方式</td>							<td class="later" name="contactDiv"></td></tr>
	  <tr><td class="former">申请时间</td>							<td class="later" name="timeDiv"></td></tr>
	 <tr  class="button3">
		<td>
		</td>
		<td>
		 <button type="button" style="float:right;" onclick="CloseDiv('MyDiv2','fade')">取消</button>&nbsp;&nbsp;&nbsp;
	     <button type="button" style="float:right;" onclick="view('delete')">删除</button>&nbsp;&nbsp;&nbsp;
		 <button type="button"style="float:right;">确认</button>
		</td>
	</tr>
	 </table>
 </div>
	 </div>

	 </div> 
	 </form>
	 </body>
</html>