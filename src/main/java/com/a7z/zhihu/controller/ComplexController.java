package com.a7z.zhihu.controller;

import com.a7z.zhihu.entity.json.HeaderPageJson;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author lq
 * @create 2020/3/24-15:40
 */
@Controller
public class ComplexController {
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setIndex("active");

        model.setViewName("/index");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/answer")
    public ModelAndView answer() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setAnswer("active");

        model.setViewName("/answer");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/topic")
    public ModelAndView column() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        header.setTopic("active");

        model.setViewName("/topic");
        model.addObject("header", header);
        return model;
    }

    @RequestMapping("/editor")
    public ModelAndView editor() {
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        model.addObject("header", header);
        model.setViewName("/editor");
        return model;
    }


}
