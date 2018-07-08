<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
功能:申请创建社团
创建者:陆祎洲,董淑媛
修改者:黄友明
--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>可乐网-申请创建社团</title>
		<link rel="stylesheet" href="/common/topside.css" type="text/css"/>
		<link rel="stylesheet" href="/myCommunities/establish.css" type="text/css"/>

	</head>
	 <body>
	 <jsp:include page="/common/AfterLoginTittle.jsp"/>
	 <form action="/mapages.do" id="subform" method="post">
		 <input type="hidden" name="method" value="AplEstCom">
	 <table border="0" style="position: absolute; left: 30%;  top: 25%;">
		 <tr>
			 <td>账号</td>
			 <td><input disabled = "true" type="text" style="height:20px;width:150px;" name = "stuId" value=${loginStudent.getId()}  /></td></tr>
	 <tr>
	 <td>姓名</td>
	 <td><input disabled = "true" type="text" style="height:20px;width:150px;" name = "stuName" value=${loginStudent.getName()} /></td></tr>
	 <tr>
	 <td>性别</td>
	 <td><input disabled = "true" type="text" style="height:20px;width:150px;"   value=${loginStudent.getSex()} /></td></tr>
	 <tr>
	 <td>社团类型</td>
	 <td><input type="text" style="height:20px;width:150px;" name = "applyType" id="applyType"/></td></tr>
	 <tr>
	 <td>社团名称</td>
	 <td><input type="text" style="height:20px;width:150px;" name ="applyName" id = "applyName"/></td></tr>
	 <tr>
	 <td>申请原因</td>
	 <td><textarea type="txt" clos="20" rows="3" warp="virtual" style="width:149px;" name="applyReason" id = "applyReason"></textarea></td></tr>
	 <tr>
	 <td>社团未来发展的规划</td>
	 <td><textarea type="txt" clos="20" rows="3" warp="virtual" style="width:149px;" name="applyPlan" id = "applyPlan"></textarea></td></tr>
	 <tr>
	 <td>你所具备的优势</td>
	 <td><textarea type="txt" clos="20" rows="3" warp="virtual" style="width:149px;"name="advantages" id="advantages"></textarea></td></tr>
	 <tr>
	 <td>联系方式</td>
	 <td><input type="text" style="height:20px;width:150px;" value = ${loginStudent.getContact()} /></td></tr>
	 <tr>
	 <td><button class="table-button"  style="position: absolute; left: 30%; " type="button" onclick="sub()">提交</button></td>
	 <td><button class="table-button"  style="position: absolute; left: 60%; " type="button">取消</button></td>
	 </tr>
	 </table>
		 <script>
			 function Tomain() {
				 Location.href = "/mainpages/AfterLogin.jsp";
             }
             function sub() {
			     var applyName   = document.getElementById("applyName").value;
                 var applyReason = document.getElementById("applyReason").value;
                 var applyPlan   = document.getElementById("applyPlan").value;
                 var advantages  = document.getElementById("advantages").value;
                 var applyType   = document.getElementById("applyType").value;
                 if(0 == applyName.length)
				 {
				     alert("请输入社团名称");
				     return;
				 }
                 if(0 == applyReason.length)
                 {
                     alert("请输入申请原因");
                     return;
                 }
                 if(0 == applyPlan.length)
                 {
                     alert("请输入社团规划");
                     return;
                 }
                 if(0 == advantages.length)
                 {
                     alert("请输入您的优势");
                     return;
                 }
                 if(0 == applyType.length)
                 {
                     alert("请输入社团类型");
                     return;
                 }
                 document.getElementById("subform").submit();
             }
		 </script>
	 </form>
	 </div>
	 </body>
</html>