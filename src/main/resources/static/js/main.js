/*index页面登录注册面板js*/
function openLoginDiv() {
    $("#zf-register").hide();
    $("#tips").hide();
    $("#zf-login").fadeIn(1000);
}

function openRegisterDiv() {
    $("#zf-login").hide();
    $("#zf-register").fadeIn(1000);
}

/*Endindex页面登录注册面板jsEnd*/

/*显示评论面板的回复,踩,举报*/
function showDv2(id) {
    id = "#" + id;
    $(id).fadeIn(100);

}

function closeDv2(id) {
    id = "#" + id;
    $(id).fadeOut(100);
}

/*end显示评论面板的回复,踩,举报end*/

/*显示评论面板*/
function openComment(id) {
    $("#A" + id).fadeIn();
}

function closeComment(id) {
    $("#A" + id).fadeOut();
}

/*end显示评论面板的回复,踩,举报end*/

/*切换最新最热评论*/
function toggleComments() {
    const hasClass = $("#toggleComments").attr("class");
    if (hasClass === "latest") {
        $("#toggleComments").text("切换为热门评论")
    } else if (hasClass === "hot") {
        $("#toggleComments").text("切换为最新评论")
    }
    if (hasClass === "hot") {
        $("#toggleComments").attr("class", "latest");
    } else {
        $("#toggleComments").attr("class", "hot");

    }
}

/*end切换最新最热评论end*/

/*我的文章展开隐藏*/
function toggleSlide(id) {
    id = "#" + id;
    const hasClass = $(id).hasClass("slideUp");
    if (hasClass) {
        $(id).slideDown();
    } else {
        $(id).slideUp();
    }
    if (hasClass) {
        $(id).removeClass("slideUp")
    } else {
        $(id).addClass("slideUp")
    }
}

/*end我的文章展开隐藏end*/

/*按钮按下去开关*/
function toggleButton(id) {
    id = "#" + id;
    const hasClass = $(id).hasClass("down");
    if (hasClass) {
        $(id).css("background-color", "rgba(30,144,255,0.44)");


    } else {
        $(id).css("background-color", "rgb(0, 126, 250)");


    }
    if (hasClass) {
        $(id).removeClass("down");
    } else {
        $(id).addClass("down");
    }

}

/*end按钮按下去开关end*/

/*按钮按下去开关*/
function toggleButton2(id, str) {
    id = "#" + id;
    const hasClass = $(id).hasClass("down");
    if (hasClass) {
        $(id).css("background-color", "rgba(30,144,255,0.28)");
        $(id).text(str);

    } else {
        $(id).css("background-color", "rgb(0, 126, 250)");
        $(id).text("已关注");

    }
    if (hasClass) {
        $(id).removeClass("down");
    } else {
        $(id).addClass("down");
    }

}

/*end按钮按下去开关end*/

/*index二级目录跳转*/
function directoryChange(id) {
    $(".types").attr("class", "types");
    $("#" + id).addClass("typeChoose");
    var url = '/' + id;
    $.ajax({
        url: url,
        method: 'POST',
        success: function (data) {
            $("#flashContent").html(data);

        }


    })
}

/*index二级目录跳转end*/

/*展开关闭内容*/


/*展开关闭内容end*/

/*info页面信息切换*/
function infoChange(id, str) {


    $(".info").removeClass("chooseButtom");
    $("#" + id).addClass("chooseButtom");


}

/*endinfo页面信息切换end*/

/*get请求*/
function userInfoGet(id) {//get请求跳转info页面
    location.href = "/users&id=" + id;
}

/*get请求*/
/*post请求*/
/*post请求*/

/*页面跳转*/
function herf(str) {
    location.href = str;
}

/*页面跳转end*/
/*动态script*/


/*动态script*/

//移除标签
function tagRemove() {
    alert("44444");
    $(this).hide();
    $(this).text("00000");
}

let str = "";

//添加标签
function addTag() {
    var btn = "<button class=\"noButton tag\"\n" +
        "style=\"border: 1px solid rgba(0,0,0,0);margin-top:5px;margin-right:5px;border-radius:25px;background: #7A7A7A;color: whitesmoke\" onclick='tagRemove()'>\n" +
        $("#tag").val() +
        " </button>";
    $("#tagsDiv").append(btn);
    str = str + $("#tag").val() + "&&";
    $("#tags").text(str);
}

