<%@ page import="com.java.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: 黄友明
  Date: 2018/7/5
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<div class="mainbox">
    <div class="topside">
        <div class="logo"><img src="/pic/logo_blue.png"></div>
        <div class="sectionlist">
            <form action="/mapages.do" method="post" id="topform">
                <button class="section" id="section-header"    type="button" onclick="Tomain()" >首页</button>
                <button class="section" id="section-recruit"   type="button" onclick="act('viewCommunities')">社团招新</button>
                <button class="section" id="section-activity"  type="button" onclick="act('viewActivities')">社团活动</button>

                <%
                    Student student = (Student) request.getSession().getAttribute("loginStudent");
                    if(student != null)
                    {
                        %>
                <button class="section" id="section-mine"      type="button" onclick="act('viewMyCommunities')">我的社团</button>

                <button class="section" id="section-personal" type="button"  onclick="act('showTask');ShowDiv('safecodeupdate','fade')">个人中心</button>

                <%
                    if(student.getId().equals("13972229703") )
                    {
                %>
                <button class="section" id="section-personal" type="button"  onclick="act('reviewCreate_Apply')">社团审核</button>

                <%
                    }



                    }
                %>
                <button class="section" id="section-personal" type="button"  onclick="Tobefore()">退出登录</button>
                <input type="hidden" name="method" value="" id = "methodtop">
                <script>
                    function Tomain() {
                        location.href = "/mainpages/afterlogin.jsp";
                    }
                    function act(method) {
                        document.getElementById("methodtop").value = method;
                        document.getElementById("topform").submit();
                    }
                    function Tobefore() {
                        location.href = "/mainpages/login.jsp";
                    }
                </script>
            </form>
        </div>
        <div class="head" id="head">
            <img src="${loginStudent.getLogo()}">
            <p>欢迎你：${loginStudent.getName()}</p>
        </div>
    </div>