package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.dao.UserImgDao;
import com.a7z.zhihu.entity.json.HeaderPageJson;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.Get.UserGetVo;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.service.AuthenticateService;
import com.a7z.zhihu.service.UserService;
import com.a7z.zhihu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    AuthenticateService authenticateService;
    @Autowired
    ArticleService articleService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getUserInfo(@PathVariable int id) {
        //导航栏信息
        ModelAndView model = new ModelAndView();
        HeaderPageJson header = new HeaderPageJson();
        model.addObject("header", header);

        //设置用户信息
        UserGetVo vo = userService.getUserGetVo(id);
        switch (vo.getSex()) {
            case "1":
                vo.setSex("man.svg");
                break;
            case "2":
                vo.setSex("woman.svg");
                break;
            default:
                vo.setSex("sex.svg");
                break;
        }

        Integer uid = (Integer) SecurityUtils.getSubject().getPrincipal();
        //登录信息
        boolean content = false;
        if (uid != null) {
            int ido;
            try {
                ido = userService.findIdo(uid, id);
            } catch (Exception e) {
                ido = 0;
            }
            if (vo.getUid() == ido)
                content = true;
        }
        model.addObject("content", content);

        model.addObject("user", vo);
        model.addObject("subject", uid);
        model.setViewName("user");

        return model;

    }


}
