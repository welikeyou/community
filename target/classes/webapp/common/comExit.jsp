<%--
  Created by IntelliJ IDEA.
  User: 黄友明
  Date: 2018/7/13
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    //弹出隐藏层
    function ShowDivExit(show_div,bg_div){
        document.getElementById(show_div).style.display='block';
        document.getElementById(show_div).style.top = window.scrollY + 'px';
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

</script>

<!--弹出层时背景层DIV-->
<%--<div id="fade">
</div>
<div id="tuichu">
    <div style="text-align: right; cursor: default; height: 40px;">
        <span style="font-size: 20px;font-weight:bold" onclick="CloseDiv('tuichu','fade')">×</span>
    </div>
    <p>亲,你确定要退出该社团吗？</p>
    <button type="button">确定</button>
    <button type="button" onclick="CloseDiv('tuichu','fade')">取消</button>
</div>--%>
<form action="/mapages.do" method="post">
    <input type="hidden" name="method" value="exit">
<div id="fade">
</div>
<div id="tuichu">
    <div>
        <span onclick="CloseDiv('tuichu','fade')"><p class="cancel" style="font-size: 20px;font-weight:700;">×</p></span>
    </div>
    <p id="tuichup1">你确定要退出该社团吗？</p>
    <p id="tuichup2" style="display:none">由于你为该社团创建者，一旦退出，此社团将解散，请慎重选择.</p>
    <button type="submit" id="tuichub1" class="buttonb" >确定</button>
    <button type="button" class="buttonb" id="tuichub2" onclick="CloseDiv('tuichu','fade')">取消</button>
</div>
</form>