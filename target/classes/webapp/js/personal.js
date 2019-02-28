function update(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='hidden';
  	children[0].children[1].style.visibility='hidden';
  	children[1].children[0].style.visibility='visible';
    children[1].children[1].style.visibility='visible';
  	var texts = document.getElementsByTagName('input');
	var up = document.getElementById('uploadspan');
	up.style.visibility='visible';
  	for (var i = texts.length - 1; i >= 0; i--) {
  		texts[i].disabled=false;
		texts[i].style.border="1px solid #333";
  	}
}
function confirm(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='visible';
  	children[0].children[1].style.visibility='visible';
  	children[1].children[0].style.visibility='hidden';
    children[1].children[1].style.visibility='hidden';
  	var texts = document.getElementsByTagName('input');
	var up = document.getElementById('uploadspan');
	up.style.visibility='hidden';
  	for (var i = texts.length - 4; i >= 0; i--) {
  		texts[i].disabled=true;
		texts[i].style.border="0";
  	}
}
function cancel(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='visible';
  	children[0].children[1].style.visibility='visible';
  	children[1].children[0].style.visibility='hidden';
    children[1].children[1].style.visibility='hidden';
  	var texts = document.getElementsByTagName('input');
	var up = document.getElementById('uploadspan');
	up.style.visibility='hidden';
  	for (var i = texts.length - 1; i >= 0; i--) {
  		texts[i].disabled=true;
		texts[i].style.border="0";
  	}
}
//获取上传按钮
window.onload=function(){
    var input1 = document.getElementById("upload");
    if (typeof FileReader === 'undefined') {
        //result.innerHTML = "抱歉，你的浏览器不支持 FileReader"; 
        input1.setAttribute('disabled', 'disabled');
    } else {
        input1.onchange =function(){
            var file = this.files[0]; //获取上传文件列表中第一个文件
            if (!/image\/\w+/.test(file.type)) {
                //图片文件的type值为image/png或image/jpg
                alert("文件必须为图片！");
                return false;
            }
            // console.log(file);
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
                    // 获取 canvas DOM 对象 
                    var img = document.getElementById('cvs');
                    img.src = image.src;
                };
            }
        };
    }
}
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