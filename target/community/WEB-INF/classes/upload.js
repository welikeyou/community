//获取上传按钮
window.onload=function(){
    var input1 = document.getElementById("upload");
    if (typeof FileReader === 'undefined') {
        //result.innerHTML = "抱歉，你的浏览器不支持 FileReader"; 
        input1.setAttribute('disabled', 'disabled');
    } else {
        input1.onchange =function(){
            var file = this.files[0]; //获取上传文件列表中第一个文件
            if (!/image\/\w+/.test(file.type)) {
                //图片文件的type值为image/png或image/jpg
                alert("文件必须为图片！");
                return false;
            }
            // console.log(file);
            var reader = new FileReader(); //实例一个文件对象
            reader.readAsDataURL(file); //把上传的文件转换成url
            //当文件读取成功便可以调取上传的接口
            reader.onload = function(e) {
                var image = new Image();
                // 设置src属性 
                image.src = e.target.result;
                var max = 200;
                // 绑定load事件处理器，加载完成后执行，避免同步问题
                image.onload = function() {
                    // 获取 canvas DOM 对象 
                    var img = document.getElementById("cvs");
                    // 如果高度超标 宽度等比例缩放 *= 
                    img.src = image.src;
                };
            }
        };
    }
}