//获取注册验证码
function registerCode() {
    const email = $('#zf-email').val();
    $.ajax({
        url: '/emailRegister',
        method: 'post',
        data: ({
            "email": email,
        }),
        success: function (res) {
            if (res.code === 200) {
                $("#register-msg").css("color", "deepskyblue");
            } else {
                $("#register-msg").css("color", "red");
            }

            $("#register-msg").text(res.msg);
        },
        error: function () {
            $("#register-msg").css("color", "red");
            $("#register-msg").text("验证码获取失败");
        }
    })
}

//注册
function userRegister() {
    const email = $('#zf-email').val();
    const password = $('#zf-password').val();
    const rePass = $('#zf-rePass').val();
    const name = $('#zf-name').val();
    const emailCode = $('#zf-emailCode').val();
    $("#register-msg").css("color", "red");
    if (email.length > 25) {
        $("#register-msg").text("邮箱过长，请小于25个字符");
        return;
    }
    if (password.length > 20 || password.length < 6) {
        $("#register-msg").text("密码请输入6~20个字符");
        return;
    }
    if (name.length > 10 || name.length < 6) {
        $("#register-msg").text("名称请输入6~10个字符");
        return;
    }
    if (password !== rePass) {
        $("#register-msg").text("两次密码不一样");
        return;
    }
    if (emailCode === "") {
        $("#register-msg").text("验证码不能为空");
        return;
    }
    $.ajax({
        url: '/register',
        method: 'post',
        data: ({
            email: email,
            password: password,
            rePass: rePass,
            name: name,
            emailCode: emailCode,
        }),
        success: function (res) {
            console.log(res);
            if (res.code === 200) {
                $("#register-msg").css("color", "deepskyblue");
            } else {
                $("#register-msg").css("color", "red");
            }

            $("#register-msg").text(res.msg);
        },
        error: function () {
            $("#register-msg").css("color", "red");
            $("#register-msg").text("验证码获取失败");
        }
    })
}

//更换动态验证码
function changeImg() {
    $('#imgCode').attr("src", "/captcha?t" + Math.random());

}

//登录
function userLogin() {
    const account = $("#zf-account").val();
    const p = $("#zf-p").val();
    const code = $("#zf-code").val();
    $("#login-msg").css("color", "red");
    if (p === "") {
        $("#login-msg").text("密码不能为空");
        return;
    }
    if (code === "") {
        $("#login-msg").text("验证码不能为空");
        return;
    }


    $.ajax({
        url: '/login',
        method: 'post',
        data: ({
            email: account,
            password: p,
            code: code,
        }),
        success: function (res) {
            console.log(res);
            if (res.code === 200) {
                window.location.reload();

            } else {
                $("#login-msg").css("color", "red");
            }
            $("#login-msg").text(res.msg);
        },
        error: function () {
            $("#login-msg").css("color", "red");
            $("#login-msg").text("登录失败");
        }

    })


}

/*topic提交*/
function postTopic() {
    const cover = $("#zf-cover").val();
    const title = $("#zf-title").val();
    const summary = $("#zf-summary").val();
    if (cover === "") {
        alert("请上传封面图片");
        return;
    }
    if (title === "") {
        alert("标题不能空");
        return;
    }
    if (title.length > 15) {
        alert("标题过长");
        return;
    }
    if (summary === "") {
        alert("优秀的简介可以吸引其他用户参与哦~");
        return;
    }
    $.ajax({
        url: '/topics',
        method: 'post',
        data: ({
            cover: cover,
            title: title,
            summary: summary,
        }),
        success: function (res) {
            if (res.code === 200) {
                alert(res.msg);
                window.location.reload();
            } else {
                alert(res.msg);
            }
        },
        error: function () {
            alert("发布失败")
        }
    })
}


/*第三方插件点击弹出框*/
function openDiv() {
    document.getElementsByClassName('dropdown')[0].classList.toggle('down');
    document.getElementsByClassName('arrow')[0].classList.toggle('gone');
    if (document.getElementsByClassName('dropdown')[0].classList.contains('down')) {
        setTimeout(function () {
            document.getElementsByClassName('dropdown')[0].style.overflow = 'visible'
        }, 500)
    } else {
        document.getElementsByClassName('dropdown')[0].style.overflow = 'hidden'
    }
}

/*End第三方插件点击弹出End*/

