<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>可乐网-申请加入活动</title>
		<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	    <link rel="stylesheet" type="text/css" href="/css/topside.css">
	    <link rel="stylesheet" type="text/css" href="/css/table.css">
	</head>
	 <body>


	 <jsp:include page="/common/AfterLoginTittle.jsp"/>
	 <form action="/AddCommunity.do" method="post" id="subform">
		 <input type="hidden" name="method" value="addActivity">
		</div>
	 	<div class="mainbox">
			<table>
				<tr><th colspan="3">活动申请表</th></tr>
				<tr>
					<td>姓名</td>
					<td><input type="text" style="border:0;background-color:white"disabled="false" value="${loginStudent.getName()}"/></td>
					<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><input type="text" style="border:0;background-color:white"disabled="false" value="${loginStudent.getSex()}"/></td>
				</tr>
				<tr><td>学校</td>
					<td><input type="text" style="border:0;background-color:white"disabled="false" value="${loginStudent.getSchool()}"/></td>
					<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
				</tr>
				<tr>
					<td>年级</td>
					<td><input type="text" style="background-color:white" name = "grade" id = "grade"/></td>
				</tr>
				<tr>
					<td colspan="2">兴趣爱好及特长</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" warp="virtual" name = "hobby" id = "hobby"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">申请的原因</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="3" warp="virtual" name = "reason" id = "reason"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">联系方式</td>
				</tr>
				<tr>
					<td colspan="2"><input type="text"  value="${loginStudent.getContact()}" name = "contact" id = "contact"/></td>
					<td style="padding: 0px;"><div class="attention-box"><div class="attention">!</div><div class="p">不能为空</div></div></td>
				</tr>
				<tr>
				<td></td>
					<td>
						<button class="table-button" type="button" style="float:right" onclick="back()">取消</button>
						<button class="table-button" type="button" style="float:right" onclick="subapply()">提交</button>
					</td>
				</tr>
			</table>
			<script type="text/javascript">
                function back() {

                    history.go(-1);
                }
				function subapply() {
                    var grade = document.getElementById("grade").value;
                    var hobby = document.getElementById("hobby").value;
                    var reason = document.getElementById("reason").value;
                    var contact = document.getElementById("contact").value;
                    if(0 == grade.length)
					{
					    alert("请输入年级")
					    return;
					}
                    if(0 == hobby.length)
                    {
                        alert("请输入爱好")
                        return;
                    }
                    if(0 == reason.length)
                    {
                        alert("请输入原因")
                        return;
                    }
                    if(0 == contact.length)
                    {
                        alert("请输入联系方式")
                        return;
                    }
                    document.getElementById("subform").submit();
                }
				// 信息不能为空 作者：陆祎洲
				// 只针对特定网页
				window.onload=function(){
					var input = document.getElementsByTagName('input');
					var text = document.getElementsByTagName('textarea');
					for (var i = input.length - 1; i >= 0; i--) {
						// 更改针对性请在下方修改
						if (i==0||i==2||i==3||i==4) {
							input[i].onfocus=function(){
								// 选择节点
								var attention = this.parentElement.parentElement.lastElementChild.children[0];
								if (this.value=="") {
									attention.style.visibility = "visible";
								}
							}
							input[i].onchange=function(){
								// 选择节点
								var attention = this.parentElement.parentElement.lastElementChild.children[0];
								if (this.value!="") {
									attention.style.visibility = "hidden";
								}
							}
						}
					}
				}
			</script>
	 	</div>
	 </form>
	 </body>
</html>