package com.a7z.zhihu.controller;

import com.a7z.zhihu.entity.json.HeaderPageJson;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.Get.ArticleDetailGetVo;
import com.a7z.zhihu.entity.vo.Get.QuestionSimpleGetVo;
import com.a7z.zhihu.entity.vo.Get.TopicSimpleGetVo;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.service.QuestionService;
import com.a7z.zhihu.service.TopicService;
import com.a7z.zhihu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    TopicService topicService;
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
        List<ArticleDetailGetVo> listByView = articleService.getListByTime();
        model.addObject("articleListV", listByView);


        model.setViewName("/index");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/answer")
    public ModelAndView answer() {
        ModelAndView model = new ModelAndView();
        //导航栏信息
        HeaderPageJson header = new HeaderPageJson();
        header.setAnswer("active");
        model.addObject("header", header);
        //当前用户信息
        Object sessionUser = SecurityUtils.getSubject().getSession().getAttribute("user");
        User user = new User();
        if (sessionUser != null) {
            user = (User) sessionUser;
        }
        model.addObject("user", user);

        //问题信息
        List<QuestionSimpleGetVo> questionList = questionService.findSimpleListByTime(0,10);
        model.addObject("questionList", questionList);
        model.setViewName("/answer");

        return model;
    }

    @RequestMapping("/topic")
    public ModelAndView topic() {
        ModelAndView model = new ModelAndView();
        //头信息
        HeaderPageJson header = new HeaderPageJson();
        header.setTopic("active");
        model.addObject("header", header);
        //话题信息
        List<TopicSimpleGetVo> newestTopics = topicService.findNewestTopics();
        model.setViewName("/topic");
        model.addObject("topics", newestTopics);
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

    @RequestMapping(value = "/hot", method = RequestMethod.POST)
    public String flashHot(Model model) {
        List<ArticleDetailGetVo> listByView = articleService.getListByView();
        model.addAttribute("articleListV", listByView);
        return "index::flashContent";
    }

    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public String flashTime(Model model) {
        List<ArticleDetailGetVo> listByView = articleService.getListByTime();
        model.addAttribute("articleListV", listByView);
        return "index::flashContent";
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public String flashFollow(Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<Integer> ido = userService.queryIdo(user.getUid());
        List<ArticleDetailGetVo> listByView = articleService.getListByIdo(ido);
        model.addAttribute("articleListV", listByView);

        return "index::flashContent";
    }


}
