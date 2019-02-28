<!DOCTYPE html>
<%@ page import="com.java.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>可乐网-我的任务</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/woderenwu.css">
	<script src="/js/woderenwu.js"></script>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
<%
	List<Task> urgentTask=(List<Task>)request.getAttribute("urgentTask");
%>
</div>

	<input type="checkbox" id="menu">
	<label class="menu" for="menu">
		<span></span>
		<span></span>
	</label>
	<ul class="drawer" style="padding-top:50px">
		<li>
			<a onclick="ToTask()">
				<i class="0"></i>
				<span>我的任务</span>
			</a>
		</li>
		<li>
			<a onclick="Toim()">
				<i class="2"></i>
				<span>个人信息</span>
			</a>
		</li>
	</ul>
<script>
	function Toim() {
		window.location = "/myImformation/myImformation.jsp"
    }
    function ToTask() {
		window.location = "/receiveTask.do?method=showTask";
    }
</script>
	<form action="/receiveTask.do" method="post" name="showTaskForm">
	<input type="hidden" value="" name="method" id="method">
    <input type="hidden" value="" name="taskNum" id="taskNum">
	</form>
	<div class="mainbox">
<!--弹出层时背景层DIV-->
<div id="fade" class="black_overlay">
</div>
 <div id="xiangqing" class="white_content">
<div>
<span onclick="CloseDiv('xiangqing','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
</div>

<table id="xqtable"border="0">
     <tr><td>任务描述</td></tr>
	 <tr><td><textarea disabled="true" id="detail"></textarea></td></tr>
     <tr><td><button id="xiazaib"type="button" style="float:right;">下载附件</button></td></tr>
	<<input type="hidden" name="filepath" value="" id="filepath">
</table>
</div>
<script>
	function xiazai () {
	    var filepath = document.getElementById("filepath").value;
	    $.ajax(
			{
				url:"/receiveTask.do",
				type:"post",
				data:{method:"load",filepath:filepath},
                success: function(msg){
				    alert(msg);
				}
			});
    }
</script>
<div id="renwu" >
<table id="renwutable">
<tr><th colspan="8"id="title">我的任务</th></tr>
<tr><th class="th">任务标题</th><th class="th">状态</th><th class="th">截止时间</th><th class="th">发布社团</th><th class="th">发布人</th><th class="th">发布日期</th><th class="th">详情</th><th class="th">操作</th></tr>
</table>
<div id="renwu2">
<table id="renwutable2">
 <%
               for(int i=0;i<urgentTask.size()&&urgentTask!=null;i++){
%>
 <tr><td><%=urgentTask.get(i).getTitle()%></td><td style="color:red"><%=urgentTask.get(i).getState()%></td><td><%=urgentTask.get(i).getDdl()%></td><td><%=urgentTask.get(i).getClubName()%></td><td><%=urgentTask.get(i).getSenderName()%></td><td><%=urgentTask.get(i).getSendTime()%></td><td onclick="ShowDiv('xiangqing','fade',<%=urgentTask.get(i).getResult()%>)">详细信息</td><td><input type="button" value="完成" onclick="toFinish(<%=urgentTask.get(i).getResult()%>)"> </td></tr>
			   <%}%>
<%List<Task> doingTask=(List<Task>)request.getAttribute("doingTask");
		for(int i=0;i<doingTask.size()&&doingTask!=null;i++)
	{%>
 <tr><td><%=doingTask.get(i).getTitle()%></td><td><%=doingTask.get(i).getState()%></td><td><%=doingTask.get(i).getDdl()%></td><td><%=doingTask.get(i).getClubName()%></td><td><%=doingTask.get(i).getSenderName()%></td><td><%=doingTask.get(i).getSendTime()%></td><td onclick="ShowDiv('xiangqing','fade',<%=doingTask.get(i).getResult()%>)">详细信息</td><td><input type="button" value="完成" onclick="toFinish(<%=doingTask.get(i).getResult()%>)"> </td></tr>
			   <%}%>
		<%List<Task> overtimeTask=(List<Task>)request.getAttribute("overtimeTask");
	for(int i=0;i<overtimeTask.size()&&overtimeTask!=null;i++)
	{%>		
<tr><td><%=overtimeTask.get(i).getTitle()%></td><td><%=overtimeTask.get(i).getState()%></td><td><%=overtimeTask.get(i).getDdl()%></td><td><%=overtimeTask.get(i).getClubName()%></td><td><%=overtimeTask.get(i).getSenderName()%></td><td><%=overtimeTask.get(i).getSendTime()%></td><td onclick="ShowDiv('xiangqing','fade',<%=overtimeTask.get(i).getResult()%>)">详细信息</td><td><input type="button" value="完成" onclick="toFinish(<%=overtimeTask.get(i).getResult()%>)"> </td></tr>
			   <%}%>
<%List<Task> finishedTask=(List<Task>)request.getAttribute("finishedTask");
		for(int i=0;i<finishedTask.size()&&finishedTask!=null;i++)
		{%>	
<tr><td><%=finishedTask.get(i).getTitle()%></td><td><%=finishedTask.get(i).getState()%></td><td><%=finishedTask.get(i).getDdl()%></td><td><%=finishedTask.get(i).getClubName()%></td><td><%=finishedTask.get(i).getSenderName()%></td><td><%=finishedTask.get(i).getSendTime()%></td><td onclick="ShowDiv('xiangqing','fade',<%=finishedTask.get(i).getResult()%>)">详细信息</td><td></td></tr>
			   <%}%>	
			   
</table>
</div>
</div>	

	</div>
	<script>
        //弹出隐藏层
        function ShowDiv(show_div,bg_div,ob){
            var w=JSON.stringify(ob);
            var y=""+w+"";
            var s=JSON.parse(y);
            document.getElementById("detail").innerText=s.content;
            document.getElementById("filepath").value = s.filepath;
            //附件添加处

            document.getElementById(show_div).style.display='block';
            document.getElementById(bg_div).style.display='block' ;
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;
            // bgdiv.style.height = $(document).height();
            $("#"+bg_div).height($(document).height());
        };
        //关闭弹出层
        function CloseDiv(show_div,bg_div)
        {
            document.getElementById(show_div).style.display='none';
            document.getElementById(bg_div).style.display='none';
        };
		function toFinish(ob)
		{

		var w=JSON.stringify(ob);
		var y=""+w+"";
		var s=JSON.parse(y);

		document.getElementById("taskNum").value=s.taskNum;
		document.getElementById("method").value="toFinished";
		showTaskForm.submit();
		}

		</script>
</body>
</html>