package com.a7z.zhihu.controller.show;

import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.ArticlePostVo;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.service.TagService;
import com.a7z.zhihu.util.DateKit;
import com.a7z.zhihu.util.UUIDUtil;
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
 * @create 2020/3/25-21:52
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getOneArticle(@PathVariable("id") String id) {
        ModelAndView model = new ModelAndView();
        Article one = articleService.findOne(id);
        model.addObject(one);
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
        article.setTime(String.valueOf(DateKit.getCurrentUnixTime()));
        String[] tags = postVo.getTags().split("&&");
        System.out.println(article);
        String aid = articleService.addOne(article);
        tagService.addTag(tags);
        tagService.addTagsForArticle(aid, tags);


    }


}
