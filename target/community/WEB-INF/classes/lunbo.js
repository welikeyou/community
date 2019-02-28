/*创作者：董淑媛 功能：实现主页面图片轮播*/
window.onload = function(){
	var oDiv = document.getElementById('box');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var aLi = oUl.getElementsByTagName('li');
	var first = document.getElementById('first');
	var second = document.getElementById('second');
	var third = document.getElementById('third');
	var fourth = document.getElementById('fourth');
	var fifth = document.getElementById('fifth');
	var num = 0;
	var timer = null;

	var lastChild = oUl.children[0].cloneNode(true);   //为了实现无缝，将第一张图片复制放到最后面
	oUl.appendChild(lastChild);
	oUl.style.width = aLi[0].offsetWidth * aLi.length + 'px';

	function autoPlay(){ 
		timer = setInterval(function(){
			num++; 
			move();
		},2000);
	}
	autoPlay();
	
	function move(){
		if(num == aLi.length){  //当图片到最后一张的时候，将oUl的left值设为0重新开始
			oUl.style.left = 0;
			num = 1;
		}
		else if(num == -1){
			oUl.style.left = -(aLi.length-1)*aLi[0].offsetWidth + 'px';
			num = aLi.length-2;
		}
		doMove(oUl,'left',-num*aLi[0].offsetWidth);
	}
	//鼠标放上停止轮播
	oDiv.onmouseover = function(){
		clearInterval(timer);
	}
	//鼠标离开自动播放
	oDiv.onmouseout = autoPlay;

	first.onclick = function(){
		num=1;
		move();
	}
	second.onclick = function(){
		num=2;
		move();
	}
	third.onclick = function(){
		num=3;
		move();
	}
	fourth.onclick = function(){
		num=4;
		move();
	}
	fifth.onclick = function(){
		num=5;
		move();
	}
}

function doMove(obj,attr,target){   //运动函数
	clearInterval(obj.timer);
	obj.timer = setInterval(function(){
		var speed = (target - parseInt(getStyle(obj,attr))) / 8;  //设置速度，随着图片滚动，速度越来越小
		speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);

		if(parseInt(getStyle(obj,attr))== target){
		clearInterval(obj.timer); 
		}else{
			obj.style[attr] = parseInt(getStyle(obj,attr)) + speed + 'px';
		}
	},30);
}
function getStyle(obj,attr){ // 获取样式
	return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj)[attr];
}
