package com.a7z.zhihu.controller;

import com.a7z.zhihu.entity.json.HeaderPageJson;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lq
 * @create 2020/3/24-15:40
 */
@Controller
public class ComplexController {
    @Autowired
    ArticleService articleService;


    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        //设置<header>标签内信息
        HeaderPageJson header = new HeaderPageJson();
        header.setIndex("active");

        //添加用户信息
        Object sessionUser = SecurityUtils.getSubject().getSession().getAttribute("user");
        User user = new User();
        if (sessionUser != null) {
            user = (User) sessionUser;
        }
        model.addObject("user", user);

        //文章列表（默认按热度排名）
        List<Article> listByView = articleService.getListByView();
        model.addObject("articleListV", listByView);


        model.setViewName("/index");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/answer")
    public ModelAndView answer() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setAnswer("active");
        Object sessionUser = SecurityUtils.getSubject().getSession().getAttribute("user");
        User user = new User();
        if (sessionUser != null) {
            user = (User) sessionUser;
        }

        model.addObject("user", user);

        model.setViewName("/answer");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/topic")
    public ModelAndView topic() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setTopic("active");


        model.setViewName("/topic");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setTopic("active");


        model.setViewName("/column");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/topicEditor")
    public ModelAndView test2() {
        ModelAndView model = new ModelAndView();


        model.setViewName("/topicEditor");
        return model;
    }

    @RequestMapping("/articleEditor")
    public ModelAndView editor() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        model.addObject("header", header);
        model.setViewName("/articleEditor");
        return model;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("/login");
        return model;
    }


}
