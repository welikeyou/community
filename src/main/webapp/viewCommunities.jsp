<%--
  Created by IntelliJ IDEA.
  User: 黄友明
  Date: 2018/7/3
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String[][] stringss =(String[][]) request.getAttribute("communities");
%>
<form action="AddCommunity.do" method="post" id = "form">
    <table>
        <tr>
            <td>社团名称</td>
            <td>社团类型</td>
            <td>社团招新标语</td>
            <td>查看操作</td>
            <td>添加操作</td>
        </tr>

        <%

        for(String[] strings:stringss)
        {
    %>
        <tr>
            <td><%=strings[0]%></td>
            <td><%=strings[1]%></td>
            <td><%=strings[2]%></td>
            <td><button onclick="view('<%=strings[3]%>','<%="viewDetail"%>')">查看详细信息</button></td>
            <td><button onclick="view('<%=strings[3]%>','<%="applyCommunity"%>')">申请加入社团</button></td>
        </tr>
        <%
            }
        %>
    </table>
    <input type="hidden" name="method" value="" id = "method">
    <input type="hidden" name="community" value="" id = "viewDetail">
    <script>
        function view(id,method) {
            document.getElementById("method").value = method;
            document.getElementById("viewDetail").value = id;
            document.getElementById("form").submit;
        }
        function view2(method) {
            document.getElementById("method").value = method;

            document.getElementById("form").submit;
        }
    </script>
    <button onclick="view2('<%="viewapplies"%>')>查看申请</button>
</form>
</body>
</html>
