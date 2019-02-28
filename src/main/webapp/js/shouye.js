//弹出隐藏层
function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth; 
 // bgdiv.style.height = $(document).height();
 $("#"+bg_div).height($(document).height());
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
};
//弹出隐藏层
function ShowDiv2(bg_div){
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth; 
 // bgdiv.style.height = $(document).height();
 $("#"+bg_div).height($(document).height());
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
};
//获取上传按钮
var input1 = document.getElementById("upload");
if (typeof FileReader === 'undefined') {
	//result.innerHTML = "抱歉，你的浏览器不支持 FileReader"; 
	input1.setAttribute('disabled', 'disabled');
} else {
	input1.addEventListener('change', readFile, false);
}

function readFile() {
	var file = this.files[0]; //获取上传文件列表中第一个文件
	if (!/image\/\w+/.test(file.type)) {
    //图片文件的type值为image/png或image/jpg
    alert("文件必须为图片！");
    return false;
}
	var reader = new FileReader(); //实例一个文件对象
	reader.readAsDataURL(file); //把上传的文件转换成url
	//当文件读取成功便可以调取上传的接口
	reader.onload = function(e) {
		var image = new Image();
		// 设置src属性 
		image.src = e.target.result;
		var max = 200;
		// 绑定load事件处理器，加载完成后执行，避免同步问题
		image.onload = function() {
			var box = document.getElementById("title");
			box.style.backgroundImage="URL("+image.src+")";
		};
	}
};
function readFile2() {
	var file = this.files[0]; //获取上传文件列表中第一个文件
	if (!/image\/\w+/.test(file.type)) {
    //图片文件的type值为image/png或image/jpg
    alert("文件必须为图片！");
    return false;
}
	var reader = new FileReader(); //实例一个文件对象
	reader.readAsDataURL(file); //把上传的文件转换成url
	//当文件读取成功便可以调取上传的接口
	reader.onload = function(e) {
		var image = new Image();
		// 设置src属性 
		image.src = e.target.result;
		var max = 200;
		// 绑定load事件处理器，加载完成后执行，避免同步问题
		image.onload = function() {
			var box = document.getElementById("kuangjia");
			box.style.backgroundImage="URL("+image.src+")";
		};
	}
};
function edit(element,id){
　var oldhtml = element.innerHTML;//获得元素之前的内容
　var newobj = document.createElement('input');//创建一个input元素
　newobj.type = 'text';//为newobj元素添加类型
newobj.value=oldhtml;
	newobj.id = id;
　element.innerHTML = '';　　 //设置元素内容为空
　element.appendChild(newobj);//添加子元素
　newobj.focus();//获得焦点
     //设置newobj失去焦点的事件
     　newobj.onblur = function(){
     //下面应该判断是否做了修改并使用ajax代码请求服务端将id与修改后的数据提交
    //当触发时判断newobj的值是否为空，为空则不修改，并返回oldhtml
    element.innerHTML = this.value ? this.value : "title";
}
}
$(document).ready(function(){
	$("#xiugai").click(function(){
		$("#toolbar").show();
	});
	$("#xiugai").click(function(){
		$("#fade2").hide();
	});
	$("#xiugai").click(function(){
		$("#add").show();
	});
	$("#xiugai").click(function(){
		$("#upload").show();
	});
	$("#xiugai").click(function(){
		$("#upload2").show();
	});
	$("#xiugai").click(function(){
		$("#baocun").show();
	});
	$("#xiugai").click(function(){
		$("#uploads").show();
	});
	$("#xiugai").click(function(){
		$(".shanchu").show();
	});
	$("#baocun").click(function(){
		$("#fade2").show();
	});
	$("#baocun").click(function(){
		$("#add").hide();
	});  
	$("#baocun").click(function(){
		$("#upload").hide();
	});  
	$("#baocun").click(function(){
		$("#upload2").hide();
	});  
	$("#baocun").click(function(){
		$("#baocun").hide();
	});
	$("#baocun").click(function(){
		$("#uploads").hide();
	});
	$("#baocun").click(function(){
		$(".shanchu").hide();
	});
});