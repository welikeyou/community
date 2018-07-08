<%@ page import="com.java.model.Student" %>
<!--轮播、表格：董淑媛 ，整合：陆祎洲,修改:黄友明-->
 <!DOCTYPE html>
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
  <link rel="icon" href="/pic/logo.png" type="image/x-icon"/>
  
  <link rel="stylesheet" type="text/css" href="/common/login.css">
  <link rel="stylesheet" type="text/css" href="/common/topside.css">
  <link rel="stylesheet" type="text/css" href="/common/lunbo.css">
  <link rel="stylesheet" type="text/css" href="/common/mainpage.css" />

  <script type="text/javascript" src="/common/mainpage.js"></script>
  <script type="text/javascript" src="/common/login.js"></script>
  <script type="text/javascript" src="/common/lunbo.js"></script>
</head>

<body>

    <jsp:include page="/common/AfterLoginTittle.jsp"/>
    <div class="pptbox">
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
        <tr>
          <td>账号：</td><td><input type="text" style="height:25px;width:150px;" /></td>
        </tr>
        <tr>
          <td>密码：</td><td><input type="text" style="height:25px;width:150px;" /></td>
        </tr>
        <tr style="column-span: 2;">
          <td><button type="button" style="">登录</button></td>
          <td><button type="button" style="border:1px solid #66331f ">注册</button></td>
        </tr>
      </table>
    </div>

    <!--弹出注册表格-->
    <div id="signtable" class="white_content">
      <div style="text-align: right; cursor: default; height: 40px;">
        <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('signtable','fade')">关闭</span>
      </div>
      <table>
        <tr>
          <td>手机号：</td><td><input type="text" style="height:25px;width:150px;" />
          </td>
        </tr>
        <tr>
          <td>密码：</td><td><input type="password" style="height:25px;width:150px;" />
          </td>
        </tr>
        <tr style="column-span: 2;">
          <td><button type="button" style="width: 50px;height: 25px;">登录</button></td>
          <td><button type="button" style="width: 50px;height: 25px;">取消</button></td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>