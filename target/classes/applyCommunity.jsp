<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-申请加入社团</title>
	<link rel="stylesheet" href="/css/topside.css" type="text/css"/>
	<link rel="stylesheet" href="/css/table.css" type="text/css"/>
	<script type="text/javascript" src="/js/apply.js"></script>
</head>
<body>
	<%--<div class="topside">
		<div class="logo"><img src="pic/logo_blue.png"></div>
		<div class="sectionlist">
			<button class="section" name="section-header">首页</button>
			<button class="section" name="section-recruit">社团招新</button>
			<button class="section" name="section-activity">社团活动</button>
			<button class="section" name="section-mine">我的社团</button>
			<button class="section" name="section-personal">个人中心</button>
			<div class="head" id="head">
				<img src="pic/logo.png">
				<p>欢迎你：XXX</p>
			</div>
		</div>
	</div>--%>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>
	<form action="AddCommunity.do" method="post" id="form">
	<div class="mainbox">
		<table>
			<tr><th colspan="3">加入社团申请表</th></tr>
			<tr>
				<td>姓名</td>
				<td><input type="text" style="border:0;background-color:white"disabled="false" value="${loginStudent.getName()}"/></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="text"style="border:0;background-color:white"disabled="false" value="${loginStudent.getSex()}"/></td>
			</tr>
			<tr>
				<td>出生年月日</td>
				<td><input type="text"style="border:0;background-color:white"disabled="false" value="${loginStudent.getBirthday()}"/></td>
			</tr>
			<tr>
				<td>学校</td>
				<td><input type="text"style="border:0;background-color:white"disabled="false" value="${loginStudent.getSchool()}"/></td></tr>
			<tr>
				<td>已加入的社团</td>
			</tr>
			<tr>
				<td colspan="2"><textarea clos="20" rows="2"style="border:0;background-color:white"disabled="false">
					<%
						HashMap<String,String> map=(HashMap<String,String>)request.getAttribute("personalAssociations");
						if(map != null) {
							Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
							while (iterator.hasNext()) {
								Map.Entry<String, String> entry = iterator.next();
								String associationName=(String)entry.getValue();
								%>
					<%=associationName%>
					<<br><<br>
								<%
							}
						}
					%>
				</textarea></td>
			</tr>
			<tr>
				<td>想加入的部门</td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="department" id = "department"/></td>
				<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
			</tr>
				<td>兴趣爱好及特长</td>
			</tr>
			<tr>
				<td colspan="2"><textarea clos="20" rows="3" warp="virtual" name = "hobby" id = "hobby"></textarea></td>
			</tr>
			<tr>
				<td>申请的原因</td>
			</tr>
			<tr>
				<td colspan="2"><textarea clos="20" rows="3" warp="virtual" name="reason" id = "reason"></textarea></td>
				<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
			</tr>
			<tr>
				<td>联系方式</td>

			</tr>
			<tr>
				<td colspan="2"><input type="text" name = "contact" value="${loginStudent.getContact()}" id = "contact"/></td>
				<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left: 20px;">
					<button class="table-button" id="cancel" style="float:right" type="button" onclick="back()">取消</button>
					<button class="table-button" id="green" style="float:right" type="button" onclick="check('addCommunity')">提交</button>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="method" value="" id="method">
	<script>
        function check(method) {
            var department = document.getElementById("department").value;
            var hobby = document.getElementById("hobby").value;
            var reason = document.getElementById("reason").value;
            var contact = document.getElementById("contact").value;
            if(0 == department.length) {
                alert("请填写部门")
                return;
            }else if(0 == hobby.length)
			{
			    alert("请填写兴趣爱好")
				return;
			}else if(0 == reason.length)
			{
			    alert("请填写理由")
				return;
			}else if(0 == contact.length)
			{
			    alert("请填写联系方式")
				return;
			}
            document.getElementById("method").value = method;
            document.getElementById("form").submit();
        }
        function back() {

            history.go(-1);
        }
	</script>
	</form>
</body>
</html>