package com.a7z.zhihu.controller;


import com.a7z.zhihu.util.UploadUtil;
import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 配置beditor文版编辑器
 */
@Controller
public class ServerController {
    @RequestMapping("/config")
    @ResponseBody
    public String config(HttpServletRequest request) {
        String rootPath = UploadUtil.getUploadFilePath() + "upload"+File.separator;
        String msg= new ActionEnter(request, rootPath).exec();
//        System.out.println(msg);
        return msg;
    }
}