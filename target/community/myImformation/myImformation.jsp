<%@ page import="com.java.model.Student" %>
<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

 <meta charset="UTF-8">
 <link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
 <link rel="stylesheet" href="/css/table.css" type="text/css"/>
 <link rel="stylesheet" href="/css/personal.css" type="text/css"/>
 <link rel="stylesheet" href="/css/topside.css" type="text/css"/>
 <link rel="stylesheet" type="text/css" href="/css/topside2.css">
 <link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>

 <title>可乐网-个人信息</title>
</head>
<body >
<% Student student = (Student) request.getSession().getAttribute("loginStudent");%>

<jsp:include page="/common/AfterLoginTittle.jsp"/>
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
 
 <div class="mainbox">
  <div class="personal-table">
    <table class="personal-update">
      <tr><th colspan="2">个人信息</th></tr>
      <tr><td style="text-align-last: start;">上传头像</td>
        <td>
          <img id="cvs" class="header" src="${loginStudent.getLogo()}"/>
          <span class="upload-but"style="visibility:hidden"id="uploadspan">
            <input type="file" class="upload-pic" id="upload" onchange="uploadfile()"/>点此上传
          </span>
        </td></tr>
        <tr><td style="text-align-last: start;">姓名</td><td><input type="text" disabled="true" class="text" style="border:0;background-color:white" id="Infoname0" value="${loginStudent.getName()}"/></td></tr>
        <tr><td style="text-align-last: start;">性别</td><td><input type="radio" name="sex" disabled="true"   id="sexm"/><span style="color:#007bd9;" >男♂</span><input type="radio" name="sex" disabled="true" id="sexf"/><span style="color:#ea5554;">女♀</span></td></tr>
        <tr><td style="text-align-last: start;">生日</td><td><input style="border:0;background-color:white"type="date" disabled="true" class="text"id="Infobirthday0"  value="${loginStudent.getBirthday()}"/></td></tr>
        <tr><td style="text-align-last: start;">联系方式</td><td><input style="border:0;background-color:white"type="text" disabled="true" class="text" id="Infocontact0" value="${loginStudent.getContact()}"/></td></tr>
        <tr><td style="text-align-last: start;">学校</td><td><input style="border:0;background-color:white"type="text" disabled="true" class="text" id="Infoschool0" value="${loginStudent.getSchool()}"/></td></tr>
        <tr><td style="text-align-last: start;">地址</td></tr>
        <tr><td colspan="2"><input style="border:0;background-color:white"type="text" disabled="true" class="text" id="Infoaddress0" value="${loginStudent.getAddress()}"/></td></tr>
        <tr><td style="text-align-last: start;">兴趣爱好</td></tr>
        <tr><td colspan="2"><input style="border:0;background-color:white"type="text" disabled="true" class="text" id="Infohobby0" value="${loginStudent.getHobby()}"/></td></tr>
        <tr><td style="text-align-last: start;">个人签名</td></tr>
        <tr><td colspan="2"><input style="border:0;background-color:white"type="text" disabled="true" class="text" id="Infoslogan0" value="${loginStudent.getSlogan()}"/></td></tr>
        <tr>
          <td>
            <button class="table-button" onclick="update(this)" type="button">修改信息</button>
            <button class="table-button" id="safecode-update" onclick="ShowDiv('safecodeupdate','fade')" type="button">修改密码</button>
          </td>
          <td style="text-align-last: end;">
            <button class="table-button"  style="visibility: hidden;" onclick="confirm(this)" type="button">完成</button>
            <button class="table-button" style="visibility: hidden;" onclick="cancel(this)" type="button">取消</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <!--弹出时背景层DIV-->
  <div id="fade" class="black_overlay"></div>
  <!--弹出修改表格-->
<form action="/IoServlet.do" method="post" name="changepwform">
  <div id="safecodeupdate" class="white_content" style="margin-top:10%">
    <div>
      <div class="cancel" onclick="CloseDiv('safecodeupdate','fade')">
        <p class="cancel" style="margin-bottom: 2px;">×</p>
      </div>


      <table style="margin-left:50px;">
        <tr>
          <td><input type="text" placeholder="旧密码" style="height:25px;width:150px;" id="oldpassword"/></td>
        </tr>
        <tr>
          <td><input type="password" placeholder="新密码" style="height:25px;width:150px;" id="newpassword1"/></td>
        </tr>
        <tr>
          <td><input type="password" placeholder="确认密码" style="height:25px;width:150px;" id="newpassword2"/></td>
        </tr>
        <tr>
          <td>
            <button class="table-button" onclick="changepw()" type="button">完成</button>
            <button class="table-button" onclick="CloseDiv('safecodeupdate','fade')" type="button">取消</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
    <input type="hidden" name="method" value="changepwd">
    <input type="hidden" name="orignalsex" id="originalsex" value="${loginStudent.getSex()}">
</form>
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
                <input type="hidden" value="/pic/lemon.jpg" id = "infologo" name="infologo">
		    </form>
  
