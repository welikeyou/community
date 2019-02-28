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
	document.getElementById(show_div).style.top = window.scrollY + 'px';
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

//提交导航栏
function actReview(method){

    document.getElementById("method").value=method;
    document.getElementById("sideForm").submit();

}



//点击增加
function add(method,formId){
    document.getElementById("addmethod").value=method;
    document.getElementById("modifyMethod").value=method;
    document.getElementById("moneyDiv1").value = document.getElementById("text").innerText;
    document.getElementById("moneyDiv2").value = document.getElementById("text").innerText;
    /*alert("moneyDiv1:"+document.getElementById("moneyDiv1").value );
    alert("moneyDiv2:"+document.getElementById("moneyDiv2").value );
    alert("moneyDiv1:"+document.getElementsByName("moneyDiv")[0].value);
    alert("moneyDiv2:"+document.getElementsByName("moneyDiv")[1].value);
    alert("textDiv"+document.getElementById("text").innerText);*/
    var form=document.getElementById(formId);
    if(!check(form)){return false;}
    else{document.getElementById(formId).submit();}

}


//检查添加表单
function check(form) {

    with (form) {
        var pattNumber=new RegExp("^[1-9][0-9]{0,}(\.[0-9]{1,2})?$");
        var flag1=pattNumber.test(income.value);
        var flag2=pattNumber.test(cost.value);
        if (person.value == "") {
            alert("负责人不能为空");
            return false;
        } else if (income.value == "" && cost.value == "") {
            alert("收入和支出不能同时为空");
            return false;
        }else if(income.value == ""&&(!flag2)){
            alert("收入格式不正确，请输整数或者两位小数"); return false;
        }else if(cost.value == ""&&(!flag1)){
            alert("支出格式不正确，请输整数或者两位小数"); return false;
        }
        else if (date.value == "") {
            alert("请输入日期");
            return false;
            // }else if(!(new Date(date.value).getDate()==date.value.substring(date.value.length-2))){
            //     alert("日期应为合法日期，且格式为xxxx-xx-xx");
            //     return false;
        }else if(!checkDate(date.value)){
            return false;
        } else return true;
    }
}

//检查日期
function checkDate(date) {

    var d = new Date();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    var str = d.getFullYear() + "-";
    if (month < 10) str += "0" + month + "-";
    else str += month + "-";
    if (day < 10) str += "0" + day;
    else str += day;
    //alert(str);
    /*  var str=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();*/
    //alert("系统："+Date.parse(str)+" "+str+"自己："+Date.parse(date)+" "+date);
    if (Date.parse(str) >= Date.parse(date)) return 1;
    else {
        alert("日期应小于当前日期" + str);
        return 0;
    }
}
