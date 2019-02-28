<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>可乐网-社团信息修改</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" href="/css/topside2.css" type="text/css"/>
	<link rel="stylesheet" href="/css/clubpage2.css" type="text/css" >
	<link rel="stylesheet" href="/css/table2.css" type="text/css" >
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
	<script type="text/javascript" src="/js/information.js"></script>
	<script src="/js/prefixfree.min.js"></script>
	<script src="/js/jquery.js"></script>
	<script src="/js/tanchuang.js"></script>
	<style type="text/css">
	ul#main-member>li{
		pointer-events: none;
		vertical-align: middle;
		list-style: none;
		line-height: 100%;
		margin-top: 5px;
	}
</style>
</head>
<body>

 <jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
 <jsp:include page="/common/comTittle.jsp"/>
   <form action="/clubChange.do" method="post" name="clubInfo">
		<input type="hidden" id="method" name="method" value="">
		<input type="hidden" id="clubNum" name="clubNum" value=${community.getComID()}>
	   <input type="hidden" id = "logo" name =  "logo" id = "logo" value = "">
		<input type="hidden" id="clubName1" name="clubName1" value="">
		<input type="hidden" id="clubType1" name="clubType1" value="">
		<input type="hidden" id="clubLevel1" name="clubLevel1" value="">
		<input type="hidden" id="clubAim1" name="clubAim1" value="">
		<input type="hidden" id="clubMember1" name="clubMember1" value="">
		<input type="hidden" id="clubBiefInfo1" name="clubBiefInfo1" value="">
   </form>
	<div class="mainbox">
		<div class="personal-table">
			<table class="personal-update">
				<tr>
					<td>社团名称</td>
					<td><textarea cols="50" rows="1" disabled="true" class="text" style="border:0;background-color:white" name="clubName" id="clubName" >${community.getComName()}</textarea></td>
					<td colspan="2"><span class="upload-but"id="changelogo"style="visibility: hidden;">更换社团logo<input type="file" class="upload_pic" id="upload" onchange="uploadfile()"/></span></td>
				</tr>
				<tr>
					<td>社团级别</td>
					<td><textarea cols="50" rows="1" disabled="true" class="text" style="border:0;background-color:white" name="clubLevel"  id="clubLevel">${community.getLevel()}</textarea></td>
					<td rowspan="5">
					    <canvas id="cvs" class="logo" style="padding-left:0;"width="100" height="100"></canvas>
					</td>
				</tr>
				<tr>
					<td>社团类型</td>
					<td><textarea cols="50" rows="1" disabled="true" class="text" style="border:0;background-color:white" name="clubType" id="clubType" >${community.getType()}</textarea></td>
				</tr>
				<tr>
					<td>社团宗旨</td>
					<td><textarea cols="50" rows="1" disabled="true" class="text" style="border:0;background-color:white" name="clubAim" id="clubAim">${community.getAim()}</textarea></td>
				</tr>
				<tr>
					<td>社团简述</td>
					<td><textarea cols="50" rows="5" disabled="true" class="text" style="border:0;background-color:white" name="clubBiefInfo" id="clubBiefInfo">${community.getBiefInfo()}</textarea></td>
				</tr>
				<tr>
					<td>社长信息</td>
					<td>
						<textarea cols="30" rows="5" disabled="true" class="text" style="border:0;background-color:white"  name="clubMember"  id="clubMember">${community.getMembers()}</textarea>
					</td>
				</tr>
				<tr>
					<td><button class="table-button" onclick="update(this)" id="change" type="button">修改</button></td>
					<td style="text-align-last: end;" colspan="2">
						<button class="table-button" id="green" style="visibility: hidden;" onclick="confirm(this)" type="button">完成</button>
						<button class="table-button" style="visibility: hidden;" onclick="cancel(this)" type="button">取消</button>
					</td>
				</tr>
			</table>
		</div>
		<div id="fade"class="black_overlay">
	</div>


		<script src="/js/shangchuan.js"></script>

		<script type="text/javascript">
			function update(element){
				var children = element.parentElement.parentElement.children;
				children[0].children[0].style.visibility='hidden';
				children[1].children[0].style.visibility='visible';
				children[1].children[1].style.visibility='visible';
				var texts = document.getElementsByTagName('textarea');
				var  logos = document.getElementById('changelogo');
				logos.style.visibility='visible';
				for (var i = texts.length - 1; i >= 2; i--) {
					texts[i].disabled=false;
					texts[i].style.border="1px solid #333";
				}
				var uploadpic = document.getElementById('upload');
				uploadpic.disabled = false;
			}
			function confirm(element){
				var children = element.parentElement.parentElement.children;
				children[0].children[0].style.visibility='visible';
				children[1].children[0].style.visibility='hidden';
				children[1].children[1].style.visibility='hidden';
				var texts = document.getElementsByTagName('textarea');
				for (var i = texts.length - 1; i >= 0; i--) {
					texts[i].disabled=true;
					texts[i].style.border="0";
				}
				var uploadpic = document.getElementById('upload');
				uploadpic.disabled = true;
				var  logos = document.getElementById('changelogo');
				logos.style.visibility='hidden';
				
				 document.getElementById("clubName1").value=document.getElementById("clubName").value;
                 document.getElementById("clubType1").value=document.getElementById("clubType").value;
                 document.getElementById("clubLevel1").value=document.getElementById("clubLevel").value;
                 document.getElementById("clubAim1").value=document.getElementById("clubAim").value;
                 document.getElementById("clubMember1").value=document.getElementById("clubMember").value;
                 document.getElementById("clubBiefInfo1").value=document.getElementById("clubBiefInfo").value;
                 document.getElementById("method").value="changeInfo";
                 clubInfo.submit();
			}
			function cancel(element){
				var children = element.parentElement.parentElement.children;
				children[0].children[0].style.visibility='visible';
				children[1].children[0].style.visibility='hidden';
				children[1].children[1].style.visibility='hidden';
				var texts = document.getElementsByTagName('textarea');
				for (var i = texts.length - 1; i >= 0; i--) {
					texts[i].disabled=true;
					texts[i].style.border="0";
				}
				var uploadpic = document.getElementById('upload');
				uploadpic.disabled = true;
				var  logos = document.getElementById('changelogo');
				logos.style.visibility='hidden';
			}
			function toshowInfo() {
             document.getElementById("method").value="showClubInfo";
             clubInfo.submit();
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
                        document.getElementById("logo").value = srg;
                    },
                    error: function () {
                    }
                });
            }
		</script>

	</div>
 <jsp:include page="/common/comExit.jsp"/>
</body>
</html>