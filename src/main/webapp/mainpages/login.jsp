 <!--轮播、表格：董淑媛 ，整合：陆祎洲-->
 <!DOCTYPE html>
 <%@ page isELIgnored="false" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
 <%
 String path = request.getRequestURI();
		String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path;
		%>
     <base href="<%=basePath%>">
  <meta charset="UTF-8">
  <title>可乐网-打开可乐，发现惊喜</title>
  <link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
  <link rel="stylesheet" type="text/css" href="/css/login.css">
  <link rel="stylesheet" type="text/css" href="/css/lunbo.css">
  <link rel="stylesheet" type="text/css" href="/css/topside1.css">
  
  <script type="text/javascript" src="/js/lunbo.js"></script>
  <script type="text/javascript" src="/js/login.js"></script>
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>

<body style="height: 100%;background-image:url('/pic/bg.png');">
  <!--导航界面-->
  <div class="topside">
    <div class="logo"><img src="/pic/logo_withe.png"></div>
    <div class="sectionlist">
      <button class="section" id="section-header" onclick="Tomain()" type="button">首页</button>
      <button class="section" id="section-recruit" onclick="act('viewbeCommunities')" type="button">社团招新</button>
      <button class="section" id="section-activity" onclick="act('viewbeActivities')" type="button">社团活动</button>
      <button class="section" id="section-login" onclick="toLogin()" type="button">登录</button>
    </div>

  </div>
  <form action="/mapages.do" method="post" id="topform">
      <input type="hidden" name="method" value="" id = "methodtop">
  </form>
  <!-- 右侧信息栏 -->
  <!-- <div class="info-bar">
    <p>About us</p>
    <p>info...</p>
    <p>info...</p>
    <button class="joinus">join us</button>
  </div> -->
  <!--<div class="smallcolorblock1"></div> -->  
  <!--创作者：董淑媛 功能：实现主页面图片轮播-->
  <div class="pptbox">
    <div id="box">
      <ul>
        <li><a href="javascript:;"><img src="/pic/1.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/2.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/3.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/4.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/5.jpg" alt=""></a></li>
      </ul>
    </div>
    <div class = "buttons">
      <button id="first"></button>
      <button id="second"></button>
      <button id="third"></button>
      <button id="fourth"></button>
      <button id="fifth"></button>
    </div>
  </div>
  <!-- 页脚 -->
  <div class="webfoot">
    <table style="width: 90%;">
      <tr><th style="float: left;">关于我们</th></tr>
      <tr><td>团队成员</td><td></td><td>联系与合作</td><td></td></tr>
    </table>
  </div>

  <!-- 登录弹窗 作者：董淑媛，陆祎洲 -->
  <!--弹出时背景层DIV-->
  <div id="fade" class="black_overlay"></div>

  <!--弹出登录表格-->
  <div id="logintable" class="white_content">
   <div class="cancel" onclick="Cancel()"><p class="cancel" style="margin-bottom: 2px;">×</p></div>
   <div style="margin-left: 40%;margin-top: 10%;"><img src="/pic/logo_blue.png"></div>
   <!--表单-->
   <form action="/IoServlet.do" method="post" name="loginform">
   <input type="hidden" name="method" value="login">
   <table>
    <tr><td>帐号</td></tr>
    <tr><td><input type="text" id="account" name="account" placeholder="请输入账号" style="height:25px;width:200px;" /></td></tr>
    <tr><td>密码</td></tr>
    <tr><td><input type="password" id="password" name="password" placeholder="请输入密码" style="height:25px;width:200px;" /><td></tr>
      <tr><td>
        <button class="div-button" id="login" onclick="Login()" type="button">登录</button>
      </td></tr>
      <tr><td>
        <button class="div-button" onclick="Sign()" type="button">注册</button>
      </td></tr>
    </table>
   </form>
  </div>

  <!--弹出注册表格-->
  <div id="signtable" class="white_content">
    <div class="cancel" onclick="Cancel()"><p class="cancel" style="margin-bottom: 2px;">×</p></div>
    <div style="margin-left: 40%;margin-top: 10%;"><img src="/pic/logo_blue.png"></div>
    <!--表单-->
	<form action="/IoServlet.do" method="post" name="registerform">
	<input type="hidden" name="method" value="register">
    <table>
      <tr><td>手机号</td></tr>
      <tr><td><input type="text" placeholder="请输入手机号" id="regaccount" name="egaccount" style="height:25px;width:200px;" /></td></tr>
      <tr><td>密码</td></tr>
      <tr><td><input type="password" placeholder="请输入密码" id="regpassword1" name="egpassword1" style="height:25px;width:200px;" /></td></tr>
      <tr><td>
        <button class="div-button" onclick="Register()" type="button">完成</button>
      </td></tr>
      <tr><td>
        <button class="div-button" id="cancel" onclick="Back()" type="button">已有帐号？ 直接登录</button>
      </td></tr>
    </table>
	</form>
  </div>
  
  <script>

      function Tomain() {
          location.href = "/mainpages/login.jsp";
      }
      function act(method) {
          document.getElementById("methodtop").value = method;
          document.getElementById("topform").submit();
      }


	function Login()
	{
	var account= document.getElementById("account").value;
	var password1=document.getElementById("password").value;
	if(account == null || account == "")
    {
        alert("请输入账号")
        return;
    }
    if(password1 == null || password1 == "")
    {
        alert("请输入密码")
        return;
    }
	$.ajax({
	url: "/IoServlet.do",
	type: "post",
	data: {method: "login", jaccount: account,jpassword: password1},
	success: function (msg) {
	    if(msg != null && msg != "" )
	    alert(msg);
	    else
            window.location = "/mainpages/afterlogin.jsp";
	   /*;; var check = "登录成功"var ms = msg
	    if("登录成功" == ms)
	   ;     window.location = "/mainpages/afterlogin.jsp";*/
	/*if("false"==msg)
	{
	alert("账号或密码错误");
	}*/
	},
	error: function () {
	alert("数据获取失败");
	}
	});
	//loginform.submit();
	}

	function Register() {
        var account= document.getElementById("regaccount").value;
        var password1=document.getElementById("regpassword1").value;
      //  var password2=document.getElementById("regpassword2").value;
        var regBox = {
            regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
            regTel : /^0[\d]{2,3}-[\d]{7,8}$/
        }
        var mflag = regBox.regMobile.test(account);
        var tflag = regBox.regTel.test(account);
        if(!(mflag||tflag))
        {
            alert("请输入合法电话号码");
            return;
        }
        else if(password1.length<6)
        {
            alert("密码不能少于六位");
            return;
        }
        else if(password1.length>18)
        {
            alert("密码不能多余18位");
            return;
        }
       /* else if(password1!=password2)
        {
            alert("两次输入的密码不一致");
        }*/
        $.ajax({
            url:"/IoServlet.do",
            type :"post",
            data:{method:"register",jaccount:account,jpassword:password1},
            success: function (msg){
               // alert("获得消息成功");
               // var check = "true";
                if(msg != "" && msg != null)
                {
                    alert(msg);
                }
                else
                {
                    alert("注册成功");
                    CloseDiv("signtable","fade");
                    ShowDiv('logintable','fade');
                }
            },
            error: function () {
                alert("数据获取失败");
            }
        });
var login_win = document.getElementById('logintable');
var sign_win = document.getElementById('signtable');
var bg = document.getElementById('fade');
sign_win.style.animation='table-out 0.5s 0.2s forwards';
//sign_win.style.display='none';

login_win.style.display='block';
login_win.style.animation='table-in 0.5s 0.4s forwards';
	}
	</script>
</body>
</html>