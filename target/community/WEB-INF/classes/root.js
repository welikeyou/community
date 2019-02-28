function ucheckall(){
	var flag=document.getElementById("uckall").checked;
	var trs = document.querySelectorAll(".establish-apply-uck");
	for (var i = trs.length - 1; i >= 0; i--) {
		trs[i].children[0].children[0].checked = flag;
	}
}

function checkall(){
	var flag=document.getElementById("ckall").checked;
	var trs = document.querySelectorAll(".establish-apply-ck");
	for (var i = trs.length - 1; i >= 0; i--) {
		trs[i].children[0].children[0].checked = flag;
	}
}
//弹出隐藏层
function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth; 
 // bgdiv.style.height = $(document).height();
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
};