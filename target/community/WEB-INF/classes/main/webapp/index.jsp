<html>
<body>
<h2>Hello World!</h2>
<form action="AddCommunity.do" method="post">
    <input type="hidden" name="method" value="viewCommunities">
   <%
       request.getSession().setAttribute("stuID","001");
   %>
    <input type="submit">
</form>
</body>
</html>
