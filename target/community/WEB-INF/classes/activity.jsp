<%@ page import="java.util.List" %>
<%@ page import="com.java.model.Activity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-社团招新</title>
	<link rel="stylesheet" href="/css/viewCommunities.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/css/topside.css"/>

	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.js"></script>
	<script src="/js/mainpage.js"></script>


</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
<form action="/AddCommunity.do " method="post" id="realform">
		<!-- 海报展示，作者：陆祎洲 -->
		<div class="content">
			<div class="searchbar">
				<div class="search">
					<img class="search-pic" src="pic/search.png" id="off" onclick="search(this)">
					<div class="input-div"><input type="textarea" id="keywords"></div>
					<button class="search-but">搜索</button>
				</div>
				<div class="filter" onmouseleave="dropup(this)">
					<ul class="dropdown">
						<li>我加入的</li>
						<li>我创建的</li>
						<li>我关注的</li>
						<li>最热门的</li>
						<li>全部</li>
					</ul>
					<div class="filter-text">筛选</div>
					<div class="order" id="order-text">选择筛选依据<button class="dropdown" onmouseover="dropdown(this)">↓</button></div>
				</div>
			</div>
			<div class="innercontent" style="font-size: 0px;">
				<%
					List<Activity> list = (List<Activity>) request.getAttribute("activities");
					for(int i = 0;i<list.size() &&list.get(i) != null;i++)
					{
					    Activity activity = list.get(i);
				%>
				<div class="block" name="">

					<button class="right" type="button" onclick="viewDetail('<%=activity.getTitle()%>')">
						<img class="block-img" src=<%=activity.getPostcard()%>>
					</button>
					<span class="introduction">点击图片查看详情</span>
					<button class="apply" name="applybutton1" onclick="applyCo('<%=activity.getTitle()%>','applyActivity')" type="button">我要加入</button>
				</div>
				<%
					}
				%>
			</div>
			<div class="page"><button class="pagedown"><</button><button class="pageup">></button></div>
		</div>
	</div>
	<!-- 背景 -->
	<div class="bgDiv"></div>
	<!-- 侧边信息卡 功能：董淑媛 CSS调整：陆祎洲 -->
	<div class="leftNav">
		<div class="banner">
			<img class="community-logo" src="/pic/cola.jpg"><span class="title" id = "Ltitle"></span>
		</div>
		<div class="table">
			<table class="info_card">
				<tr><td class="h">社团</td>
					<td class="h">活动介绍</td></tr>
				<tr><td id = "LassociationName"></td>
					<td rowspan="7" style="text-align-last: start;vertical-align: top;padding: 4px;" id = "Lintrodution"></td></tr>
				<%--<tr><td class="h">活动名称</td></tr>
				<tr><td id = ""></td></tr>--%>
				<tr><td class="h">活动时间</td></tr>
				<tr><td id  = "Ltime"></td></tr>
				<tr><td class="h">活动地点</td></tr>
				<tr><td id = "Laddress"></td></tr>
				<tr><td class="h">报名截止</td>
					<td class="h">报名须知</td></tr>
				<tr><td id = "LregistrationTime"></td>
					<td rowspan="2" style="text-align-last: start;vertical-align: top;padding: 4px;" id="Ldemand"></td></tr>
				<tr><td class="h">联系方式</td></tr>
				<tr><td id = "Lcontact"></td></tr>
				<tr><td colspan="2" style="float: left;padding-top: 5px;"><button class="innerapply" onclick="applybyt('applyActivity')" type="button">我要报名</button></td></tr>
			</table>
		</div>
	</div>
	<div class="rightNav">
		<div class="banner">
			<img class="community-logo" src="/pic/cola.jpg"><span class="title" id = "Rtitle"></span>
		</div>
		<div class="table">
			<table class="info_card">
				<tr><td class="h">社团</td>
					<td class="h">活动介绍</td></tr>
				<tr><td id = "RassociationName"></td>
					<td rowspan="7" style="text-align-last: start;vertical-align: top;padding: 4px;" id = "Rintrodution"></td></tr>
				<%--<tr><td class="h">活动名称</td></tr>
				<tr><td id = ""></td></tr>--%>
				<tr><td class="h">活动时间</td></tr>
				<tr><td id  = "Rtime"></td></tr>
				<tr><td class="h">活动地点</td></tr>
				<tr><td id = "Raddress"></td></tr>
				<tr><td class="h">报名截止</td>
					<td class="h">报名须知</td></tr>
				<tr><td id = "RregistrationTime"></td>
					<td rowspan="2" style="text-align-last: start;vertical-align: top;padding: 4px;" id="Rdemand"></td></tr>
				<tr><td class="h">联系方式</td></tr>
				<tr><td id = "Rcontact"></td></tr>
				<tr><td colspan="2" style="float: left;padding-top: 5px;"><button class="innerapply" onclick="applybyt('applyActivity')" type="button">我要报名</button></td></tr>
			</table>
		</div>
	</div>
	<input type="hidden" name="retit" value="" id="retit">
	<input type="hidden" name="method" value="" id="method">
	<script>
        function applyCo(id,method) {
            document.getElementById("retit").value = id;
            document.getElementById("method").value = method;

            document.getElementById("realform").submit();
        }
        function applybyt(method) {
            document.getElementById("method").value = method;
            document.getElementById("realform").submit();
        }

        function viewDetail(id)
		{
            document.getElementById("retit").value = id;
            $.ajax({
                url:"/AddCommunity.do",
                type :"post",
                data:{method:"viewActDe",retit:id},
                success: function (msg) {
                    var objs = JSON.parse(msg);
                    document.getElementById("Ltitle").innerText = objs.activity.title;
                    document.getElementById("LassociationName").innerText = objs.activity.associationName;
                    document.getElementById("Lintrodution").innerText = objs.activity.introdution;
                    document.getElementById("Ltime").innerText = objs.activity.time;
                    document.getElementById("Laddress").innerText = objs.activity.address;
                    document.getElementById("LregistrationTime").innerText = objs.activity.registrationTime;
                    document.getElementById("Ldemand").innerText = objs.activity.demand;
                    document.getElementById("Lcontact").innerText = objs.activity.contact;



                    document.getElementById("Rtitle").innerText = objs.activity.title;
                    document.getElementById("RassociationName").innerText = objs.activity.associationName;
                    document.getElementById("Rintrodution").innerText = objs.activity.introdution;
                    document.getElementById("Rtime").innerText = objs.activity.time;
                    document.getElementById("Raddress").innerText = objs.activity.address;
                    document.getElementById("RregistrationTime").innerText = objs.activity.registrationTime;
                    document.getElementById("Rdemand").innerText = objs.activity.demand;
                    document.getElementById("Rcontact").innerText = objs.activity.contact;
                },
                error: function () {
                    alert("error")
                }
            });




		}
	</script>
</form>
</body>
</html>