<%@ page import="com.java.model.Member" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>可乐网-社团成员</title>
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/css/member.css">
	<link rel="stylesheet" type="text/css" href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
</head>
<body>
<jsp:include page="/common/AfterLoginTittle.jsp"/>
</div>
<jsp:include page="/common/comTittle.jsp"/>
<form action="/manageMember.do" method="post" name="mangemember">
	<input type="hidden" id="method" name="method" >
	<input type="hidden" id="stuNum" name="stuNum" value="">
	<div class="mainbox">

		<div id="buzhang">
			<table class="uncheck">
				<!-- 表头 -->
				<thead>
				<tr>
					<th colspan="6">部长列表</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="first-ckall" name="ubox" onclick="checkall('first-ckall','.first-ck')" />部长</td>
					<td>姓名</td><td>性别</td><td>所属部门</td>
				</tr>
				</thead>
				<tbody class="scrolltbody" style="height:150px;">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%List<Member> buZhang=(List<Member>)request.getAttribute("allBuZhang");%>
				<%
					int num1=buZhang.size();
					for(int i=0;i<num1 && buZhang != null;i++)
					{%>
				<tr class="first-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box" value=<%=buZhang.get(i).getStudentNum()%>></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buZhang.get(i).getResult()%>)"><%=buZhang.get(i).getStudentName()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buZhang.get(i).getResult()%>)"><%=buZhang.get(i).getSex()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buZhang.get(i).getResult()%>)"><%=buZhang.get(i).getBuMen()%></p></td>
				</tr>
				<% }%>
				<!-- 循环体结束 -->
				</tbody>
				<tbody>
				<tr>
					<td style="float: right;" colspan="3">
						<select style="width: 60px;border: 1px #007bd9 solid;height: 30px;font-size: 16px;margin-top:8px" id="selectZhiWei1" onchange="changeZhiWei(this.id)">
							<option value="buzhang">部长</option>
							<option value="fubu">副部</option>
							<option value="chengyuan">成员</option>
						</select>
						<button style="float: right;" class="table-button" id="red1" type="button" onclick="deleteMem()">删除成员</button></td>
				</tr>
				</tbody>
			</table>
		</div>

		<div id="fubu">
			<table class="uncheck">
				<!-- 表头 -->
				<thead>
				<tr>
					<th colspan="6">副部列表</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="second-ckall" name="ubox" onclick="checkall('second-ckall','.second-ck')" />副部</td>
					<td>姓名</td><td>性别</td><td>所属部门</td>
				</tr>
				</thead>
				<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%List<Member> fuBu=(List<Member>)request.getAttribute("allFuBu");%>
				<%int num2=fuBu.size();
					for(int i=0;i<num2 && fuBu != null;i++)
					{%>
				<tr class="second-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box" value=<%=fuBu.get(i).getStudentNum()%>></td>
					<td><p onclick="ShowDiv1('details','fade',<%=fuBu.get(i).getResult()%>)"><%=fuBu.get(i).getStudentName()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=fuBu.get(i).getResult()%>)"><%=fuBu.get(i).getSex()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=fuBu.get(i).getResult()%>)"><%=fuBu.get(i).getBuMen()%></p></td>
				</tr>
				<!-- 循环体结束 -->
				<% }%>
				</tbody>
				<tbody>
				<tr>
					<td style="float: right;" colspan="3">
						<select style="width: 60px;border: 1px #007bd9 solid;height: 30px;font-size: 16px;margin-top:8px"id="selectZhiWei2" onchange="changeZhiWei(this.id)">
							<option value="buzhang">部长</option>
							<option value="fubu"selected="selected">副部</option>
							<option value="chengyuan">成员</option>
						</select>
						<button style="float: right;" class="table-button" id="red2" type="button" onclick="deleteMem()">删除成员</button></td>
				</tr>
				</tbody>
			</table>
		</div>

		<div id="chengyuan">
			<table class="uncheck">
				<!-- 表头 -->
				<thead>
				<tr>
					<th colspan="6">部员列表</th>
				</tr>
				<tr style="font-weight: 900;">
					<td><input type="checkbox" id="third-ckall" name="ubox" onclick="checkall('third-ckall','.third-ck')" />部员</td>
					<td>姓名</td><td>性别</td><td>所属部门</td>
				</tr>
				</thead>
				<tbody class="scrolltbody">
				<!-- 未审核申请列表 -->
				<!-- 循环体 -->
				<%List<Member> buWei=(List<Member>)request.getAttribute("allBuWei");%>
				<%
					int num3=buWei.size();
					for(int i=0;i<num3 && buWei != null;i++)
					{%>
				<tr class="third-ck">
					<td style="padding-top: 20px;"><input type="checkbox" name="box" value=<%=buWei.get(i).getStudentNum()%>></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buWei.get(i).getResult()%>)"><%=buWei.get(i).getStudentName()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buWei.get(i).getResult()%>)"><%=buWei.get(i).getSex()%></p></td>
					<td><p onclick="ShowDiv1('details','fade',<%=buWei.get(i).getResult()%>)"><%=buWei.get(i).getBuMen()%></p></td>
				</tr>
				<!-- 循环体结束 -->
				<% }%>
				</tbody>
				<tbody>
				<tr>
					<td style="float: right;" colspan="3">
						<select style="width: 60px;border: 1px #007bd9 solid;height: 30px;font-size: 16px;margin-top:8px" id="selectZhiWei3" onchange="changeZhiWei(this.id)">
							<option value="buzhang">部长</option>
							<option value="fubu">副部</option>
							<option value="chengyuan" selected="selected">成员</option>
						</select>
						<button style="float: right;" class="table-button" id="red3" type="button" onclick="deleteMem()">删除成员</button></td>
				</tr>
				</tbody>
			</table>
		</div>

	</div>
