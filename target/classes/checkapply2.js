
//checkapply.jsp:审核加入社团
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
function ShowDiv(show_div,bg_div,ob){
 var w=JSON.stringify(ob);
 var y=""+w+"";
 var s=JSON.parse(y);

 alert(y);

 document.getElementsByName("studentNumDiv")[0].innerText=s.studentNum;
 document.getElementsByName("studentNameDiv")[0].innerText=s.studentName;
 document.getElementsByName("sexDiv")[0].innerText=s.sex;
 document.getElementsByName("birthdayDiv")[0].innerText=s.birthday;
 document.getElementsByName("schoolDiv")[0].innerText=s.school;
 document.getElementsByName("associationsDiv")[0].innerText=s.associations;
 document.getElementsByName("departmentDiv")[0].innerText=s.department;
 document.getElementsByName("reasonDiv")[0].innerText=s.reason;
 document.getElementsByName("hobbyDiv")[0].innerText=s.hobby;
 document.getElementsByName("contactDiv")[0].innerText=s.contact;
 document.getElementsByName("timeDiv")[0].innerText=s.time;

 document.getElementsByName("studentNumDiv")[1].innerText=s.studentNum;
 document.getElementsByName("studentNameDiv")[1].innerText=s.studentName;
 document.getElementsByName("sexDiv")[1].innerText=s.sex;
 document.getElementsByName("birthdayDiv")[1].innerText=s.birthday;
 document.getElementsByName("schoolDiv")[1].innerText=s.school;
 document.getElementsByName("departmentDiv")[1].innerText=s.department;
 document.getElementsByName("reasonDiv")[1].innerText=s.reason;
 document.getElementsByName("hobbyDiv")[1].innerText=s.hobby;
 document.getElementsByName("associationsDiv")[1].innerText=s.associations;
 document.getElementsByName("contactDiv")[1].innerText=s.contact;
 document.getElementsByName("timeDiv")[1].innerText=s.time;
 document.getElementById("updateInfo2").value=s.associationNum;
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

function act(method){
    document.getElementById("method").value=method;
    document.getElementById("sideForm").submit();
}

function update(method){
    document.getElementById("methodMain").value=method;
    document.getElementById("updateInfo1").value=document.getElementsByName("studentNumDiv")[0].innerText;
    document.getElementById("mainForm").submit();
}
/*弹出退出框的隐藏栏*/
function ShowDiv2(show_div,bg_div){
    document.getElementById(show_div).style.display='block';
    document.getElementById(show_div).style.top = window.scrollY + 'px';
    document.getElementById(bg_div).style.display='block' ;
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
};

function exit(){
    alert(1);
    document.getElementById("methodMain").value="exit";
    document.getElementById("mainForm").submit();

}