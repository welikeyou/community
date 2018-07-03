<%--
  Created by IntelliJ IDEA.
  User: 黄友明
  Date: 2018/7/3
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="AddCommunity.do" method="post">
    <%
        String[] strings = (String[])request.getAttribute("viewDetail");
    %>
    社团名称 <%=strings[0]%>
    社长 <%=strings[1]%>
    类型 <%=strings[2]%>
    迎新标语 <%=strings[3]%>
    社团成员人数 <%=strings[4]%>
    社团人数上限 <%=strings[5]%>
    社团级别 <%=strings[6]%>
    社团资金  <%=strings[7]%>
</form>
</body>
</html>
