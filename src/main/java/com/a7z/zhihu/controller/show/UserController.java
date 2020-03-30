package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.entity.json.HeaderPageJson;
import com.a7z.zhihu.entity.vo.RegisterVo;
import com.a7z.zhihu.entity.json.UserRegisterJson;
import com.a7z.zhihu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lq
 * @create 2020/3/24-17:08
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getUserInfo(@PathVariable String id) {
        System.out.println("do");
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        model.addObject("header", header);
        System.out.println(id);
        model.setViewName("/user");

        return model;

    }


}
