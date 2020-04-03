package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.ArticleGetVo;
import com.a7z.zhihu.entity.vo.ArticlePostVo;
import com.a7z.zhihu.entity.vo.CommentRootVo;
import com.a7z.zhihu.service.*;
import com.a7z.zhihu.util.DateKit;

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
        ArticleGetVo vo = articleService.findOneArticleVo(id);

        //作者信息
        User author = userService.findOne(vo.getUid());
        int fans = userService.getFansCount(vo.getUid());
        model.addObject("author", author);
        model.addObject("fans", fans);


        //文章信息
        model.addObject("article", vo);
        String s = DateKit.formatDateByUnixTime(vo.getTime());
        String[] s1 = s.split(" ");
        model.addObject("time", s1[0]);


        //评论信息
        List<CommentRootVo> allRootComment = commentService.getAllRootComment(id, "1");
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

    }


}
