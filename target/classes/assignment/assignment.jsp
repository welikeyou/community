<!DOCTYPE html>
<%@ page import="com.java.model.Student" %>
<%@ page import="com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="/pic/logo_blue.png" type="image/x-icon"/>
	<title>可乐网-分配任务</title>
	<link rel="stylesheet" type="text/css" href="/css/checkapply.css"/>
	<link rel="stylesheet" type="text/css" href="/css/topside2.css">
	<link rel="stylesheet" type="text/css" href="/css/clubpage2.css">
	<link rel="stylesheet" type="text/css" href="/css/tanchuang.css">
	<link rel="stylesheet" type="text/css" href="/css/assignment.css">
	<script src="/js/prefixfree.min.js"></script>
	<script src="/js/tanchuang.js"></script>

</head>
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay"></div>
	<jsp:include page="/common/AfterLoginTittle.jsp"/>
	</div>
	<!-- 导航按钮 作者：陆祎洲-->
	<jsp:include page="/common/comTittle.jsp"/>

	<div class="mainbox">
		<form action="/assignment.do" method="post" id="subform" >
			<input type="hidden" name="method" value="assiginmentTask">
		<!-- 表单 -->
		<table id="table">
			<tr><th colspan="2">任务发布</th></tr>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" id = "title" style="width: 100%;" /></td>
			</tr>
			<tr>
				<td>任务描述</td>
				<td style="width: auto;padding-right: 10px;"><textarea cols="50" name = "content" id = "content" rows="5" style="margin-right: 5px;"></textarea></td>
			</tr>
			<tr>
				<td>截止日期</td>
				<td><input type="date" name="DDL" id = "DDL"/></td>
			</tr>
			<tr>
				<td rowspan="4" style="border-right: 1px #007bd9 solid;">发送对象</td>
			</tr>
			<tr>
				<td style="width: 400px;">部长:<input type="checkbox" name="" onclick="checkall(this)"/>ALL
					<br>
					<!-- 循环体 -->
					<!-- 在此添加人选名单 -->${president.getName()}<input type="checkbox" name="box" value= "${president.getId()}"/>
					<!-- 形如：	
								小明,<input type="checkbox" name=""/>
								小明,<input type="checkbox" name=""/> 
								...
								加逗号好看，你不加也可以-->
								<!-- 循环体结束 -->
							</td>
						</tr>
						<tr>
							<td style="width: 400px;">副部:<input type="checkbox" name="" onclick="checkall(this)"/>ALL
								<br>
								<!-- 循环体 -->
								<!-- 在此添加人选名单 -->
									<%
											Student[] vices =(Student[]) request.getAttribute("vices");
											//System.out.println(vices.length);
											for(int i = 0;i<vices.length&&vices[i] != null;i++)
											{
											%>
								<%=vices[i].getName()%>,<input type="checkbox" name="box" value="<%=vices[i].getId()%>" />
							<%
								}
							%>
								<!-- 循环体结束 -->
							</td>
						</tr>
						<tr>
							<td style="width: 400px;">部员:<input type="checkbox" name="" onclick="checkall(this)" />ALL
								<br>
								<!-- 循环体 -->
								<!-- 在此添加人选名单 -->
									<%
												Student[] commons = (Student[]) request.getAttribute("commons");
												for (int i = 0;i<commons.length && commons[i] != null;i++)
												{
											%>
								<%=commons[i].getName()%>,<input type="checkbox" name="box"value="<%=commons[i].getId()%>" />
							<%
								}
							%>
								<!-- 循环体结束 -->
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span class="upload-but" id="fj">
									点此上传附件
									<input type="file" class="upload_file" id="upload" onchange="uploadfile()"/>
								</span>
								<span style="position: relative;top: 3px;padding-left: 5px;width: 100%;"></span>
								<!-- 上传附件记录 -->
								<script type="text/javascript">
						// 保存所有文件的文件属性的数组
						var filenames = new Array();

						var i = 0;
						var input = document.querySelector('.upload_file');
						input.onchange = function(){
							this.parentElement.parentElement.children[1].innerText += this.files[0].name + ";";
							filenames[i] = this.files[0];
							i++;
						}
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">

					<button class="table-button" type="button" id="green"style="float:right" onclick="sub()">提交</button>
				</td>
			</tr>
		</table>
		<!-- 全选函数 -->
			<script>
                function sub() {
                    var title = document.getElementById("title").value;
                    var content = document.getElementById("content").value;
                    var DDL = document.getElementById("DDL").value;
                    if(0 == title.length)
					{
					    alert("请输入标题")
					    return;
					}
                    if(0 == content.length)
                    {
                        alert("请输入内容")
                        return;
                    }
                    if(0 == DDL.length)
                    {
                        alert("请选择时间")
                        return;
                    }
                    document.getElementById("subform").submit();
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
                            // alert(srg);
                           // temp.src = srg;
                        },
                        error: function () {
                        }
                    });
                }
			</script>
		<script type="text/javascript">
			function checkall(element){
				var flag= element.checked;
				var trs = element.parentElement.children;
				for (var i = trs.length - 1; i > 1; i--) {
					trs[i].checked = flag;
				}
			}
		</script>
		</form>
	</div>
<div id="fade"class="black_overlay">
	</div>

	<jsp:include page="/common/comExit.jsp"/>

</body>
</html>