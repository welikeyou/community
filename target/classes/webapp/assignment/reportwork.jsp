<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>可乐网-发布活动</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/css/topside2.css"/>
	<link rel="stylesheet" type="text/css" href="/css/table2.css"/>
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
	<script src="/js/prefixfree.min.js"></script>
	<script src="/js/tanchuang.js"></script>
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
<jsp:include page="/common/comTittle.jsp"/>
<form action="/assignment.do" method="post" id="subform" >
	<input type="hidden" name="method" value="reportwork">
<div class="mainbox">
		<table>
			<tr><th colspan="4">活动发布</th></tr>
			<tr>
				<td>标题</td>
				<td><input type="text"  name = "title" id = "title"/></td>
				<td>活动海报<p style="color: #aaa;margin:0px;">87:54为宜</p></td>
				<td><span class="upload-but">点此上传海报</span><input type="file" class="upload_pic" id="upload" onchange="uploadfile()"/></td>
				</tr>
				<tr>
					<td>举办社团</td>
					<td><input type="text"style="border:0;background-color:white"disabled="false " value="${community.getComName()}"/></td>
					<td colspan="2" rowspan="10"><img class="poster" id="cvs" style="width: 330px;height: 520px;" /></td>
				</tr>
				<tr>
					<td>活动名称</td>
					<td><input type="text" name = "name" id = "name"/></td>
				</tr>
				<tr>
					<td>活动时间</td>
					<td><input type="text" name = "time" id = "time"/></td>
				</tr>
				<tr>
					<td>活动地点</td>
					<td><input type="text" name = "address" id = "address"/></td>
				</tr>
				<tr>
					<td>活动介绍</td>
					<td><textarea type="text" clos="20" rows="5" warp="virtual" name = "introduction" id = "introduction"></textarea></td>
				</tr>
				<tr>
					<td>活动招募要求</td>
					<td><textarea type="text" clos="20" rows="3" warp="virtual" name = "demand" id = "demand"></textarea></td>
				</tr>
				<tr><td>活动报名时间</td>
					<td><input type="text" name = "registrationTime" id = "registrationTime"/></td>
				</tr>
				<tr>
					<td>发布人</td>
					<td><input type="text" name = "publisher" id = "publisher"/></td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input type="text" name="contact" id = "contact"/></td>
				</tr>
				<tr>
				<td></td>
					<td><button class="table-button" type="button" onclick="sub()" style="float:right;margin-right:0">提交</button></td>
				</tr>
			</table>
			<script src="/js/upload_poster.js"></script>
		</div>
</form>
<script>
    function sub() {
        var title = document.getElementById("title").value;
        var name = document.getElementById("name").value;
        var time = document.getElementById("time").value;
        var address = document.getElementById("address").value;
        var introduction = document.getElementById("introduction").value;
        var demand = document.getElementById("demand").value;
        var registrationTime = document.getElementById("registrationTime").value;
        var publisher = document.getElementById("publisher").value;
        var contact = document.getElementById("contact").value;
        if(0>title.length)
        {
            alert("请输入标题");
            return;
        }
        if(0>name.length)
        {
            alert("请输入活动名称");
            return;
        }
        if(0>address.length)
        {
            alert("请输入社团地址");
            return;
        }
        if(0>introduction.length)
        {
            alert("请输入活动介绍");
            return;
        }
        if(0>demand.length)
        {
            alert("请输入活动要求");
            return;
        }
        if(0>registrationTime.length)
        {
            alert("请输入报名时间");
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
		<div id="fade"class="black_overlay">
	</div>

<jsp:include page="/common/comExit.jsp"/>
	</body>
	</html>