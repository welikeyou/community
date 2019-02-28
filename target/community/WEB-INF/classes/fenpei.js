function checkall(element){
	var children = element.parentElement.parentElement.parentElement.children[1].children;
	if(element.checked){
		for (var i = children.length - 1; i >= 0; i--) {
			children[i].children[0].checked=true;
		}
	}
	else{
		for (var i = children.length - 1; i >= 0; i--) {
			children[i].children[0].checked=false;
		}
	}
}