$(function () {
    var posters=$('.block-img');
    var bg = $('.bgDiv');
    var leftNav = $('.leftNav');
    var rightNav = $('.rightNav');
    for (var i = posters.length - 1; i >= 0; i--) {
        var poster = posters[i];
        if (poster.x-poster.width>document.children[0].clientWidth/2) {
            showNav(poster, leftNav, "left");
        }
        else{
            showNav(poster, rightNav, "right");
        }
    }

    //显示侧边
    function showNav(btn, navDiv, direction) {
        btn.onclick = function () {
            var labels= document.getElementsByTagName('a');
            var colors =['#ff6969','#ffbe69','#7bff69','#69ccff','#6969ff','#ff69d3'];
            for (var i = labels.length - 1; i >= 0; i--) {
                var j = i%6;
                if (j==0) {
                    labels[i].style.backgroundColor="#ff6969";
                }
                if (j==1) {
                    labels[i].style.backgroundColor="#ffbe69";
                }
                if (j==2) {
                    labels[i].style.backgroundColor="#7bff69";
                }
                if (j==3) {
                    labels[i].style.backgroundColor="#69ccff";
                }
                if (j==4) {
                    labels[i].style.backgroundColor="#6969ff";
                }
                if (j==5) {
                    labels[i].style.backgroundColor="#ff69d3";
                }
            }
            bg.css({
                display: "block",
                transition: "opacity .5s"
            });
            if (direction == "right") {
                navDiv.css({
                    right: "0px",
                    transition: "right 0.6s ease-out"
                });
            } else if (direction == "left") {
                navDiv.css({
                    left: "0px",
                    transition: "left 0.6s ease-out"
                });
            }
        };
    }

    $('span').each(function () {
        var dom = $(this);
        dom.on('click', function () {
            hideNav();
            alert(dom.text())
        });
    });

    bg.on('click', function () {
        hideNav();
    });

    function hideNav() {
        leftNav.css({
            left: "-50%",
            transition: "left .5s"
        });
        rightNav.css({
            right: "-50%",
            transition: "right .5s"
        });
        bg.css({
            display: "none",
            transition: "display 1s"
        });
    }
});
function search(element){
    var text = document.getElementById('keywords');
    var but = text.parentElement.parentElement.children[2];
    if (element.id=="on") {
        element.style.transform="rotate(90deg)";
        element.style.animation="off 0.3s forwards";
        element.id="off";
        text.style.width="150px";
        text.style.animation="hide 0.3s ease-out forwards";
        but.style.visibility = "hidden";
    }
    else {
        element.style.transform="rotate(0deg)";
        element.style.animation="on 0.3s forwards";
        element.id="on";
        text.style.width="0px";
        text.style.animation="out 0.3s ease-out forwards";
        but.style.visibility = "visible";
    }
}
function dropdown(element){
    var ul = element.parentElement.parentElement.children[0];
    ul.style.display = "block";
}
function dropup(element){
    var ul = element.children[0];
    ul.style.display = "none";
}
window.onload=function(){ 
    var ul = document.getElementsByTagName("ul");
    var lis = ul[0].children;
    for (var i = lis.length - 1; i >= 0; i--) {
        lis[i].onclick = function (){
            var text = document.getElementById("order-text");
            text.innerHTML = this.innerText + "<button class='dropdown' onmouseover='dropdown(this)'>↓</button>";
        } 
    }
} 
// 登录
function SignUp(close_div1,show_div2,bg_div){
    rotate();
    CloseDiv(close_div1,bg_div);
    ShowDiv(show_div2,bg_div);
}

function Login(){
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