<script>
     var sex=document.getElementById("originalsex").value;
        if(sex=="男")
        {
            document.getElementById("sexm").setAttribute("checked","checked");
        }
        else
        {
            document.getElementById("sexf").setAttribute("checked","checked");
        }

    function update(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='hidden';
        children[0].children[1].style.visibility='hidden';
        children[1].children[0].style.visibility='visible';
        children[1].children[1].style.visibility='visible';
        var texts = document.getElementsByTagName('input');
        var up = document.getElementById('uploadspan');
        up.style.visibility='visible';
        for (var i = texts.length - 1; i >= 0; i--) {
            texts[i].disabled=false;
            texts[i].style.border="1px solid #333";
        }
    }
    function confirm(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='visible';
        children[0].children[1].style.visibility='visible';
        children[1].children[0].style.visibility='hidden';
        children[1].children[1].style.visibility='hidden';
        var texts = document.getElementsByTagName('input');
        var up = document.getElementById('uploadspan');
        up.style.visibility='hidden';
        for (var i = texts.length - 15; i >= 0; i--) {
            texts[i].disabled=true;
            texts[i].style.border="0";
        }
        var sexs = document.getElementsByName('sex');
        for (var i = 0; i < sexs.length; i++) {
            if (sexs[i].checked == true){
                if(i==0)
                     document.getElementById("Infosex").value="男";
                else
                    document.getElementById("Infosex").value="女";
            }
        }
        document.getElementById("Infoname").value=document.getElementById("Infoname0").value;
        document.getElementById("Infocontact").value=document.getElementById("Infocontact0").value;
        document.getElementById("Infoschool").value=document.getElementById("Infoschool0").value;
        document.getElementById("Infobirthday").value=document.getElementById("Infobirthday0").value;
        document.getElementById("Infoaddress").value=document.getElementById("Infoaddress0").value;
        document.getElementById("Infohobby").value=document.getElementById("Infohobby0").value;
        document.getElementById("Infoslogan").value=document.getElementById("Infoslogan0").value;

        baseform.submit();
    }
    function cancel(element){
        var children = element.parentElement.parentElement.children;
        children[0].children[0].style.visibility='visible';
        children[0].children[1].style.visibility='visible';
        children[1].children[0].style.visibility='hidden';
        children[1].children[1].style.visibility='hidden';
        var texts = document.getElementsByTagName('input');
        var up = document.getElementById('uploadspan');
        up.style.visibility='hidden';
        for (var i = texts.length - 1; i >= 0; i--) {
            texts[i].disabled=true;
            texts[i].style.border="0";
        }
    }
    //获取上传按钮
    window.onload=function(){
        var input1 = document.getElementById("upload");
        if (typeof FileReader === 'undefined') {
            //result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
            input1.setAttribute('disabled', 'disabled');
        } else {
            input1.onchange =function(){
                var file = this.files[0]; //获取上传文件列表中第一个文件
                if (!/image\/\w+/.test(file.type)) {
                    //图片文件的type值为image/png或image/jpg
                    alert("文件必须为图片！");
                    return false;
                }
                // console.log(file);
                var reader = new FileReader(); //实例一个文件对象
                reader.readAsDataURL(file); //把上传的文件转换成url
                //当文件读取成功便可以调取上传的接口
                reader.onload = function(e) {
                    var image = new Image();
                    // 设置src属性
                    image.src = e.target.result;
                    var max = 200;
                    // 绑定load事件处理器，加载完成后执行，避免同步问题
                    image.onload = function() {
                        // 获取 canvas DOM 对象
                        var img = document.getElementById('cvs');
                        img.src = image.src;
                    };
                }
            };
        }
    }
    //弹出隐藏层
    function ShowDiv(show_div,bg_div){
        document.getElementById("oldpassword").value="";
        document.getElementById("newpassword1").value="";
        document.getElementById("newpassword2").value="";
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
     function changepw() {
         var oldpassword = document.getElementById("oldpassword").value;
         var password1 = document.getElementById("newpassword1").value;
         var password2 = document.getElementById("newpassword2").value;
         if (password1.length < 6) {
             alert("密码不能少于六位");
             return;
         }
         else if (password1.length > 18) {
             alert("密码不能多余18位");
             return;
         }
         else if (password1 != password2) {
             alert("两次输入的密码不一致");
         }
         else {
             $.ajax({
                 url: "/IoServlet.do",
                 type: "post",
                 data: {method: "changepwd",joldpassword:oldpassword, jpassword: password1},
                 success: function (msg) {
                     document.getElementById("oldpassword").value="";
                     document.getElementById("newpassword1").value="";
                     document.getElementById("newpassword2").value="";
                     alert(msg);
                 },
                 error: function () {
                     alert("数据获取失败");
                 }
             });
         }
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
                 document.getElementById("infologo").value = srg;
                 // alert(srg);
                 // temp.src = srg;
             },
             error: function () {
             }
         });
     }
</script>
</body>
</html>