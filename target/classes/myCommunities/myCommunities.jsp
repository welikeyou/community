<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>

<!DOCTYPE html>
<%--
功能:申请创建社团
创建者:陆祎洲,董淑媛
修改者:黄友明,刘诗滢
--%>
<html>
<head>
	<link rel="icon" href="/pic/logo.png" type="image/x-icon"/>
	<title>可乐网-我的社团</title>
	<link rel="stylesheet" href="/myCommunities/mine.css" type="text/css"/>
	<link rel="stylesheet" href="/common/topside.css" type="text/css"/>
</head>
<body>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>


	</div>
	<div class="mainbox" id="mainbox" style="display: flex;flex-direction: column;align-items: center;">
		<div class="content" style="width: 50%;">
			<div class="dropdown">
				<div class="dropdown-head">
					<img src="/pic/triangle.png" style="width: 15px;height: 12px;">
					<p style="margin: 2px;">我加入的社团</p>
				</div>

				<table class="list" id="join">
					<th></th>
					<%
                       @SuppressWarnings("unchecked") 
                       HashMap<String,String> map=(HashMap<String,String>)request.getAttribute("personalAssociations");
                                if(map==null){%>
                             <tr><td>没有加入社团</td></tr>
                               <%}else{
                                Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                               while (iterator.hasNext()) {
	                           Map.Entry<String, String> entry = iterator.next();
	                           String associationNum=(String)entry.getKey();
	                          String associationName=(String)entry.getValue();
	                          %>
					<tr>
						<td><%=associationName %></td>
					</tr>
					 <%
                      }
                      }
                %>
				</table>

				<%-- <script>
        function view(id,method) {
            document.getElementById("method").value = method;
            document.getElementById("viewDetail").value = id;
            document.getElementById("form").submit();
        }
    </script>--%>
			</div>
			<div class="dropdown">
				<div class="dropdown-head">
					<img src="/pic/triangle.png" style="width: 15px;height: 12px;">
					<p style="margin: 2px;">我管理的社团</p>
				</div>
				<table class="list" id="manage">
					<th></th>
					<tr>
						<%--<td>美术社</td>--%>
					</tr>
				</table>
			</div>
			<div class="dropdown">
				<div class="dropdown-head">
					<img src="/pic/triangle.png" style="width: 15px;height: 12px;">
					<p style="margin: 2px;">我创建的社团</p>
				</div>
				<form action="/mapages.do" id="estform" method="post">
					<input type="hidden" name="method" value="estCom">
				<table class="list" id="establish">
					<th></th>
					<tr>
						<%--<td>美术社</td>--%>
					</tr>
					<tr>

						<td><button type="submit">创建社团</button></td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>