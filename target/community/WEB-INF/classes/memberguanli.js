
function bcheckall(){
var flag=document.getElementById("bkall").checked;
var ho=document.getElementsByName("bbox");
for(var i=0;i<ho.length;i++){
ho[i].checked=flag;
}
}
function fcheckall(){
var flag=document.getElementById("fkall").checked;
var ho=document.getElementsByName("fbox");
for(var i=0;i<ho.length;i++){
ho[i].checked=flag;
}
}
function ccheckall(){
var flag=document.getElementById("ckall").checked;
var ho=document.getElementsByName("cbox");
for(var i=0;i<ho.length;i++){
ho[i].checked=flag;
}
}