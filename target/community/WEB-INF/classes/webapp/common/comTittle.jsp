<%--
  Created by IntelliJ IDEA.
  User: 黄友明
  Date: 2018/7/11
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/mapages.do" method="post" id = "comForm">
    <%
        String pos =(String) request.getSession().getAttribute("pos");
        if(pos.equals("not"))
        {
    %>
    <input type="checkbox" id="menu">
    <label class="menu" for="menu">
        <span></span>
        <span></span>
    </label>
    <ul class="drawer" style="padding-top:50px">
    <li>
        <a href = "/mainpages/shouye.jsp">
            <i class="0"></i>
            <span>社团首页</span>
        </a>
        <ul>
            <li>
                <a>
                    <i class="0.1"></i>
                    <span id="xiugai">修改主页</span>
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a>
            <i class="1"></i>
            <span>消息发布</span>
        </a>
        <ul>
            <li>
                <a href = "/assignment/reportnew.jsp">
                    <i class="1.1"></i>
                    <span>社团招新</span>
                </a>
            </li>
            <li>
                <a href = "/assignment/reportwork.jsp">

                    <i class="1.2"></i>
                    <span>社团活动</span>
                </a>
            </li>
        </ul>
    </li>
    <li  >
      <%--  onclick="choose('fenpeirenwu')"--%>
        <a onclick="choose('fenpeirenwu')">
            <i class="2" ></i>
            <span>任务分配</span>
        </a>

    </li>
        <li>
            <a>
                <i class="3"></i>
                <span>申请审核</span>
            </a>
            <ul>
                <li>
                    <a onclick="choose('reviewJoin_Apply')">
                        <i class="1.1"></i>
                        <span>招新审核</span>
                    </a>
                </li>
                <li>
                    <a onclick="choose('reviewActivity_Join')">
                        <i class="1.2"></i>
                        <span>活动审核</span>
                    </a>
                </li>
            </ul>
        </li>
    <%--<li>
        <a onclick="choose('reviewJoin_Apply')">
            <i class="3"></i>
            <span>申请审核</span>
        </a>
    </li>--%>
    <li>
        <a onclick="choose('showClubInfo')">
            <i class="4"></i>
            <span>社团信息</span>
        </a>
    </li>
    <li>
        <a onclick="choose('reviewFunds')">
            <i class="5"></i>
            <span>资金管理</span>
        </a>
    </li>
    <li>
        <a  onclick="choose('showMember')">
            <i class="6"></i>
            <span>成员管理</span>
        </a>
    </li>
    <li>
        <a>
            <i class="7"></i>
            <span onclick="ShowDiv('tuichu','fade')">退出社团</span>
        </a>
    </li>
</ul>
    <%
        }
        if(pos.equals("common"))
        {
    %>
    <input type="checkbox" id="menu">
    <label class="menu" for="menu">
        <span></span>
        <span></span>
    </label>
    <ul class="drawer" style="padding-top:50px">
        <li>
            <a href = "/mainpages/shouye.jsp">
                <i class="0"></i>
                <span>社团首页</span>
            </a>
        </li>
        <li>
            <a>
                <i class="7"></i>
                <span onclick="ShowDiv('tuichu','fade')">退出社团</span>
            </a>
        </li>
    </ul>
    <%
        }
    %>
    <script>
        function choose(method) {
            document.getElementById("comMethod").value = method;
            document.getElementById("comForm").submit();
        }
    </script>
    <input name="method" type="hidden" value="" id = "comMethod">
</form>