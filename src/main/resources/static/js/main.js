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


/*主页导航栏跳转*/
function changeIndex(url) {
    $('#change').show();
    $('main').hide();
    setTimeout(function () {
        $.ajax({
            url: url,
            success: function () {
                $('#change').hide();
                $('main').show();
            }
        });


    }, 2000)

}

/*主页导航栏跳转end*/

/*我的文章展开隐藏*/
function toggleSlide(stat) {
    $('#infoLoad').show();
    $('#infoDiv').hide();
    setTimeout(function () {

        if (stat === 1) {
            $('#statName').text('我的专栏');
            $('#statImg').attr('src', '/fonts/column-solid.svg');
            $.ajax({
                url: "/myColumn",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/topics/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }
        if (stat === 2) {
            $('#statName').text('我的文章');
            $('#statImg').attr('src', '/fonts/article-solid.svg');
            $.ajax({
                url: "/myArticle",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/articles/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }
        if (stat === 3) {
            $('#statName').text('我的问题');
            $('#statImg').attr('src', '/fonts/question-solid.svg');
            $.ajax({
                url: "/myQuestion",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/questions/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }

        if (stat === 5) {
            $('#statName').text('我关注的专栏');
            $('#statImg').attr('src', '/fonts/topic-solid.svg');
            $.ajax({
                url: "/favorite",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                    kind: "1",
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/topics/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }
        if (stat === 6) {
            $('#statName').text('我收藏的文章');
            $('#statImg').attr('src', '/fonts/favorite-solid.svg');
            $.ajax({
                url: "/favorite",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                    kind: "2",
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/articles/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }
        if (stat === 7) {
            $('#statName').text('我关注的问题');
            $('#statImg').attr('src', '/fonts/question2.svg');
            $.ajax({
                url: "/favorite",
                method: "POST",
                datatype: "json",
                data: ({
                    start: 0,
                    kind: "3",
                }), success: function (response) {
                    $('#statNum').text(response.counts);
                    $.each(response.lists, function (i, item) {
                        const index = i + 1;
                        $('#statdiv' + index).show();
                        $('#stata' + index).attr('href', '/questions/' + item.id);
                        $('#statspan' + index).text(item.title);

                    });
                    $('#infoDetail').show();
                    $('#infoLoad').hide();
                }
            })
        }


        $('#statNum').text();
    }, 1000)


}

function returnDiv() {
    $('#infoLoad').show();
    $('#infoDetail').hide();
    setTimeout(function () {

        $('#infoDiv').show();
        $('#infoLoad').hide();
    }, 1000)

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


/*点赞*/



/*end点赞end*/


/*按钮按下去开关*/
function toggleButton2(id) {
    id = "#" + id;
    const hasClass = $(id).hasClass("down");
    if (hasClass) {
        $(id).css("background-color", "rgb(0, 126, 250)");
        $(id).text("关注Ta");
        $(id).removeClass("down");
    } else {

        $(id).css("background-color", "rgba(30,144,255,0.28)");
        $(id).text("已关注");
        $(id).addClass("down");
    }

}

/*end按钮按下去开关end*/

/*index二级目录跳转*/
function directoryChange(id) {
    $(".types").attr("class", "types");
    $("#" + id).addClass("typeChoose");
    $('#articles').hide();
    $('#load').show();
    setTimeout(function () {
        const url = '/' + id;
        $.ajax({
            url: url,
            method: 'POST',
            success: function (data) {
                $("#flashContent").html(data);
                $('#load').hide();
                $('#articles').fadeIn(1000);
            }


        })
    }, 1000);

}

/*index二级目录跳转end*/

/*跳转*/
function goHref(url) {
    location.href = url;
}

/*跳转end*/

/*info页面信息切换*/
function infoChange(id, str, start) {
    $("#noMessage").hide();
    $(".hd").hide();
    $("#infoLoad").show();
    setTimeout(function () {
        $(".info").removeClass("chooseButtom");
        $("#" + id).addClass("chooseButtom");
        ajaxInfo(str, start);
    }, 400)

}


function ajaxInfo(url, start) {

    $.ajax({
        url: "/info/" + url,
        method: "POST",
        datatype: "json",
        data: ({
            uid: $("#userId").text(),
            start: start,
        }), success: function (data) {
            console.log(data);
            $(".hd").hide();
            if (data.length === 0) {
                $("#noMessage").show();

            } else {
                const count = data.length + start;
                const num = $("#" + url + "Count").text();
                if (num < count) {
                    $.each(data, function (i, item) {
                        let text = "";
                        if (url === "articles") {
                            text = "   <div style=\"margin-top: 20px;min-height: 50px;\">\n" +
                                "                            <div style=\"float:left;\">\n" +
                                "                                <img src=\"/fonts/circle.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                                "                                <span style=\"font-weight: bold\">" + item.time + "</span>\n" +
                                "                            </div>\n" +
                                "                            <div style=\"margin-bottom: 10px;margin-left: 20px;padding: 10px;float: left;box-shadow:0px 0px 1px 1px rgba(195,195,195,0.56);width: 80%\">\n" +
                                "                                <a href=\"/articles/" + item.aid + "\">\n" +
                                "                                    <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                                "                                    <div style=\"display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;max-height: 100px\">\n" +
                                "                                      " + item.content +
                                "                                    </div>\n" +
                                "                                </a>\n" +
                                "                            </div>\n" +
                                "                        </div>";

                        }
                        if (url === "answers") {
                            text = "    <div style=\"margin-top: 20px;min-height: 70px\">\n" +
                                "                            <div style=\"float:left;\">\n" +
                                "                                <img src=\"/fonts/circle.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                                "                                <span style=\"font-weight: bold\">" + item.time + "</span>\n" +
                                "                            </div>\n" +
                                "                            <div style=\"margin-left: 20px;padding: 10px;float: left;box-shadow:0px 0px 1px 1px rgba(195,195,195,0.56);width: 80%\">\n" +
                                "                                <a href=\"/questions/" + item.qid + "\">\n" +
                                "                                    <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                                "                                    <div style=\"display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;color: #789fdb;\">\n" +
                                "                                       " + item.content +
                                "                                    </div>\n" +
                                "                                </a>\n" +
                                "                            </div>\n" +
                                "                        </div>";
                        }
                        if (url === "questions") {
                            text = "<div style=\"margin-top: 20px;min-height: 55px\">\n" +
                                "                            <div style=\"float:left;\">\n" +
                                "                                <img src=\"/fonts/circle.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                                "                                <span style=\"font-weight: bold\">" + item.time + "</span>\n" +
                                "                            </div>\n" +
                                "                            <div style=\"margin-left: 20px;padding: 10px;float: left;box-shadow:0px 0px 1px 1px rgba(195,195,195,0.56);width: 80%\">\n" +
                                "                                <a href=\"/questions/" + item.qid + "\">\n" +
                                "                                    <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                                "                                    <div style=\"display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;\">\n" +
                                "                                    <span>\n" +
                                "                                     <img src=\"/fonts/view.svg\" alt=\"\" width=\"20px\" height=\"20px\">\n" +
                                "                                        <span class=\"font-c3\">" + item.view + "</span>\n" +
                                "                                    </span>\n" +
                                "                                        <span>\n" +
                                "                                     <img src=\"/fonts/answer3.svg\" alt=\"\" width=\"14px\" height=\"14px\">\n" +
                                "                                    <span class=\"font-c3\"> " + item.comments + "</span>\n" +
                                "                                    </span>\n" +
                                "                                    </div>\n" +
                                "                                </a>\n" +
                                "                                \n" +
                                "                            </div>\n" +
                                "                        </div>";
                        }
                        if (url === "topics") {
                            text = "<div style=\"margin-top: 20px;min-height: 55px\">\n" +
                                "                            <div style=\"float:left;\">\n" +
                                "                                <img src=\"/fonts/circle.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                                "                                <span style=\"font-weight: bold\">" + item.time + "</span>\n" +
                                "                            </div>\n" +
                                "                            <div style=\"margin-left: 20px;padding: 10px;float: left;box-shadow:0px 0px 1px 1px rgba(195,195,195,0.56);width: 80%\">\n" +
                                "                                <a href=\"/topics/" + item.id + "\">\n" +
                                "                                    <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                                "                                    <div style=\"display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;\">\n" +
                                "                                    <span>\n" +
                                "                                     <img src=\"/fonts/view.svg\" alt=\"\" width=\"20px\" height=\"20px\">\n" +
                                "                                        <span class=\"font-c3\">" + item.view + "</span>\n" +
                                "                                    </span>\n" +
                                "                                    </div>\n" +
                                "                                </a>\n" +
                                "                                \n" +
                                "                            </div>\n" +
                                "                        </div>";
                        }
                        if (url === "contents" || url === "fans") {
                            text = "<div style=\"margin-top: 20px;height: 40px\">\n" +
                                "                            <div style=\"float:left;margin-top: 6px\">\n" +
                                "                                <a href=\"/users/" + item.uid + "\">\n" +
                                "                                    <img src=\"/upload/users/" + item.userImg + "\" alt=\"\" height=\"40px\" width=\"40px\"\n" +
                                "                                         style=\"border-radius: 50%\">\n" +
                                "                                </a>\n" +
                                "                            </div>\n" +
                                "                            <div style=\";margin-left: 20px;padding: 5px;float: left;box-shadow:0px 0px 1px 1px rgba(195,195,195,0.56);width: 70%\">\n" +
                                "                                <a href=\"/users/" + item.uid + "\">\n" +
                                "                                    <div style=\"font-weight: bold\">" + item.name + "</div>\n" +
                                "                                    <div style=\"display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:1;overflow: hidden;\">\n" +
                                "                                        " + item.introduce + "\n" +
                                "                                    </div>\n" +
                                "                                </a>\n" +
                                "                            </div>\n" +
                                "                        </div>";
                        }

                        $("#" + url).append(text);
                    });
                    $("#" + url + "Count").text(count);
                }
                $("#noMessage").hide();
                $("#" + url).show();
            }
            $("#infoLoad").hide();

        }
    })


}


/*endinfo页面信息切换end*/

/*get请求*/
function userInfoGet(id) {//get请求跳转info页面
    location.href = "/users/" + id;
}

/*get请求*/

/*setting页面设置*/
function changeSetting(str, name, describe, aj) {
    $('.set').attr('class', 'noButton setSpan  ');
    $('#' + str + 'Btn').attr('class', 'noButton set  setChooseSpan');
    $('.showDiv').hide();
    $('#' + str).fadeIn(500);
    $('#setName').text(name);
    $('#setDescribe').text(describe);
    if (str === "topic" || str === "article" || str === "question" || str === "answer")
        setAjax(str, aj);

}

/*setting页面设置end*/
function setAjax(str, aj) {
    $.ajax({
        url: '/set/' + str,
        method: 'POST',
        datatype: 'JSON',
        data: ({
            start: 0,
        }), success: function (respond) {

            const count = $('#' + str + 'Count').text();
            if (respond.length > count) {
                if (aj === 1) {
                    $.each(respond, function (i, item) {
                        const text = "<div class=\"Options\">\n" +
                            "                                    <a href=\"/topics/" + item.id + "\">\n" +
                            "                                        <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                            "                                    </a>\n" +
                            "                                    <div class='infoStatusDiv' style=\"float: left;width: 94%;color: " + item.color + "\">\n" +
                            "                                        <span>" + item.status + "</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div style=\"float: right\">\n" +
                            "                                        <button class=\"noButton\" style=\"color: #0e90f4\" onclick='deleteObject(" + item.id + ",1)'>删除</button>\n" +
                            "                                    </div>\n" +
                            "                                    <img src=\"/fonts/view.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                            "                                    <span>" + item.view + "</span>\n" +
                            "                                    <img src=\"/fonts/time.svg\" alt=\"\" height=\"15px\" width=\"15px\">\n" +
                            "                                    <span>" + item.time + "</span>\n" +
                            "                                </div>";
                        $('#topic').append(text)
                    });
                }
                if (aj === 2) {
                    $.each(respond, function (i, item) {
                        const text = "<div class=\"Options\">\n" +
                            "                                    <a href=\"/articles/" + item.id + "\">\n" +
                            "                                        <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                            "                                    </a>\n" +
                            "                                    <div class='infoStatusDiv'  style=\"float: left;width: 94%;color: " + item.color + "\">\n" +
                            "                                        <span>" + item.status + "</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div style=\"float: right\">\n" +
                            "                                        <button class=\"noButton\" style=\"color: #0e90f4\" onclick='deleteObject(" + item.id + ",2)'>删除</button>\n" +
                            "                                    </div>\n" +
                            "                                    <img src=\"/fonts/view.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                            "                                    <span>" + item.view + "</span>\n" +
                            "                                    <img src=\"/fonts/message3.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                            "                                    <span>" + item.comment + "</span>\n" +
                            "                                    <img src=\"/fonts/time.svg\" alt=\"\" height=\"15px\" width=\"15px\">\n" +
                            "                                    <span>" + item.time + "</span>\n" +
                            "                                </div> ";
                        $('#article').append(text)
                    });
                }
                if (aj === 3) {
                    $.each(respond, function (i, item) {
                        const text = "<div class=\"Options\">\n" +
                            "                                    <a href=\"/questions/" + item.id + "\">\n" +
                            "                                        <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                            "                                    </a>\n" +
                            "                                    <div class='infoStatusDiv'  style=\"float: left;width: 94%;color: " + item.color + "\">\n" +
                            "                                        <span>" + item.status + "</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div style=\"float: right\">\n" +
                            "                                        <button class=\"noButton\" style=\"color: #0e90f4\" onclick='deleteObject(" + item.id + ",3)'>删除</button>\n" +
                            "                                    </div>\n" +
                            "                                    <img src=\"/fonts/view.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                            "                                    <span>" + item.view + "</span>\n" +
                            "                                    <img src=\"/fonts/message3.svg\" alt=\"\" height=\"20px\" width=\"20px\">\n" +
                            "                                    <span>" + item.comment + "</span>\n" +
                            "                                    <img src=\"/fonts/time.svg\" alt=\"\" height=\"15px\" width=\"15px\">\n" +
                            "                                    <span>" + item.time + "</span>\n" +
                            "                                </div> ";
                        $('#question').append(text)
                    });
                }
                if (aj === 4) {
                    $.each(respond, function (i, item) {
                        const text = "<div class=\"Options\">\n" +
                            "                                    <a href=\"/questions/" + item.id + "\">\n" +
                            "                                        <div style=\"font-weight: bold\">" + item.title + "</div>\n" +
                            "                                    </a>\n" +
                            "                                    <div style=\"float: left;;width: 94%;color: #a7a7a7\">\n" +
                            "                                       <span style='color:#a75900;'>答：</span> <span>" + item.content + "</span>\n" +
                            "                                    </div>\n" +
                            "                                    <div style=\"float: right\">\n" +
                            "                                        <button class=\"noButton\" style=\"color: #0e90f4\" onclick='deleteObject(" + item.cid + ",4)'>删除</button>\n" +
                            "                                    </div>\n" +
                            "                                    <img src=\"/fonts/time.svg\" alt=\"\" height=\"15px\" width=\"15px\">\n" +
                            "                                    <span>" + item.time + "</span>\n" +
                            "                                </div>";
                        $('#answer').append(text)
                    });
                }
                $('#' + str + 'Count').text(respond.length)
            }
        }
    })
}

function getCode(type) {
    if (type === 1) {
        const ret = /^1[3456789]\d{9}$/;
        if (ret.test($('#set-phone').val())) {
            buttonTime(type);
        } else {
            alert('请您输入正确的手机号');
        }
    }
    if (type === 2) {
        buttonTime(type);
    }
}

function buttonTime(type) {
    $('#btn' + type).addClass('disabled');
    let time = 60;
    setInterval(function f() {
        if (time > 0) {
            time--;
            $('#btn' + type).text(time + 's后获取');
        } else {
            $('#btn' + type).text('获取验证码');
            $('#btn' + type).removeClass('disabled');
        }
    }, 1000);
}

//更改号码
function changePhone() {
    const ret = /^1[3456789]\d{9}$/;
    if (ret.test($('#set-phone').val())) {
        if ($('#set-phoneCode').val().length === 6) {
            $.ajax({
                url: '/set/changePhone',
                method: 'POST',
                data: ({
                    phone: $('#set-phone').val(),
                    code: $('#set-phoneCode').val(),
                }), success: function (res) {
                    alert(res.msg);
                    if (res.code === 200)
                        location.href = "#";
                    location.reload();
                }
            });
        } else {
            alert('请您输入获得的6位验证码');
        }
    } else {
        alert('请您输入正确的手机号');
    }
}

//更改性别
function changeSex(type) {
    $('#infoSex').val(type);
}

//更改密码
function changePassword() {
    const password = $('#set-newPassword').val();
    if (password === $('#set-confirm').val()) {
        if ($('#set-emailCode').val().length === 6) {
            $.ajax({
                url: '/set/changePassword',
                method: 'POST',
                data: ({
                    password: password,
                    code: $('#set-emailCode').val(),
                }), success: function (res) {
                    alert(res.msg);
                    if (res.code === 200)
                        location.href = "#";
                    location.reload();
                }
            });
        } else {
            alert('请您输入获得的6位验证码');
        }
    } else {
        alert("确认密码不一致")
    }

}

//更改资料
function changeInfo() {
    const name = $('#infoName').val();
    let sex = $('#infoSex').val();
    const describe = $('#infoDescribe').val();
    const identity = $('#infoIdentity').val();
    const agency = $('#infoAgency').val();
    if (sex === 'woman.svg') {
        sex = "2";
    }
    if (sex === 'man.svg') {
        sex = "1";
    }
    if (sex === 'sex.svg') {
        sex = "0";
    }
    if (name.length < 2 && name.length > 8) {
        alert('名称长度有误');
        return;
    }
    if (describe.length < 0 && describe.length > 40) {
        alert('个性签名长度有误');
        return;
    }
    $.ajax({
        url: '/set/changeInfo',
        method: 'POST',
        data: ({
            name: name,
            sex: sex,
            describe: describe,
            identity: identity,
            agency: agency,
        }), success: function (res) {
            alert(res.msg);
            if (res.code === 200)
                location.href = "#";
            location.reload();
        }
    })

}


function editorSetting(str) {
    location.href = str;
}

function deleteObject(id, type) {
    const mymessage = confirm("确认删除吗？此操作为不可逆操作，请您谨慎！！");
    if (mymessage === true) {
        $.ajax({
            url: '/set/changeStatus',
            type: 'POST',
            data: ({
                id: id,
                type: type,
            }), success: function (res) {
                alert(res.msg);
                if (res.code === 200)
                    location.href = "#";
                location.reload();
            }
        })
    }


}

//移除标签
function tagRemove() {
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

//页面跳转
function href(url) {
    location.href = url;
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

