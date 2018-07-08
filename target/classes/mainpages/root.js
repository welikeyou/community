
/*
功能:审核页面逻辑
创建者:陆祎洲,董淑媛
修改者:陆祎洲,董淑媛
*/

function checkall(){
var flag1=document.getElementById("ckall1").checked;
var flag2=document.getElementById("ckall2").checked;
var ho1=document.getElementsByName("box1");
var ho2=document.getElementsByName("box2");
for(var i=0;i<ho1.length;i++){
if(ho1[i].checked==false)ho1[i].checked=flag1;
}
for(var i=0;i<ho2.length;i++){
 if(ho2[i].checked==false)ho2[i].checked=flag2;
    }
}
function reverse(){
var ho=document.getElementsByName("box");
for(var i=0;i<ho.length;i++){
ho[i].checked=!ho[i].checked;
}
}
//弹出隐藏层
function ShowDiv(show_div,bg_div,ob){
   // alert(1);
    var w=JSON.stringify(ob);
    var y=""+w+"";
    var s=JSON.parse(y);
   // alert(s.associationName);
   // alert(y);
    //alert(s.date);
    document.getElementsByName("associationNameDiv")[0].innerText=s.associationName;
    document.getElementsByName("associationNameDiv")[1].innerText=s.associationName;
    document.getElementsByName("studentNumDiv")[0].innerText=s.studentNum;
    document.getElementsByName("studentNumDiv")[1].innerText=s.studentNum;
    document.getElementsByName("studentNameDiv")[0].innerText=s.studentName;
    document.getElementsByName("studentNameDiv")[1].innerText=s.studentName;
    document.getElementsByName("typeDiv")[0].innerText=s.type;
    document.getElementsByName("typeDiv")[1].innerText=s.type;
    document.getElementsByName("slogan_inDiv")[0].innerText=s.slogan_in;
    document.getElementsByName("slogan_inDiv")[1].innerText=s.slogan_in;
    document.getElementsByName("slogan_outDiv")[0].innerText=s.slogan_out;
    document.getElementsByName("slogan_outDiv")[1].innerText=s.slogan_out;
    document.getElementsByName("timeDiv")[0].innerText=s.time;
    document.getElementsByName("timeDiv")[1].innerText=s.time;

    document.getElementsByName("contactDiv")[0].innerText=s.contact;
    document.getElementsByName("contactDiv")[1].innerText=s.contact;
    document.getElementsByName("advantageDiv")[0].innerText=s.advantage;
    document.getElementsByName("advantageDiv")[1].innerText=s.advantage;
    document.getElementsByName("planDiv")[0].innerText=s.plan;
    document.getElementsByName("planDiv")[1].innerText=s.plan;
    document.getElementsByName("reasonDiv")[0].innerText=s.reason;
    document.getElementsByName("reasonDiv")[1].innerText=s.reason;
    document.getElementById(show_div).style.display='block';
    document.getElementById(bg_div).style.display='block' ;
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    // bgdiv.style.height = $(document).height();
    $("#"+bg_div).height($(document).height());
  //  alert("1");
};
/*function ShowDiv(show_div,bg_div,name,stuName,num,type,slogan_in,slogan_out,time,contact,advantage,plan,reason){
    /!*var w=JSON.stringify(ob);
    var y=""+w+"";
    var s=JSON.parse(y);*!/

    document.getElementsByName("associationNameDiv")[0].innerText=name;
    document.getElementsByName("associationNameDiv")[1].innerText=name;
    document.getElementsByName("studentNumDiv")[0].innerText=num;
    document.getElementsByName("studentNumDiv")[1].innerText=num;
    document.getElementsByName("studentNameDiv")[0].innerText=stuName;
    document.getElementsByName("studentNameDiv")[1].innerText=stuName;
    document.getElementsByName("typeDiv")[0].innerText=type;
    document.getElementsByName("typeDiv")[1].innerText=type;
    document.getElementsByName("slogan_inDiv")[0].innerText=slogan_in;
    document.getElementsByName("slogan_inDiv")[1].innerText=slogan_in;
    document.getElementsByName("slogan_outDiv")[0].innerText=slogan_out;
    document.getElementsByName("slogan_outDiv")[1].innerText=slogan_out;
    document.getElementsByName("timeDiv")[0].innerText=time;
    document.getElementsByName("timeDiv")[1].innerText=time;

    document.getElementsByName("contactDiv")[0].innerText=contact;
    document.getElementsByName("contactDiv")[1].innerText=contact;
    document.getElementsByName("advantageDiv")[0].innerText=advantage;
    document.getElementsByName("advantageDiv")[1].innerText=advantage;
    document.getElementsByName("planDiv")[0].innerText=plan;
    document.getElementsByName("planDiv")[1].innerText=plan;
    document.getElementsByName("reasonDiv")[0].innerText=reason;
    document.getElementsByName("reasonDiv")[1].innerText=reason;
    document.getElementById(show_div).style.display='block';
    document.getElementById(bg_div).style.display='block' ;
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    // bgdiv.style.height = $(document).height();
   /!* $("#"+bg_div).height($(document).height());
    alert("1");*!/
};*/
//关闭弹出层
function CloseDiv(show_div,bg_div)
{

 document.getElementById(show_div).style.display='none';
 document.getElementById(bg_div).style.display='none';
};

function view(method){

    document.getElementById("method").value=method;
    document.getElementById("updateState1").value=document.getElementsByName("studentNumDiv")[0].innerText;
    document.getElementById("updateState2").value=document.getElementsByName("associationNameDiv")[0].innerText;
    document.getElementById("form").submit();

}