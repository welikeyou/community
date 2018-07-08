/*
功能:主页面的逻辑
创建者:陆祎洲,董淑媛
修改者:陆祎洲,董淑媛
*/

$(function () {
    var left = $('.left');
    var right = $('.right');
    var bg = $('.bgDiv');
    var leftNav = $('.leftNav');
    var rightNav = $('.rightNav');
   
    showNav(left, leftNav, "left");
    showNav(right, rightNav, "right");
    
    function showNav(btn, navDiv, direction) {
        btn.on('click', function () {
            bg.css({
                display: "block",
                transition: "opacity .5s"
            });
            if (direction == "right") {
                navDiv.css({
                    right: "0px",
                    transition: "right 0.4s"
                });
            } else if (direction == "left") {
                navDiv.css({
                    left: "0px",
                    transition: "left 1s"
                });
            }
        });
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