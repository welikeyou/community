/*
功能:我的信息逻辑
创建者:陆祎洲,董淑媛
修改者:陆祎洲,董淑媛
*/

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
  	document.getElementById("Infoname").value=document.getElementById("Infoname0").value;
    document.getElementById("Infosex").value=document.getElementById("Infosex0").value;
    document.getElementById("Infocontact").value=document.getElementById("Infocontact0").value;
    document.getElementById("Infoschool").value=document.getElementById("Infoschool0").value;
    document.getElementById("Infobirthday").value=document.getElementById("Infobirthday0").value;
    document.getElementById("Infoaddress").value=document.getElementById("Infoaddress0").value;
    document.getElementById("Infohobby").value=document.getElementById("Infohobby0").value;
    document.getElementById("Infoslogan").value=document.getElementById("Infoslogan0").value;

    baseform.submit();
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

function changepw() {
    changepwform.submit();
}