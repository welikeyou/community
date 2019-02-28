<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-发布招新</title>
	<link rel="stylesheet" type="text/css" href="/css/topside2.css"/>
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/table2.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
	<script src="/js/prefixfree.min.js"></script>
    <script src="/js/tanchuang.js"></script>
</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
<!-- 侧边任务栏 作者：董淑媛-->
<jsp:include page="/common/comTittle.jsp"/>
	</div>
	<!-- 导航按钮 作者：陆祎洲-->
<form action="/assignment.do" method="post" id="subform" >
	<div class="mainbox">
		<table>
			<tr><th colspan="4">招新发布</th></tr>
			<tr>
				<td>标题</td>
				<td><input type="text" name = "title" id = "title"/></td>
				<td>社团海报<p style="color: #aaa;margin:0px;">87:54为宜</p></td>
				<td><span class="upload-but">点此上传海报</span><input type="file" class="upload_pic" id="upload" onchange="uploadfile()"/></td>
				</tr>
				<tr>
					<td>社团类型</td>
					<td><input type="text"style="border:0;background-color:white"disabled="false" value = "${community.getType()}"/></td>
					<td colspan="2" rowspan="10"><img class="poster" id="cvs" style="width: 330px;height: 520px;" /></td>
				</tr>
				<tr>
					<td>社团级别</td>
					<td><input type="text"style="border:0;background-color:white"disabled="false" value="${community.getLevel()}"/></td>
				</tr>
				<tr>
					<td>社团简介</td>
					<td><textarea type="text" clos="20" rows="3" warp="virtual" name = "introduction" id ="introduction"></textarea></td>
				</tr>
				<tr>
					<td>招新要求</td>
					<td><textarea type="text" clos="20" rows="3" warp="virtual" name="demand" id = "demand"></textarea></td>
				</tr>
				<tr>
					<td>报名时间</td>
					<td><input type="text" name = "time" id = "time"/></td>
				</tr>
				<tr>
					<td>考核方式</td>
					<td><textarea type="text" clos="20" rows="3" warp="virtual" name = "examine" id = "examine"></textarea></td>
				</tr>
				<tr>
					<td>招新宣传文案</td>
					<td><textarea type="text" clos="20" rows="5" warp="virtual" name = "propaganda" id = "propaganda"></textarea></td>
				</tr>
				<tr>
					<td>发布人</td>
					<td><input type="text"  name = "publisher" id = "publisher" value="${loginStudent.getName()}"/></td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input type="text" name = "contact" id = "contact" value="${loginStudent.getContact()}"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button class="table-button" type="button" style="float:right;margin-right:0" onclick="sub('reportnew')">提交</button></td>
					<input name="method"value="" id="methodSub" type="hidden">
				</tr>
			</table>
			<script src="/js/upload_poster.js"></script>
		</div>
</form>
<script>
    function sub(method) {
        var title = document.getElementById("title").value;
        var introduction = document.getElementById("introduction").value;
        var demand = document.getElementById("demand").value;
        var time = document.getElementById("time").value;
        var examine = document.getElementById("examine").value;
        var propaganda = document.getElementById("propaganda").value;
        var publisher = document.getElementById("publisher").value;
        var contact = document.getElementById("contact").value;
        if(0>title.length)
		{
		    alert("请输入标题");
		    return;
		}
        if(0>introduction.length)
        {
            alert("请输入社团简介");
            return;
        }
        if(0>demand.length)
        {
            alert("请输入社团要求");
            return;
        }
        if(0>time.length)
        {
            alert("请选择时间");
            return;
        }
        if(0>examine.length)
        {
            alert("请输入考核方式");
            return;
        }
        if(0>propaganda.length)
        {
            alert("请输入标语");
            return;
        }
        if(0>publisher.length)
        {
            alert("请输入发布人");
            return;
        }
        if(0>contact.length)
        {
            alert("请输入联系方式");
            return;
        }
        document.getElementById("methodSub").value=method;
        document.getElementById("subform").submit();
    }
    function uploadfile() {
        var fileobj = document.getElementById("upload").files[0];
        var form = new FormData();
        form.append("file",fileobj);
        $.ajax({
            url:"/assignment.do?method=upload",
            type :"post",
            data :form,
            dataType:"text",
            contentType: false,
            processData: false,
            success: function (srg) {
            },
            error: function () {
            }
        });
    }
</script>
	<div id="fade" class="black_overlay">
	</div>
<jsp:include page="/common/comExit.jsp"/>
	</body>
	</html>