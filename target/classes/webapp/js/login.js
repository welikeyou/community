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
function SignUp(close_div1,show_div2,bg_div){
	rotate();
	CloseDiv(close_div1,bg_div);
	ShowDiv(show_div2,bg_div);
}

function toLogin(){
	var login_win = document.getElementById('logintable');
	var sign_win = document.getElementById('signtable');
	var bg = document.getElementById('fade');
	login_win.style.display='block';
	// sign_win.style.display='block';
	if (bg.style.display=='none'||bg.style.display=='') {
		bg.style.display='block';
		bg.style.width=document.body.scrollWidth;
	}

	login_win.style.animation='table-in 0.3s 0.1s forwards';
}
function Cancel(){
	var login_win = document.getElementById('logintable');
	var sign_win = document.getElementById('signtable');
	var bg = document.getElementById('fade');
	if (login_win.style.display=='block') {
		login_win.style.display='none';
		login_win.style.top==-200+'px';

	} 
	if (sign_win.style.display=='block') {
		sign_win.style.display='none';
		sign_win.style.top==-200+'px';
	} 
	bg.style.display='none';
}
function Sign(){
	var login_win = document.getElementById('logintable');
	var sign_win = document.getElementById('signtable');
	var bg = document.getElementById('fade');
	login_win.style.animation='table-out 0.5s 0.2s forwards';
	//login_win.style.display='none';

	sign_win.style.display='block';
	sign_win.style.animation='table-in 0.5s 0.4s forwards';
}
function Back(){
	var login_win = document.getElementById('logintable');
	var sign_win = document.getElementById('signtable');
	var bg = document.getElementById('fade');
	sign_win.style.animation='table-out 0.5s 0.2s forwards';
	//sign_win.style.display='none';

	login_win.style.display='block';
	login_win.style.animation='table-in 0.5s 0.4s forwards';
}