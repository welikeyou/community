<%@ page import="com.java.model.Community" %>
<%@ page import="com.java.model.Recruitment" %>
<!DOCTYPE html>
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
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-社团招新</title>
	<link rel="stylesheet" href="/css/viewCommunities.css" type="text/css"/>
	<link rel="stylesheet" href="/css/topside.css" type="text/css"/>

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
					<img class="search-pic" src="/pic/search.png" id="off" onclick="search(this)">
					<div class="input-div"><input type="textarea" id="keywords"></div>
					<button class="search-but" onclick="search()">搜索</button>
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

			<div class="innercontent" style="font-size: 0px;" id = "message">
				<%
					Recruitment[] recruitments = (Recruitment[]) request.getSession().getAttribute("recruitments");

					for(int i = 0 ; i<recruitments.length && recruitments[i] != null;i++)

					{

				%>

				<div class="block" name="">

					<button class="right" type="button" onclick="viewDetail('<%=recruitments[i].getTittle()%>')">
					<img class="block-img" src=<%=recruitments[i].getPostcard()%>>
					</button>
					<span class="introduction">点击图片查看详情</span>
					<button class="apply" name="applybutton1" onclick="applyCo('<%=recruitments[i].getTittle()%>','applyCommunity')" type="button">我要加入</button>
				</div>
				<%
					}
				%>

			</div>
			<div class="page"><button class="pagedown" onclick="applybyt('pagedown')"><</button><button class="pageup" onclick="applybyt('pageup')">></button></div>

		</div>
	</div>
	<!-- 背景 -->
	<div class="bgDiv"></div>
	<!-- 侧边信息卡 功能：董淑媛 CSS调整：陆祎洲 -->
	<div class="leftNav">
		<div class="banner">
			<img class="community-logo" src="/pic/cola.jpg"><span class="title"id = "Ltittle">欢迎大家的加入</span>
		</div>
		<div class="table">
			<table class="info_card">
				<tr><td class="h">社团类型</td><td class="h">宣传文案</td></tr>
				<tr>
					<td>
						<a id="Ltype">神秘的社团</a>

					</td>
					<td rowspan="11" style="text-align-last: start;vertical-align: top;padding: 4px;"id = "Lpropaganda">社长有点懒,什么都没留下</td></tr>
				<tr><td class="h">社团等级</td></tr>
				<tr><td id="Llevel">未填写</td></tr>
				<tr><td class="h">报名时间</td></tr>
				<tr><td id = "Ltime">无</td></tr>
				<tr><td class="h">招新要求</td></tr>
				<tr><td style="word-wrap: break-word;word-break: all;"id = "Ldemand">无</td></tr>
				<tr><td class="h">考核方式</td></tr>
				<tr><td id = "Lexamine">无</td></tr>
				<tr><td class="h">联系方式</td></tr>
				<tr><td style="word-wrap: break-word;word-break: all;" id = "Lcontact">无</td></tr>
				<tr><td colspan="2" style="float: left;padding-top: 5px;"><button class="innerapply"  onclick="applybyt('applyCommunity')" type="button">我要报名</button></td></tr>
			</table>
		</div>
	</div>
	<div class="rightNav">
		<div class="banner">
			<img class="community-logo" src="/pic/cola.jpg"><span class="title"id = "Rtittle">欢迎大家的加入</span>
		</div>
		<div class="table">
			<table class="info_card">
				<tr><td class="h">社团类型</td><td class="h">宣传文案</td></tr>
				<tr>
					<td>
						<a id="Rtype">神秘的社团</a>

					</td>
					<td rowspan="11" style="text-align-last: start;vertical-align: top;padding: 4px;"id = "Rpropaganda">社长有点懒,什么都没留下</td></tr>
				<tr><td class="h">社团等级</td></tr>
				<tr><td id="Rlevel">未填写</td></tr>
				<tr><td class="h">报名时间</td></tr>
				<tr><td id = "Rtime">无</td></tr>
				<tr><td class="h">招新要求</td></tr>
				<tr><td style="word-wrap: break-word;word-break: all;"id = "Rdemand">无</td></tr>
				<tr><td class="h">考核方式</td></tr>
				<tr><td id = "Rexamine">无</td></tr>
				<tr><td class="h">联系方式</td></tr>
				<tr><td style="word-wrap: break-word;word-break: all;" id = "Rcontact">无</td></tr>
				<tr><td colspan="2" style="float: left;padding-top: 5px;"><button class="innerapply"  onclick="applybyt('applyCommunity')" type="button">我要报名</button></td></tr>
			</table>
		</div>
		</div>

	</div>
		<input type="hidden" name="retit" value="" id="retit">
		<input type="hidden" name="method" value="" id="method">
	</form>

	<script>
        function applyCo(id,method) {
            document.getElementById("retit").value = id;
            document.getElementById("method").value = method;

            document.getElementById("realform").submit();
        }
        function viewDetail(id) {
            document.getElementById("retit").value = id;

            $.ajax({
                url:"/AddCommunity.do",
                type :"post",
				data:{method:"viewDetail",retit:id},
				success: function (msg) {
                    var objs = JSON.parse(msg)
                    document.getElementById("Ltype").innerText = objs.community.type;
                    document.getElementById("Llevel").innerText = objs.community.level;
					document.getElementById("Ltittle").innerText = objs.recruitment.tittle;
                    document.getElementById("Lpropaganda").innerText = objs.recruitment.propaganda;
                    document.getElementById("Ltime").innerText = objs.recruitment.time;
                    document.getElementById("Ldemand").innerText = objs.recruitment.demand;
                    document.getElementById("Lexamine").innerText = objs.recruitment.examine;
                    document.getElementById("Lcontact").innerText = objs.recruitment.contact;

                    document.getElementById("Rtype").innerText = objs.community.type;
                    document.getElementById("Rlevel").innerText = objs.community.level;
                    document.getElementById("Rtittle").innerText = objs.recruitment.tittle;
                    document.getElementById("Rpropaganda").innerText = objs.recruitment.propaganda;
                    document.getElementById("Rtime").innerText = objs.recruitment.time;
                    document.getElementById("Rdemand").innerText = objs.recruitment.demand;
                    document.getElementById("Rexamine").innerText = objs.recruitment.examine;
                    document.getElementById("Rcontact").innerText = objs.recruitment.contact;
                },
                error: function () {
                    alert("error")
                }
            });



        }
        function applybyt(method) {
            document.getElementById("method").value = method;
            document.getElementById("realform").submit();
        }
        function search() {
            var searxhtext = document.getElementById("keywords").value;
			$.ajax(
				{
                    url:"/AddCommunity.do",
                    type :"post",
                    data:{method:"serach",keywords:"keywords"},
                    success: function () {
                    },
                    error: function () {
                        alert("error")
                    }

				}


			)
        }
	</script>
</body>
</html>