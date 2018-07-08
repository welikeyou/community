
/*
功能:注册和登录的逻辑
创建者:陆祎洲,董淑媛
修改者:陆祎洲,董淑媛
*/
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