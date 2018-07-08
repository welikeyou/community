<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--
功能:申请加入社团
创建者:陆祎洲,董淑媛
修改者:黄友明
--%>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/AddCommunity/applyCommunity.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset='utf-8'" />
	   <%-- <script type="text/javascript" src="JS/申请.js"></script>--%>
	</head>
	 <body>
	 <jsp:include page="/common/AfterLoginTittle.jsp"/>
	 <form action="AddCommunity.do" method="post" id="form">
	 <%--<div class="mainbox">
	 <div class="topside">
			<div class="logo"><img src="pic/slogen.png"></div>
			<div class="sectionlist">
				<button class="section" name="section-header">首页</button>
				<button class="section" name="section-recruit">社团招新</button>
				<button class="section" name="section-activity">社团活动</button>
				<button class="section" name="section-mine">我的社团</button>
				<button class="section" name="section-personal">个人中心</button>
			</div>
			<div class="head" name="head">
				<img src="pic/logo.png">
				<p>欢迎你：XXX</p>
			</div>
		</div>--%>

	 <table border="0" style="position: absolute; left: 30%;  top: 25%;">
	 <tr>
	 <td>姓名</td>
	 <td><input type="text" style="height:20px;width:150px;" name = "stuName" id = "stuName"  /></td></tr>
	 <tr>
	 <td>性别</td>
	 <td><input type="text" style="height:20px;width:150px;" name="stuSex"  id="stuSex"/></td></tr>
	 <tr>
	 <td>出生年月日</td>
	 <td><input type="text" style="height:20px;width:150px;" name = "stuBir"/></td></tr>
	 <tr>
	 <td>学校</td>
	 <td><input type="text" style="height:20px;width:150px;" name = "stuSch"/></td></tr>
	 <tr>
	 <td>已加入的社团</td>
	 <td><input type="text" style="height:20px;width:150px;" name  = "stuCo"/></td></tr>
	 <tr>
	 <td>兴趣爱好及特长</td>
	 <td><textarea type="text" clos="20" rows="3" warp="virtual"style="width:149px;" name="stuHo"></textarea></td></tr>
	 <tr>
	 <td>申请的原因</td>
	 <td><textarea type="text" clos="20" rows="3" warp="virtual"style="width:149px;" name = "stuRe" id = "stuRe" ></textarea></td></tr>
	 <tr>
	 <td>联系方式</td>
	 <td><input type="text" style="height:20px;width:150px;"  name = "stuPh"/></td></tr>
	 <tr>
	 <td><button class="table-button" type="button" style="position: absolute; left: 30%; " onclick="check('addCommunity')">提交</button></td>
	 <td><button class="table-button" type="button" style="position: absolute; left: 60%; "onclick="back()" >取消</button></td>
	 </tr>
	 </table>
	 </div>
		 <input type="hidden" name="method" value="" id="method">
		 <script>
			 function check(method) {
                 var name = document.getElementById("stuName").value;
                 var reason = document.getElementById("stuRe").value;


                 if (0 == name.length ) {
                     alert("填名字啊,蠢货 ");
                     return;
                 }
                 if (0 == reason.length ) {

                 alert("没理由,谁理你啊")
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