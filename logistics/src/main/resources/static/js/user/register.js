var nickname = document.getElementById("nickname");
var username = document.getElementById("username");
var password = document.getElementById("password");
var phone = document.getElementById("phone");
var email = document.getElementById("email");
var form = document.getElementById("form");
var tips = document.querySelectorAll(".tip");
var test1 = false;
var test2 = false;
var test3 = false;
var test4 = false;
var test5 = false;

//验证用户名，失去焦点时触发
username.onblur = function () {
    var pattern = /^\w{6,18}$/;
    var username = $(this).val();
    if (username == "") {//this指代当前输入框
        tips[0].innerHTML = "账号不能为空";
        tips[0].style.color = "red";
    } else {
        if (pattern.exec(username) == null) {
            tips[0].innerHTML = "请输入6-18位数字、字母或下划线";
            tips[0].style.color = "red";
        } else {
            $.get("findUsername", {username: username}, function (data) {
                if (data.userExit) {
                    tips[0].innerHTML = "账号已存在";
                    tips[0].style.color = "red";
                } else {
                    tips[0].innerHTML = "正确";
                    tips[0].style.color = "green";
                    test2 = true;
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
    } else {
        if (pattern.exec(this.value) == null) {
            tips[1].innerHTML = "请输入6-18位数字、字母或下划线";
            tips[1].style.color = "red";
        } else {
            tips[1].innerHTML = "正确";
            tips[1].style.color = "green";
            test3 = true;
        }
    }
};

//验证中文名，失去焦点时触发
nickname.onblur = function () {
    var name = $(this).val();
    if (name == "") {//this指代当前输入框
        tips[2].innerHTML = "用户名不能为空";
        tips[2].style.color = "red";
    } else {
        if (name.length < 1 || name.length > 6) {
            tips[2].innerHTML = "请输入1-6位数字、字母或下划线";
            tips[2].style.color = "red";
        } else {
            $.get("findName", {name: name}, function (data) {
                if (data.nameExit) {
                    tips[2].innerHTML = "用户名已存在";
                    tips[2].style.color = "red";
                } else {
                    tips[2].innerHTML = "正确";
                    tips[2].style.color = "green";
                    test1 = true;
                }
            });
        }
    }
};

//验证手机号，失去焦点时触发
phone.onblur = function () {
    var pattern = /^\d{11}$/;
    if (this.value == "") {//this指代当前输入框
        tips[3].innerHTML = "手机号不能为空";
        tips[3].style.color = "red";
    } else {
        if (pattern.exec(this.value) == null) {
            tips[3].innerHTML = "手机号格式错误";
            tips[3].style.color = "red";
        } else {
            tips[3].innerHTML = "正确";
            tips[3].style.color = "green";
            test4 = true;
        }
    }
};
//验证邮箱，失去焦点时触发
email.onblur = function () {
    //aa@qq.com
    var pattern = /^\w+@\w+(\.[a-zA-Z_]{2,4})+$/;
    if (this.value == "") {//this指代当前输入框
        tips[4].innerHTML = "邮箱不能为空";
        tips[4].style.color = "red";
    } else {
        if (pattern.exec(this.value) == null) {
            tips[4].innerHTML = "邮箱格式错误";
            tips[4].style.color = "red";
        } else {
            tips[4].innerHTML = "正确";
            tips[4].style.color = "green";
            test5 = true;
        }
    }
};


//前面所有数据正确时，才能提交
function check() {
    if (test1 && test2 && test3 && test4 && test5) {
        return true;
    } else {
        alert("信息填写有误");
        return false;
    }
}