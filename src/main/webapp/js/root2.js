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
    alert(1);
    var w=JSON.stringify(ob);
    var y=""+w+"";
    var s=JSON.parse(y);
    alert(s.associationName);
    alert(y);
    document.getElementsByName("associationNameDiv")[0].innerText=s.associationName;
    document.getElementsByName("associationNameDiv")[1].innerText=s.associationName;
    document.getElementsByName("studentNumDiv")[0].innerText=s.studentNum;
    document.getElementsByName("studentNumDiv")[1].innerText=s.studentNum;
    document.getElementsByName("studentNameDiv")[0].innerText=s.studentName;
    document.getElementsByName("studentNameDiv")[1].innerText=s.studentName;
    document.getElementsByName("sexDiv")[0].innerText=s.sex;
    document.getElementsByName("sexDiv")[1].innerText=s.sex;
    document.getElementsByName("birthdayDiv")[0].innerText=s.birthday;
    document.getElementsByName("birthdayDiv")[1].innerText=s.birthday;
    document.getElementsByName("schoolDiv")[0].innerText=s.school;
    document.getElementsByName("schoolDiv")[1].innerText=s.school;
    document.getElementsByName("timeDiv")[0].innerText=s.time;
    document.getElementsByName("timeDiv")[1].innerText=s.time;
    document.getElementsByName("contactDiv")[0].innerText=s.contact;
    document.getElementsByName("contactDiv")[1].innerText=s.contact;
    document.getElementsByName("planDiv")[0].innerText=s.plan;
    document.getElementsByName("planDiv")[1].innerText=s.plan;
    document.getElementsByName("aimDiv")[0].innerText=s.aim;
    document.getElementsByName("aimDiv")[1].innerText=s.aim;

    document.getElementsByName("member1Div")[0].innerText=s.member1;
    document.getElementsByName("member1Div")[1].innerText=s.member1;
    document.getElementsByName("member2Div")[0].innerText=s.member2;
    document.getElementsByName("member2Div")[1].innerText=s.member2;
    document.getElementsByName("member3Div")[0].innerText=s.member3;
    document.getElementsByName("member3Div")[1].innerText=s.member3;
    document.getElementsByName("member4Div")[0].innerText=s.member4;
    document.getElementsByName("member4Div")[1].innerText=s.member4;
    document.getElementsByName("member5Div")[0].innerText=s.member5;
    document.getElementsByName("member5Div")[1].innerText=s.member5;

    document.getElementsByName("briefintro1Div")[0].innerText=s.briefintro1;
    document.getElementsByName("briefintro1Div")[1].innerText=s.briefintro1;
    document.getElementsByName("briefintro2Div")[0].innerText=s.briefintro2;
    document.getElementsByName("briefintro2Div")[1].innerText=s.briefintro2;
    document.getElementsByName("briefintro3Div")[0].innerText=s.briefintro3;
    document.getElementsByName("briefintro3Div")[1].innerText=s.briefintro3;
    document.getElementsByName("briefintro4Div")[0].innerText=s.briefintro4;
    document.getElementsByName("briefintro4Div")[1].innerText=s.briefintro4;
    document.getElementsByName("briefintro5Div")[0].innerText=s.briefintro5;
    document.getElementsByName("briefintro5Div")[1].innerText=s.briefintro5;

	document.getElementById(show_div).style.display='block';
	document.getElementById(show_div).style.top = window.scrollY + 'px';
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

function view(method){

    document.getElementById("method").value=method;
    document.getElementById("updateState1").value=document.getElementsByName("studentNumDiv")[0].innerText;
    document.getElementById("updateState2").value=document.getElementsByName("associationNameDiv")[0].innerText;
    document.getElementById("form").submit();

}