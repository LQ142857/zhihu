package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.dao.UserImgDao;
import com.a7z.zhihu.entity.json.HeaderPageJson;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.Get.UserGetVo;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.service.AuthenticateService;
import com.a7z.zhihu.service.UserService;
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
    UserImgDao userImgDao;
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
        UserGetVo vo = new UserGetVo();
        User user = userService.findOne(id);
        vo.setUid(id);
        vo.setName(user.getName());
        vo.setIntroduction(user.getIntroduction());
        String avatar = userImgDao.queryAvatar(id);

        vo.setAvatar((avatar != null) ? "/upload/users" + avatar : "/upload/users/defaultUser.jpg");

        String cover = userImgDao.queryCover(id);
        vo.setCover((cover != null) ? "/upload/users" + cover : "/upload/users/defaultCover.jpg");

        String identity = authenticateService.getIdentity(id);
        vo.setIdentity(identity == null ? "" : identity);

        String agency = authenticateService.getAgency(id);
        vo.setAgency(agency == null ? "" : agency);


        vo.setSex(user.getSex());
        vo.setFansCount(userService.getFansCount(id));
        vo.setIdoCount(userService.getIdoCount(id));
        vo.setDynamicCount(0);
        vo.setArticleCount(articleService.getAllArticleCount(id));
        vo.setAnswerCount(0);
        vo.setQuestionCount(0);
        vo.setTopicCount(0);
        vo.setFavoriteCount(0);


        model.addObject("user", vo);
        model.setViewName("/user");

        return model;

    }


}
