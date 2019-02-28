// 信息不能为空 作者：陆祎洲
// 只针对特定网页
window.onload=function(){
	var input = document.getElementsByTagName('input');
	var text = document.getElementsByTagName('textarea');
	for (var i = input.length - 1; i >= 0; i--) {
		// 更改针对性请在下方修改
		if (i==0||i==3||i==5||i==6) {
			input[i].onfocus=function(){
				// 选择节点
				var attention = this.parentElement.parentElement.lastElementChild.children[0];
				if (this.value=="") {
					attention.style.visibility = "visible";
				}
			}
			input[i].onchange=function(){
				// 选择节点
				var attention = this.parentElement.parentElement.lastElementChild.children[0];
				if (this.value!="") {
					attention.style.visibility = "hidden";
				}
			}
		}
	}
}