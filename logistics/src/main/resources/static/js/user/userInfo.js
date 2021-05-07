//头像上传
$("#img_input").on("change", function (e) {
    var file = e.target.files[0]; //获取图片资源
    // 只选择图片文件
    if (!file.type.match('image.*')) {
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file); // 读取文件
    // 渲染文件
    reader.onload = function (arg) {

        var img = '<img class="m-preview-avatar" src="' + reader.result + '" alt="preview"/>';
        $(".preview_box").empty().append(img);
    }
});

// 表单验证
var nickname = document.getElementById("nickname");
var password = document.getElementById("password");
var phone = document.getElementById("phone");
var email = document.getElementById("email");
var tips = document.querySelectorAll(".tip");
var test1 = true;
var test2 = true;
var test3 = true;
var test4 = true;

//验证中文名，失去焦点时触发
nickname.onblur = function () {
    // var pattern = /[\u4e00-\u9fa5_a-zA-Z0-9_]{1,6}/;
    var name = $(this).val();
    if (name == "") {//this指代当前输入框
        tips[0].innerHTML = "用户名不能为空";
        tips[0].style.color = "red";
        test1 = false;
    } else {
        if (name.length < 1 || name.length > 6) {
            tips[0].innerHTML = "用户名长度为1~6位";
            tips[0].style.color = "red";
            test1 = false;
        } else {
            $.get("findByName", {name: name}, function (data) {
                if (data.nameExit) {
                    tips[0].innerHTML = "用户名已存在";
                    tips[0].style.color = "red";
                    test1 = false;
                } else {
                    tips[0].innerHTML = "正确";
                    tips[0].style.color = "green";
                    test1 = true;
                }
            });
        }
    }
};

//验证密码，失去焦点时触发
password.onblur = function () {
    var pattern = /^\w{6,18}$/;
    if (this.value == "") {//this指代当前输入框
        tips[1].innerHTML = "密码不能为空";
        tips[1].style.color = "red";
        test2 = false;
    } else {
        if (pattern.exec(this.value) == null) {
            tips[1].innerHTML = "请输入6-18位数字、字母或下划线";
            tips[1].style.color = "red";
            test2 = false;
        } else {
            tips[1].innerHTML = "正确";
            tips[1].style.color = "green";
            test2 = true;
        }
    }
};

//验证手机号，失去焦点时触发
phone.onblur = function () {
    var pattern = /^\d{11}$/;
    if (this.value == "") {//this指代当前输入框
        tips[2].innerHTML = "手机号不能为空";
        tips[2].style.color = "red";
        test3 = false;
    } else {
        if (pattern.exec(this.value) == null) {
            tips[2].innerHTML = "手机号格式错误";
            tips[2].style.color = "red";
            test3 = false;
        } else {
            tips[2].innerHTML = "正确";
            tips[2].style.color = "green";
            test3 = true;
        }
    }
};
//验证邮箱，失去焦点时触发
email.onblur = function () {
    //aa@qq.com
    var pattern = /^\w+@\w+(\.[a-zA-Z_]{2,4})+$/;
    if (this.value == "") {//this指代当前输入框
        tips[3].innerHTML = "邮箱不能为空";
        tips[3].style.color = "red";
        test4 = false;
    } else {
        if (pattern.exec(this.value) == null) {
            tips[3].innerHTML = "邮箱格式错误";
            tips[3].style.color = "red";
            test4 = false;
        } else {
            tips[3].innerHTML = "正确";
            tips[3].style.color = "green";
            test4 = true;
        }
    }
};


//前面所有数据正确时，才能提交
function check() {
    if (test1 && test2 && test3 && test4) {
        alert("修改成功");
        return true;
    } else {
        alert("信息填写有误");
        return false;
    }
}



