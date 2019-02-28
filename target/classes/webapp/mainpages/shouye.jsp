<%@ page import="com.java.model.ComMainPage" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>可乐网-社团主页</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/shouye.css">
	<script src="/js/jquery.js"></script>
</head>
<body>

<jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>
<jsp:include page="/common/comTittle.jsp"/>

	<span id="uploads">上传背景图片<input type="file" class="upload_pic" id="upload" onchange="uploadfile()"/></span>

	<button id="add" onclick="ShowDiv('adddiv','fade')">添加新闻</button>
<form action="/comMain.do" method="post" id = "saveform">
	<input type="hidden" name="bgpic" value="${community.getBackgroundPicture()}" id="bgpic">
	<input type="hidden" name="tit" value="${community.getHeadPicture()}" id="tit">
	<input type="hidden" name="tit1" value="${community.getTitle1()}" id="tit1">
	<input type="hidden" name="con1" value="${community.getContent1()}" id="con1">
	<input type="hidden" name="tit2" value="${community.getTitle2()}" id="tit2">
	<input type="hidden" name="con2" value="${community.getContent2()}" id="con2">
	<input type="hidden" name="method" value="updateinfo" id="method">
	<button id="baocun" type="button" onclick="subsave()">保存修改</button>
</form>
<script>
    function subsave() {
        document.getElementById("saveform").submit();
    }
    function uploadfile() {
          var fileobj = document.getElementById("upload").files[0];
        var form = new FormData();
        form.append("file", fileobj);
        $.ajax({
            url: "/assignment.do?method=upload",
            type: "post",
            data: form,
            dataType: "text",
            contentType: false,
            processData: false,
            success: function (srg) {
                document.getElementById("bgpic").value = srg;
                var pic =   document.getElementsById("title");
                pic.setAttribute(style,"background-image: url("+srg+");");
            },
            error: function () {
            }
        });
    }
</script>
	<div id="kuangjia">

		<div id="title" style="background-image:url(${community.getBackgroundPicture()})">
			<p id="biaoti" ondblclick="edit(this,'ggpc')" onchange="changesave('tit','ggpc')">${community.getHeadPicture()}</p>
		</div>

		<div id="xiam">
			<div id="gonggao">
				<div class="ggp">
					<p id="ggp" ondblclick="edit(this,'ggpa')" onchange="changesave('tit1','ggpa')">${community.getTitle1()}</p>
					<textarea rows="3" cols="20" id="ggtext" ondblclick="edit(this)" onchange="changesave('con1','ggtext')"> ${community.getContent1()}</textarea>
				</div>
				<div class="ggp">
					<p id="ggp2" ondblclick="edit(this,'ggpb')" onchange="changesave('tit2','ggpb')">${community.getTitle2()}</p>
					<textarea rows="3" cols="20" id="ggtext2" ondblclick="edit(this)" onchange="changesave('con2','ggtext2')">${community.getContent2()}</textarea>
				</div>
			</div>
			<script>
                function changesave(saveid,changeid) {
                    document.getElementById(saveid).value = document.getElementById(changeid).value;
                }
			</script>
			<div id="xinwen">
				<p id="xwlb">新闻列表</p>
				<div id="xwt">
					<table id="xwtable">
						<%
							List<ComMainPage> list = (List<ComMainPage>) request.getSession().getAttribute("communityPageInfo");
							for(int i = 0;i<list.size() && list != null;i++)
							{
								ComMainPage comMainPage = list.get(i);
						%>
						<tr >
							<td class="liebiao" onclick="ShowDiv('zwdiv','fade'),info('<%=comMainPage.getContact()%>')"><%=comMainPage.getTittle()%></td>
							<td class="shanchutd"><button type="button" class="shanchu" onclick="tdelete('<%=comMainPage.getTittle()%>')">删除</button></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/shouye.js"></script>
<script>
	function info(contact) {
		document.getElementById("zw2").value = contact;
	}
</script>
	
	<!--弹出层时背景层DIV-->
<jsp:include page="/common/comExit.jsp"/>
	<%--<div id="fade">
	</div>
	<div id="tuichu">
		<div>
			<span onclick="CloseDiv('tuichu','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
		</div>
		<p id="tuichup1">你确定要退出该社团吗？</p>
		<p id="tuichup2" style="display:none">由于你为该社团创建者，一旦退出，此社团将解散，请慎重选择.</p>
		<button type="button" id="tuichub1" class="buttonb">确定</button>
		<button type="button" class="buttonb" id="tuichub2"onclick="CloseDiv('tuichu','fade')">取消</button>
	</div>--%>

	<div id="adddiv">
		<div>
			<span onclick="CloseDiv('adddiv','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;top:20px;">×</p></span>
		</div>
		<table>
			<tr><td>标题</td>
				<td><input maxlength="20" id="neib" type="text"/></td></tr>
				<tr><td id="zwtd">正文</td>
					<td><textarea id="zw">添加正文</textarea></td></tr>
					<tr><td></td><td><button class="buttonb" type="button" onclick="CloseDiv('adddiv','fade')" style="float:right">取消</button>
						<button class="buttonb" type="button" style="float:right" onclick="conajax()">确定</button>
					</td></tr>
				</table>
			</div>

			<div id="zwdiv">
				<div>
					<span onclick="CloseDiv('zwdiv','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
				</div>
				<table>
					<tr><td><textarea id="zw2" disabled="true">新闻正文</textarea></td></tr>
					<tr><td style="float:right"><button class="buttonb" type="button" onclick="CloseDiv('zwdiv','fade')">返回</button></td></tr>
				</table>
			</div>

			<div id="fade2"></div>
<script>
  function tdelete(title){
    $.ajax({
        url:"/comMain.do",
        type: "post",
        data:{method:"tdelete",title:title},
        success:function (msg) {
            alert(msg);
            setTimeout(window.location.reload(true),200);
        },
        error:function () {
            alert("error");
        }

    });
}

    function conajax() {
        var tittle = document.getElementById("neib").value;
        var contact = document.getElementById("zw").value;
        $.ajax({
            url:"/comMain.do",
            type: "post",
            data:{method:"addcominfo",tittle:tittle,contact:contact},
            success:function (msg) {
                alert(msg);
                CloseDiv('adddiv','fade')
                setTimeout(window.location.reload(true),200);
            },
            error:function () {
                alert("error");
            }

        });
    }
</script>
		</body>
		</html>