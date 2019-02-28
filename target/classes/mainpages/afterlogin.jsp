 <!--轮播、表格：董淑媛 ，整合：陆祎洲-->
 <!DOCTYPE html>
 <html>
 <%@ page isELIgnored="false" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <head>
  <meta charset="UTF-8">
  <title>可乐网-打开可乐，发现惊喜</title>
  <link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
  <link rel="stylesheet" type="text/css" href="/css/login.css">
  <link rel="stylesheet" type="text/css" href="/css/lunbo.css">
  <link rel="stylesheet" type="text/css" href="/css/topside.css">
  <script type="text/javascript" src="/js/login.js"></script>
  <script type="text/javascript" src="/js/lunbo.js"></script>
</head>

<body style="background-image:url('/pic/bg.png')">
 <%-- <div class="mainbox">
    <div class="topside">
      <div class="logo"><img src="/pic/logo_blue.png"></div>
      <div class="sectionlist">
        <button class="section" id="section-header">首页</button>
        <button class="section" id="section-recruit">社团招新</button>
        <button class="section" id="section-activity">社团活动</button>
        <button class="section" id="section-mine">我的社团</button>
        <button class="section" id="section-personal">个人中心</button>
        <div class="head" id="head">
          <img src="pic/lemon.png" style="">
          <p>你好：XXX</p>
        </div>
      </div>
    </div>--%>
 <jsp:include page="/common/AfterLoginTittle.jsp"/>
    <!-- 右侧信息栏 -->
 <!--    <div class="info-bar">
      <p>About us</p>
      <p>info...</p>
      <p>info...</p>
      <button id="joinus">join us</button>
    </div> -->
    <!--轮播界面-->
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
    <!--创作者：董淑媛 功能：实现主页面图片轮播-->
  </div>
</body>
</html>