<%@ page import="com.java.model.Community" %>
<%@ page import="jdk.nashorn.internal.objects.NativeJSON" %>
<!DOCTYPE html>
<%--
功能:查看各个正在招新的社团
创建者:陆祎洲,董淑媛
修改者:黄友明
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<%
		String path = request.getRequestURI();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path;
	%>
	<base href="<%=basePath%>">

	<link rel="icon" href="/pic/logo.png" type="image/x-icon"/>
	<title>可乐网-社团招新</title>
	<link rel="stylesheet" href="/common/mainpage.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/common/topside.css"/>

    <script src="/common/jquery.min.js"></script>
	<script src="/common/jquery.js"></script>
    <script src="/common/mainpage.js"></script>
</head>
<body>

	<%--<div class="mainbox">
		<div class="topside">
			<div class="logo"><img src="/pic/slogen.png"></div>
			<div class="sectionlist">
				<form action="/mapages.do" method="post" id="topform">
				<button class="section" name="section-header">首页</button>
				<button class="section" name="section-recruit">社团招新</button>
				<button class="section" name="section-activity">社团活动</button>
				<button class="section" name="section-mine">我的社团</button>
				<button class="section" name="section-personal">个人中心</button>
				</form>
			</div>
			<div class="head" name="head">
				<img src="/pic/logo.png">
				<p>欢迎你：XXX</p>
			</div>
		</div>--%>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>
		<form action="/AddCommunity.do " method="post" id="realform">
		<div class="content">
			<div class="innercontent" style="font-size: 0px;">
					<%
	Community[] communities = (Community[]) request.getSession().getAttribute("communities");

	for(int i = 0 ; i<communities.length;i++)

	{
	
%>
				<div class="block" name="">
					<button class="right" type="button" onclick="viewDetail('<%=communities[i].getComID()%>')">
					<img    class="block-img"<%-- onclick="*applyCo('<%=communities[i][0]%>','viewDetail')"--%> src=<%=communities[i].getPicSrc()%> >
					</button>
					<button class="apply" onclick="applyCo('<%=communities[i].getComID()%>','applyCommunity')" type="button">申请加入</button>
				</div>


<%
		}
		%>
				<input type="hidden" name="comID" value="" id="comID">
				<input type="hidden" name="method" value="" id="method">
			</div>
		</div>

	</div>

<div class="bgDiv"></div>
<div class="leftNav">
<p>社团招新啦</p>
<table>
<tr><td>社团名称</td><td ><input type="text" id="lcomName"></td></tr>
<tr><td>社团类型</td><td  class="later"></td></tr>
<tr><td>招新要求</td><td class="later"></td></tr>
<tr><td>报名时间</td><td class="later"></td></tr>
<tr><td>考核方式</td><td class="later"></td></tr>
<tr  class="button3">
<td></td><td>
<button type="button"style="float:right;">返回</button>&nbsp&nbsp&nbsp
<button type="button" style="float:right;" onclick="applybyt('applyCommunity')">我要报名</button></td></tr>
</table>
</div>
<div class="rightNav">
<p>社团招新啦</p>
<table>
<tr><td>社团名称</td><td ><input type="text" id="rcomName"></td></tr>
<tr><td>社团类型</td><td class="later"></td></tr>
<tr><td>招新要求</td><td class="later"></td></tr>
<tr><td>报名时间</td><td class="later"></td></tr>
<tr><td>考核方式</td><td class="later"></td></tr>
<tr  class="button3">
<td></td><td>
<%--<button type="button"style="float:right;">返回</button>&nbsp&nbsp&nbsp--%>
<button type="button" style="float:right;" onclick="applybyt('applyCommunity')">我要报名</button></td></tr>
</table>
</div>




	<script>
        function applyCo(id,method) {
            document.getElementById("comID").value = id;
            document.getElementById("method").value = method;

            document.getElementById("realform").submit();
        }
        function viewDetail(id) {
            document.getElementById("comID").value = id;
			document.getElementById("lcomName").value = id;
            document.getElementById("rcomName").value = id;

        }
        function applybyt(method) {
            document.getElementById("method").value = method;
            document.getElementById("realform").submit();
        }

	</script>
		</form>
</body>
</html>