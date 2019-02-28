<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-申请创建社团</title>
	<link rel="stylesheet" href="/css/topside.css" type="text/css"/>
	<link rel="stylesheet" href="/css/table.css" type="text/css"/>
	<script type="text/javascript" src="/js/upload.js"></script>
</head>
<body>

	<jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>
	<form action="/mapages.do" id="subform" method="post">
		<input type="hidden" name="method" value="AplEstCom">
	<div class="mainbox">
		<div class="out-table">
			<table>
				<tr><th colspan="6">社团建立申请</th></tr>
				<tr>
					<th style="border: none; background-color: #12C20E;color: #fff;">社团基础</th>
					<th style="border: none; background-color: #12C20E;color: #fff;">社团规划</th>
					<th style="border: none; background-color: #12C20E;color: #fff;">社团文化</th>
				</tr>
				<tr>
					<!-- 社团基础表单 -->
					<td style="padding-left: 0px;padding-right: 15px">
						<table class="inner-table">
							<tr><td>创建者姓名</td><td><input type="text" name="" value="${loginStudent.getName()}" disabled="false"style="border:0;background-color:white;"></td></tr>
							<tr>
								<td>性别</td>
								<td>
								<input type="text" name="" value="${loginStudent.getSex()}" disabled="false"style="border:0;background-color:white;">
								</td>
							</tr>
							<tr><td>出生日期</td><td><input type="text" name="" value="${loginStudent.getBirthday()}" disabled="false"style="border:0;background-color:white;"></td></tr>
							<tr><td>社团名称</td><td><input type="text" name="name" id = "name" onchange="checkComName()"></td></tr>
							<tr>
								<td>社团级别</td>
								<td>
									<!-- 可以自行更改name值，但必须同一个单选框的name相同，即如性别选项都必须是sex -->
									<input type="radio" name="level" value = "年级" ><span>年级</span>
									<input type="radio" name="level" value = "校级" ><span>校级</span>
								</td>
							</tr>
							<tr><td>社团类型</td><td><input type="text" name="type" id="type" placeholder="如：艺术，学术，体育等"></td></tr>
							<tr><td>社团宗旨</td><td></td></tr>
							<tr><td colspan="2"><input type="text" name="aim" id="aim"></td></tr>
							<tr><td>联系电话</td><td><input type="text" name="contact" id = "contact" value = "${loginStudent.getContact()}"></td></tr>
							<tr>
								<td>社团logo</td>
								<td style="display: flex;flex-direction: column;">
									<img id="cvs" class="logo"width="120px"height="120px"style="padding-left:0"></img>
									<span class="upload-but">点此上传logo
										<input type="file" class="upload-pic" id="upload" onchange="uploadfile()"/>
									</span>
								</td>
							</tr>
						</table>
					</td>
					<!-- 社团规划表单 -->
					<td style="padding-left: 0px;padding-right: 15px;">
						<table class="inner-table">
							<tr><td colspan="2">社团简述</td></tr>
							<tr><td><textarea cols="50" rows="10" maxlength="500" placeholder="不超过200字" name = "introdution" id = "introdution"></textarea></td></tr>
							<tr><td colspan="2">主要活动地点</td></tr>
							<tr><td colspan="2"><input type="text" name="address" id = "address"></td></tr>
							<tr><td colspan="2">发展规划</td></tr>
							<tr><td><textarea cols="50" rows="15" maxlength="500" placeholder="不超过300字" name = "plan" id = "plan"></textarea></td></tr>
						</table>
					</td>
					<!-- 社团文化表单 -->
					<td style="padding-left: 0px;padding-right: 15px;">
						<table class="inner-table">
							<tr><td colspan="2">初始成员及简介</td></tr>
							<tr><td>成员 1 <input type="text" name="member1" value="${loginStudent.getName()}" style="width: 100px;"></td><td>简介<textarea name = "briefintro1" cols="20" rows="3"></textarea></td></tr>
							<tr><td>成员 2 <input type="text" name="member2" style="width: 100px;"></td><td>简介<textarea name = "briefintro2" cols="20" rows="3"></textarea></td></tr>
							<tr><td>成员 3 <input type="text" name="member3" style="width: 100px;"></td><td>简介<textarea name = "briefintro3" cols="20" rows="3"></textarea></td></tr>
							<tr><td>成员 4 <input type="text" name="member4" style="width: 100px;"></td><td>简介<textarea name = "briefintro4" cols="20" rows="3"></textarea></td></tr>
							<tr><td>成员 5 <input type="text" name="member5" style="width: 100px;"></td><td>简介<textarea name = "briefintro5" cols="20" rows="3"></textarea></td></tr>
						</table>
					</td>
				</tr>
				<tr><td colspan="3">
					<button class="table-button" type="button" onclick="back()" id="red"style="float:right">取消</button>
					<button class="table-button" type="button" onclick="subform()" id="green"style="float:right;margin-right:10px;">提交</button>
				</td></tr>
			</table>
		</div>
	</div>
		<input type="hidden" name="logo" value="" id="logo">
<script>
	var is_check = false;
	function subform() {
        var name = document.getElementById("name").value;
        var type = document.getElementById("type").value;
        var aim = document.getElementById("aim").value;
        var contact = document.getElementById("contact").value;
        var introdution = document.getElementById("introdution").value;
        var address = document.getElementById("address").value;
        var plan = document.getElementById("plan").value;
	    if(/*is_check == false ||*/ 0 == name.length)
		{
		    alert("请输入合法的社团名称");
		    return;
		}
		if(0 == type.length)
		{
		    alert("请输入社团类型");
            return;
		}
		if(0 == aim.length)
		{
            alert("请输入社团宗旨");
		    return;
		}
        if(0 == contact.length)
        {
            alert("请输入社团联系方式");
            return;
        }
        if(0 == introdution.length)
        {
            alert("请输入社团介绍");
            return;
        }
        if(0 == address.length)
        {
            alert("请输入社团地址");
            return;
        }
        if(0 == plan.length)
        {
            alert("请输入社团规划");
            return;
        }
		document.getElementById("subform").submit();
    }
	function checkComName() {
		var name = document.getElementById("comName").value;
		$.ajax(
			{
				url:"/AddCommunity.do",
				type:"post",
				data:{method:"checkComName",name:name},
				success:function (msg) {

                }
			});
    }
    function uploadfile() {
        var fileobj = document.getElementById("upload").files[0];
        var form = new FormData();
        form.append("file",fileobj);
        $.ajax({
            url:"/assignment.do?method=upload",
            type :"post",
            data :form,
            dataType:"text",
            contentType: false,
            processData: false,
            success: function (srg) {
                alert("上传成功");
                document.getElementById("logo").value = srg;
                // alert(srg);
                //temp.src = srg;
            },
            error: function () {
                alert("发生了意外的错误,上传失败")
            }
        });
    }
    function back() {
        history.go(-1);
    }
</script>
		</form>
</body>
</html>