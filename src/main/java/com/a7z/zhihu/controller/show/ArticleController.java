package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.entity.json.AgreeJson;
import com.a7z.zhihu.entity.json.ResultJson;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.Attitude;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.po.UserRelation;
import com.a7z.zhihu.entity.vo.Get.ArticleDetailGetVo;
import com.a7z.zhihu.entity.vo.Post.ArticlePostVo;
import com.a7z.zhihu.entity.vo.Get.CommentRootGetVo;
import com.a7z.zhihu.service.*;
import com.a7z.zhihu.util.DateKit;

import com.a7z.zhihu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author lq
 * @create 2020/3/25-21:52
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    AttitudeService attitudeService;
    @Autowired
    TopicService topicService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    AttentionService attentionService;
    @Autowired
    CommentService commentService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getOneArticle(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView();
        ArticleDetailGetVo vo = articleService.findOneArticleVo(id);
        //登录状态
        Object sessionUser = SecurityUtils.getSubject().getSession().getAttribute("user");
        boolean login = false;
        boolean content = false;
        if (sessionUser != null) {
            login = true;
            User user = (User) sessionUser;
            int ido;
            try {
                ido = userService.findIdo(user.getUid(), vo.getUid());
            } catch (Exception e) {
                ido = 0;
            }


            if (vo.getUid() == ido)
                content = true;
        }
        model.addObject("login", login);
        model.addObject("content", content);
        //作者信息
        User author = userService.findOne(vo.getUid());
        int fans = userService.getFansCount(vo.getUid());
        model.addObject("author", author);
        model.addObject("fans", fans);
        String key = "articleView" + id;
        if (!redisUtil.hasKey(key)) {
            redisUtil.set(key, articleService.getViews(id));
        }
        int views = (int) redisUtil.incr(key, 1);
        if (views % 100 == 0) {
            int oldView = articleService.getViews(id);
            articleService.changeViews(id, views);
            if (vo.getTopic() != 0) {
                int add = views - oldView;
                topicService.changeHot(vo.getTopic(), topicService.getHot(vo.getTopic()) + add);
                redisUtil.set("topicHot" + vo.getTopic(), (Integer) redisUtil.get("topicHot" + vo.getTopic()) + add);
            }
        }
        //关注


        //文章信息
        model.addObject("views", views);
        model.addObject("article", vo);
        String s = DateKit.formatDateByUnixTime(vo.getTime());
        String[] s1 = s.split(" ");
        model.addObject("time", s1[0]);


        //评论信息
        List<CommentRootGetVo> allRootComment = commentService.getAllRootComment(id, "1");
        model.addObject("comment", allRootComment);

        model.setViewName("article");
        return model;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addOneArticle(ArticlePostVo postVo) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Article article = new Article();
        article.setCover(postVo.getCover());
        article.setAuthor(user.getUid());//代替
        article.setTitle(postVo.getTitle());
        article.setContent(postVo.getContent());
        article.setTime(DateKit.getCurrentUnixTime());
        String[] tags = postVo.getTags().split("&&");
        System.out.println(article);
        String aid = articleService.addOne(article);
        tagService.addTag(tags);
        tagService.addTagsForArticle(aid, tags);
        redisUtil.del("user" + user.getUid());


    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public AgreeJson changeAttitude(int uid, int id, String kind, String good) {
        Attitude at = new Attitude();
        AgreeJson json = new AgreeJson();
        Attitude attitude = attitudeService.findAttitude(uid, id, kind);
        if (attitude != null) {
            attitudeService.changeAttitude(attitude, good);
        } else {
            at.setId(id);
            at.setUid(uid);
            at.setGood(good);
            at.setKind(kind);
            at.setTime(DateKit.getCurrentUnixTime());
            attitudeService.addAttitude(at);
        }
        json.setNum(attentionService.findAuthorQuestionCount(id, kind));
        json.setStatus(good);
        return json;
    }
}
