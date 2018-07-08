/*创作者：董淑媛  修改者:陆祎洲,董淑媛 功能：实现主页面图片轮播*/
window.onload = function(){
	var oDiv = document.getElementById('box');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var aLi = oUl.getElementsByTagName('li');
	var prev = document.getElementById('prev');
	var next = document.getElementById('next');
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

	prev.onclick = function(){
		num--;
		move();
	}
	next.onclick = function(){
		num++;
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
