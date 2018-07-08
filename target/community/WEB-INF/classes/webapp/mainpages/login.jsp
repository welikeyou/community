 <!--轮播、表格：董淑媛 ，整合：陆祎洲,修改:黄友明,李兰-->
 <!--
    时间：2108/7/5
    任务：前后端整合
    操作者：李兰
    更改区：增加form表单及按钮处理
 -->

 <html>
 <%@ page isELIgnored="false" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <head>
   <%
     String path = request.getRequestURI();
     String basePath = request.getScheme() + "://"
             + request.getServerName() + ":" + request.getServerPort()
             + path;
   %>
   <base href="<%=basePath%>">
  <title>可乐网-打开可乐，发现惊喜</title>
     <link rel="stylesheet" type="text/css" href="/common/login.css">
     <link rel="stylesheet" type="text/css" href="/common/topside.css">
     <link rel="stylesheet" type="text/css" href="/common/lunbo.css">


     <script type="text/javascript" src="/common/lunbo.js"></script>
     <script type="text/javascript" src="/common/login.js"></script>
</head>

<body>
  <div class="topside">
    <div class="logo"><img src="/pic/slogen.png"></div>
    <div class="sectionlist">
        <form action="/mapages.do" method="post" id="topform">
            <button class="section" id="section-header"   onclick="Tomain()">首页</button>
            <button class="section" id="section-recruit"  onclick="act('viewCommunities')">社团招新</button>
            <button class="section" id="section-activity" onclick="act('viewActivities')">社团活动</button>
            <input type="hidden" name="method" value="" id = "methodtop">

            <script>
                function Tomain() {
                    location.href = "/mainpages/login.jsp";
                }
                function act(method) {
                    document.getElementById("methodtop").value = method;
                    document.getElementById("topform").submit();
                }
            </script>
        </form>
    </div>
    <div class="head" id="head">
      <input id="Button1" type="button" value="登录" onclick="toLogin()" />
      <input id="Button2" type="button" value="注册" onclick="toRegister()" />
    </div>
  </div>
  <div class="mainbox">
    <div id="box">
      <ul>
        <li><a href="javascript:;"><img src="/pic/ppt1.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/ppt2.jpg" alt=""></a></li>
        <li><a href="javascript:;"><img src="/pic/ppt3.jpg" alt=""></a></li>
      </ul>
      <a id="prev"><</a>
      <a id="next">></a>
    </div>
  </div>
  <!--创作者：董淑媛 功能：实现主页面图片轮播-->
  <!--弹出时背景层DIV-->
  <div id="fade" class="black_overlay"></div>

  <!--弹出登录表格-->
  <div id="logintable" class="white_content">
    <div style="text-align: right; cursor: default; height: 40px;">
      <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('logintable','fade')">关闭</span>
    </div>
    <table>
        <form action="/IoServlet.do" method="post" name="loginform">
            <input type="hidden" name="method" value="login">
      <tr>
        <td>账号：</td><td><input type="text" id="account" name="account" style="height:25px;width:150px;" /></td>
      </tr>
      <tr>
        <td>密码：</td><td><input type="text" id="password" name="password" value="" style="height:25px;width:150px;" /></td>
      </tr>
        </form>
      <tr style="column-span: 2;">
        <td><button type="button" onclick="Login()" style="">登录</button></td>
        <td><button type="button" onclick="toRegister()" style="border:1px solid #66331f ">注册</button></td>
      </tr>
    </table>
  </div>

  <!--弹出注册表格-->
  <div id="signtable" class="white_content">
    <div style="text-align: right; cursor: default; height: 40px;">
      <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('signtable','fade')">关闭</span>
    </div>
    <table>
        <form action="/IoServlet.do" method="post" name="registerform">
            <input type="hidden" name="method" value="register">
      <tr>
        <td>手机号：</td><td><input type="text"  id="regaccount" name="regaccount" style="height:25px;width:150px;" />
        </td>
      </tr>
      <tr>
        <td>密码：</td><td><input type="password" id="regpassword1" name="regpassword1" style="height:25px;width:150px;" />
        </td>
      </tr>
        <tr>
            <td>确认密码：</td><td><input type="password" id="regpassword2" name="regpassword2"  style="height:25px;width:150px;" />
        </td>
        </tr>
        </form>
      <tr style="column-span: 2;">
        <td><button type="button" onclick="Register()" style="width: 50px;height: 25px;">注册</button></td>
        <td><button type="button" onclick="Cancel()"  style="width: 50px;height: 25px;">取消</button></td>
      </tr>

    </table>
  </div>
<script>
    function toLogin() {
        document.getElementById("password").value=" ";
        ShowDiv('logintable','fade');

    }
    function Login()
    {
        loginform.submit();
    }
    function toRegister()
    {
        CloseDiv("logintable","fade");
        document.getElementById("regaccount").value=" ";
        document.getElementById("regpassword1").value=" ";
        document.getElementById("regpassword2").value=" ";
        ShowDiv("signtable","fade");
    }
    function Register() {
        registerform.submit();
    }
    function Cancel() {
        CloseDiv("signtable","fade");

    }
</script>
</body>
</html>