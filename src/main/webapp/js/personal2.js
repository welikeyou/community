function update(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='hidden';
  	children[0].children[1].style.visibility='hidden';
  	children[1].children[0].style.visibility='visible';
    children[1].children[1].style.visibility='visible';
  	var texts = document.getElementsByTagName('textarea');
  	for (var i = texts.length - 1; i >= 0; i--) {
  		texts[i].disabled=false;
  	}
}
function confirm(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='visible';
  	children[0].children[1].style.visibility='visible';
  	children[1].children[0].style.visibility='hidden';
    children[1].children[1].style.visibility='hidden';
  	var texts = document.getElementsByTagName('textarea');
  	for (var i = texts.length - 1; i >= 0; i--) {
  		texts[i].disabled=true;
  	}
}
function cancel(element){
	var children = element.parentElement.parentElement.children;
  	children[0].children[0].style.visibility='visible';
  	children[0].children[1].style.visibility='visible';
  	children[1].children[0].style.visibility='hidden';
    children[1].children[1].style.visibility='hidden';
  	var texts = document.getElementsByTagName('textarea');
  	for (var i = texts.length - 1; i >= 0; i--) {
  		texts[i].disabled=true;
  	}
}
//弹出隐藏层
function ShowDiv(show_div,bg_div){
 document.getElementById(show_div).style.display='block';
 document.getElementById(bg_div).style.display='block' ;
 var bgdiv = document.getElementById(bg_div);
 bgdiv.style.width = document.body.scrollWidth; 
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
 document.getElementById(show_div).style.display='none';
 document.getElementById(bg_div).style.display='none';
};