</form>
<!-- 全选函数 -->
<script type="text/javascript">
	function checkall(flag_id,allck){
		var flag=document.getElementById(flag_id).checked;
		var trs = document.querySelectorAll(allck);
		for (var i = trs.length - 1; i >= 0; i--) {
			trs[i].children[0].children[0].checked = flag;
		}
	}
</script>

<div id="fade" class="black_overlay">
</div>

<div id="details" class="white_content">
	<div>
		<span onclick="CloseDiv('details','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
	</div>
	<!-- 弹出详细信息表单 -->
	<table class="details">
		<form action="/manageMember.do" method="post" name="tanchuangForm">
			<input type="hidden" value="" id="stuId" name="stuNum">
			<input type="hidden" value="" name="method" id="tanchuangMethod">
		</form>

		<tr>
			<td>姓名</td><td id="name"></td>
		</tr>
		<tr>
			<td>性别</td><td id="sex"></td>
		</tr>
		<tr>
			<td>生日</td><td id="birthday"></td>
		</tr>
		<tr>
			<td>联系方式</td><td id="contact"></td>
		</tr>
		<tr>
			<td>学校</td><td id="school"></td>
		</tr>
		<tr>
			<td>地址</td><td id="address"></td>
		</tr>
		<tr>
			<td>兴趣爱好</td><td><textarea cols="30" rows="3" style="border:0;background-color:white" id="hobby"></textarea></td>
		</tr>
		<tr>
			<td>个人签名</td><td><textarea cols="30" rows="3" style="border:0;background-color:white" id="slogan"></textarea></td>
		</tr>
		<tr>
			<td style="padding-top: 18px;">职务：
				<select style="width: 100px;border: 1px #007bd9 solid;height: 24px;font-size: 16px;" id="selectZhiWei4" onchange="changeZhiWeiByOne(this.id)">
					<option value="buzhang">部长</option>
					<option value="fubu">副部</option>
					<option value="chengyuan">成员</option>
				</select>
			</td>
			<td style="float: right;">
				<button class="table-button" id="red" type="button" onclick="deleteMemByOne()">删除成员</button>
			</td>
		</tr>
	</table>
</div>
<!-- 弹窗函数 -->
<script type="text/javascript">

	//弹出隐藏层
	function ShowDiv1(show_div,bg_div,ob){
		var w=JSON.stringify(ob);
		var y=""+w+"";
		var s=JSON.parse(y);
		document.getElementById("stuId").value=s.stuNum;
		document.getElementById("name").innerText=s.stuName;
		document.getElementById("sex").innerText=s.stuSex;
		document.getElementById("birthday").innerText=s.stuBirthday;
		document.getElementById("contact").innerText=s.stuContact;
		document.getElementById("school").innerText=s.stuSchool;
		document.getElementById("address").innerText=s.stuAddress;
		document.getElementById("hobby").innerText=s.stuHobby;
		document.getElementById("slogan").innerText=s.stuSlogan;

		document.getElementById(show_div).style.display='block';
		document.getElementById(show_div).style.top = window.scrollY + 'px';
		document.getElementById(bg_div).style.display='block' ;
		var bgdiv = document.getElementById(bg_div);
		bgdiv.style.width = document.body.scrollWidth;
		var _class = event.srcElement.parentElement.parentElement.className;
		var select = document.getElementsByTagName('select');
		if (_class=='first-ck') {
			select[3].options[0].selected="selected";
		}
		else if (_class=='second-ck') {
			select[3].options[1].selected="selected";
		}
		else if (_class=='third-ck') {
			select[3].options[2].selected="selected";
		}
	}
	//关闭弹出层
	function CloseDiv(show_div,bg_div)
	{
		document.getElementById(show_div).style.display='none';
		document.getElementById(bg_div).style.display='none';
	}

	function deleteMem() {
		bobj = document.getElementsByName("box");
		check_val = [];
		for(k in bobj){
			if(bobj[k].checked)
				check_val.push(bobj[k].value);
		}
		document.getElementById("method").value="deleteMember";
		document.getElementById("stuNum").value=check_val;
		mangemember.submit();
	}
	function deleteMemByOne(ob) {

		document.getElementById("tanchuangMethod").value="deleteMember";
		tanchuangForm.submit();
	}

	function changeZhiWei(selectId)
	{
		bobj = document.getElementsByName("box");
		check_val = [];
		for(k in bobj){
			if(bobj[k].checked)
				check_val.push(bobj[k].value);
		}
		document.getElementById("stuNum").value=check_val;

		var obj = document.getElementById(selectId); //定位id
		var index = obj.selectedIndex; // 选中索引
		var zhiWei = obj.options[index].value; // 选中值
		if(zhiWei=="buzhang")
		{
			document.getElementById("method").value="toBuZhang";
		}
		else if(zhiWei=="fubu")
		{
			document.getElementById("method").value="toFuBu";
		}
		else{ document.getElementById("method").value="toBuWei";}

		mangemember.submit();
	}

	function changeZhiWeiByOne(selectId) {
		var obj = document.getElementById(selectId); //定位id
		var index = obj.selectedIndex; // 选中索引
		var zhiWei = obj.options[index].value; // 选中值
		if(zhiWei=="buzhang")
		{
			document.getElementById("tanchuangMethod").value="toBuZhang";
		}
		else if(zhiWei=="fubu")
		{
			document.getElementById("tanchuangMethod").value="toFuBu";
		}
		else{ document.getElementById("tanchuangMethod").value="toBuWei";}

		tanchuangForm.submit();

	}
</script>
<jsp:include page="/common/comExit.jsp"/>
</body>
</html>