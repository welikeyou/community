<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>可乐网-我的社团</title>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/css/mine.css"/>
	<link rel="stylesheet" type="text/css" href="/css/topside.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table.css"/>
</head>
<body>
	<%--<div class="topside">
		<div class="logo"><img src="/pic/logo_blue.png"></div>
		<div class="sectionlist">
			<button class="section" id="section-header">首页</button>
			<button class="section" id="section-recruit">社团招新</button>
			<button class="section" id="section-activity">社团活动</button>
			<button class="section" id="section-mine">我的社团</button>
			<button class="section" id="section-personal">个人中心</button>
			<div class="head" id="head">
				<img src="/pic/logo.png">
				<p>欢迎你：XXX</p>
			</div>
		</div>
	</div>--%>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>


	</div>
	<form action="/mapages.do" id="estform" method="post">
	<div class="mainbox">
		<div class="content" style="width: 50%;">
			<div class="dropdown">
				<div class="dropdown-head" id="on" onclick="dropdown(this)">
					<div class="triangle"></div>
					<p style="margin: 2px;">我加入的社团</p>
				</div>
				<!-- 加入社团表单 -->
				<table class="list" id="join">
					<!-- 循环体 -->
					 <%
		               @SuppressWarnings("unchecked")
		               HashMap<String,String> map=(HashMap<String,String>)request.getAttribute("personalAssociations");
		               if(map==null){%>
		               <tr><td>没有加入社团</td></tr>
		              <%}else{
	               Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		               while (iterator.hasNext()) {
	               Map.Entry<String, String> entry = iterator.next();
		               String associationNum=(String)entry.getKey();
	               String associationName=(String)entry.getValue();
	              %>
					<tr>
						<td><img src="pic/lemon.png"></td>
						<td><%=associationName %><td>
						<td><button class="table-button" type="button" onclick="suba('<%=associationNum%>','getComMain')">进入主页</button></td>
					</tr>
					     <%	             }  
	              }
	              %>
					<!-- 循环体结束 -->

				</table>
			</div>
			<div class="dropdown">
				<div class="dropdown-head" id="on" onclick="dropdown(this)">
					<div class="triangle"></div>
					<p style="margin: 2px;">我管理的社团</p>
				</div>
				<!-- 管理社团表单 -->
				<table class="list" id="manage">
					<!-- 循环体 -->
					<%
						@SuppressWarnings("unchecked")
						HashMap<String,String> map2=(HashMap<String,String>)request.getAttribute("personalManage");
						if(map2==null){%>
					<tr><td>没有加入社团</td></tr>
					<%}else{
						Iterator<Map.Entry<String, String>> iterator = map2.entrySet().iterator();
						while (iterator.hasNext()) {
							Map.Entry<String, String> entry = iterator.next();
							String associationNum=(String)entry.getKey();
							String associationName=(String)entry.getValue();
					%><tr>
					<td><img src="pic/lemon.png"></td>
					<td><%=associationName %><td>
					<td><button class="table-button" onclick="suba('<%=associationNum%>','getComMain')">进入主页</button></td>
				</tr>
					<%	             }
					}
					%>
					<!-- 循环体结束 -->
				</table>

			</div>
			<div class="dropdown">
				<div class="dropdown-head" id="on" onclick="dropdown(this)">
					<div class="triangle"></div>
					<p style="margin: 2px;">我创建的社团</p>
				</div>
				<!-- 创建社团表单 -->
				<table class="list" id="establish">
					<%
						@SuppressWarnings("unchecked")
						HashMap<String,String> map3=(HashMap<String,String>)request.getSession().getAttribute("personalCreate");
						if(map3==null){%>
					<tr><td>没有加入社团</td></tr>
					<%}else{
						Iterator<Map.Entry<String, String>> iterator = map3.entrySet().iterator();
						while (iterator.hasNext()) {
							Map.Entry<String, String> entry = iterator.next();
							String associationNum=(String)entry.getKey();
							String associationName=(String)entry.getValue();
					%>
					<!-- 循环体 -->
					<tr>
						<td><img src="pic/lemon.png"></td>
						<td><%=associationName %><td>
						<td><button class="table-button" onclick="suba('<%=associationNum%>','getComMain')">进入主页</button></td>
					</tr>
					<%	             }
					}
					%>
					<tr style="border: none;" class="none-hover-tr">
						<td colspan="1" style="padding-top: 5px;border-top:1px #007bd8 solid;"><button class="table-button" type="button" onclick="sub('estCom')" id="green"style="float:left">创建社团</button></td>
					</tr>
				</table>
			</div>
		</div>
		<input type="hidden" name="method" value="" id = "method">
		<input type="hidden" name="comID" value="" id="comID">
		<script type="text/javascript">
            function suba(id,method) {
                document.getElementById("comID").value = id;
                document.getElementById("method").value = method;
                document.getElementById("estform").submit();
            }
            function sub(method) {
                document.getElementById("method").value = method;
                document.getElementById("estform").submit();
            }
			function dropdown(element) {
				if (element.id == 'off') {
					element.parentElement.children[1].style.display = "block";
					element.children[0].style.transform= "rotate(180deg)";
					element.children[0].style.animation= "rotate-to 0.3s ease-out forwards";
					element.id="on";
				}
				else {
					element.parentElement.children[1].style.display = "none";
					element.children[0].style.transform= "rotate(0deg)";
					element.children[0].style.animation= "rotate-back 0.3s ease-out forwards";
					element.id="off";
				}
			}
		</script>
	</div>
	</form>
</body>
</html>