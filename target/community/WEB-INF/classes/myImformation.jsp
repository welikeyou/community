<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
功能:查看个人信息
创建者:陆祎洲,董淑媛
修改者:黄友明,李兰
--%>
<html>
<head>
	<link rel="stylesheet" href="/myImformation/myImformation.css" type="text/css"/>
	<link rel="stylesheet" href="/common/topside.css" type="text/css"/>
	<%--<script type="text/javascript" src="/myImformation/myImformation.js"></script>--%>
	<title>可乐网-个人中心</title>
</head>
<body>

	<jsp:include page="/common/AfterLoginTittle.jsp"/>


	<!--弹出时背景层DIV-->
	<div id="fade" class="black_overlay"></div>

	<!--弹出修改表格-->
	<div id="safecodeupdate" class="white_content">
		<span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('safecodeupdate','fade')">×</span>
		<form action="/IoServlet.do" method="post" name="changepwform">
			<input type="hidden" name="method" value="changepwd">
		<table>


				<%--<input type="hidden" value="<%=accountfmain%>" id="pwaccount" name="pwaccount">--%>
					<tr>
					<td><input type="text" placeholder="旧密码" style="height:25px;width:150px;" id="oldpassword" name="oldpassword" /></td>
					</tr>
					<tr>
					<td><input type="password" placeholder="新密码" style="height:25px;width:150px;" id="newpassword1" name="newpassword1" /></td>
					</tr>
					<tr>
					<tr>
					<td><input type="password" placeholder="确认密码" style="height:25px;width:150px;"  id="newpassword2" name="newpassword2"/></td>
				</tr>
					<tr>
						<td>
							<button type="button" onclick="changepw()">完成</button>
						</td>
						<td>
							<button type="button" onclick="CloseDiv('safecodeupdate','fade')">取消</button>
						</td>
					</tr>
		</table>
		</form>
	</div>


	<div class="mainbox">
		<div class="personal-table">
			<table class="personal-update">
				<tr><td style="text-align-last: start;">姓名：</td><td><textarea cols="50" rows="1" disabled="true" class="text" id="Infoname0">${loginStudent.getName()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">性别：</td><td><textarea cols="50" rows="1" disabled="true" class="text" id="Infosex0" >${loginStudent.getSex()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">生日：</td><td><textarea cols="50" rows="1" disabled="true" class="text" id="Infobirthday0" >${loginStudent.getBirthday()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">联系方式：</td><td><textarea cols="50" rows="1" disabled="true" class="text" id="Infocontact0" >${loginStudent.getContact()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">学校：</td><td><textarea cols="50" rows="2" disabled="true" class="text"  id="Infoschool0">${loginStudent.getSchool()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">地址：</td><td><textarea cols="50" rows="2" disabled="true" class="text" id="Infoaddress0">${loginStudent.getAddress()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">兴趣爱好：</td><td><textarea cols="50" rows="5" disabled="true" class="text" id="Infohobby0" >${loginStudent.getHobby()}</textarea></td></tr>
				<tr><td style="text-align-last: start;">个性签名：</td><td><textarea cols="50" rows="5" disabled="true" class="text" id="Infoslogan0">${loginStudent.getSlogan()}</textarea></td></tr>

				<tr>
					<td>
						<button class="button-update" onclick="update(this)" type="button">修改</button>
						<button class="button-safecode" onclick="ShowDiv('safecodeupdate','fade')" type="button">修改密码</button>
					</td>
					<td style="text-align-last: end;">
						<button class="button-confirm" style="visibility: hidden;" onclick="confirm(this)" type="button">完成</button>
						<button class="button-cancel" style="visibility: hidden;" onclick="cancel(this)" type="button">取消</button>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<form action="/IoServlet.do" method="post" name="baseform" id = "baseform">
		<input type="hidden" name="method" value="changeIm">
		<input type="hidden" value="" id="Infoname" name="Infoname">
		<input type="hidden" value="" id="Infosex" name="Infosex" >
		<input type="hidden" value="" id="Infocontact" name="Infocontact">
		<input type="hidden" value="" id="Infoschool" name="Infoschool" >
		<input type="hidden" value="" id="Infobirthday" name="Infobirthday">
		<input type="hidden" value="" id="Infoaddress" name="Infoaddress" >
		<input type="hidden" value="" id="Infohobby" name="Infohobby">
		<input type="hidden" value="" id="Infoslogan" name="Infoslogan" >

	</form>
<script>
    function update(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='hidden';
        children[0].children[1].style.visibility='hidden';
        children[1].children[0].style.visibility='visible';
        children[1].children[1].style.visibility='visible';
        var texts = document.getElementsByTagName('textarea');
        for (var i = texts.length - 1; i >= 0; i--) {
            texts[i].disabled=false;
        }
    }
    function confirm(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='visible';
        children[0].children[1].style.visibility='visible';
        children[1].children[0].style.visibility='hidden';
        children[1].children[1].style.visibility='hidden';
        var texts = document.getElementsByTagName('textarea');
        for (var i = texts.length - 1; i >= 0; i--) {
            texts[i].disabled=true;
        }
        document.getElementById("Infoname").value=document.getElementById("Infoname0").value;
        document.getElementById("Infosex").value=document.getElementById("Infosex0").value;
        document.getElementById("Infocontact").value=document.getElementById("Infocontact0").value;
        document.getElementById("Infoschool").value=document.getElementById("Infoschool0").value;
        document.getElementById("Infobirthday").value=document.getElementById("Infobirthday0").value;
        document.getElementById("Infoaddress").value=document.getElementById("Infoaddress0").value;
        document.getElementById("Infohobby").value=document.getElementById("Infohobby0").value;
        document.getElementById("Infoslogan").value=document.getElementById("Infoslogan0").value;
        document.getElementById("baseform").submit();

    }
    function cancel(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='visible';
        children[0].children[1].style.visibility='visible';
        children[1].children[0].style.visibility='hidden';
        children[1].children[1].style.visibility='hidden';
        var texts = document.getElementsByTagName('textarea');
        for (var i = texts.length - 1; i >= 0; i--) {
            texts[i].disabled=true;
        }
    }
    //弹出隐藏层
    function ShowDiv(show_div,bg_div){
        document.getElementById(show_div).style.display='block';
        document.getElementById(bg_div).style.display='block' ;
        var bgdiv = document.getElementById(bg_div);
        bgdiv.style.width = document.body.scrollWidth;
    };
    //关闭弹出层
    function CloseDiv(show_div,bg_div)
    {
        document.getElementById(show_div).style.display='none';
        document.getElementById(bg_div).style.display='none';
    };

    function changepw() {
        changepwform.submit();
    }
</script>
</body>
</html>