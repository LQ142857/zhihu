/*index页面登录注册面板js*/
function openLoginDiv() {
    $("#zf-register").hide();
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

