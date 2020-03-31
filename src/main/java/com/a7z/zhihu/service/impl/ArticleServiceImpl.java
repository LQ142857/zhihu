package com.a7z.zhihu.service.impl;

import com.a7z.zhihu.dao.ArticleDao;
import com.a7z.zhihu.dao.UserDao;
import com.a7z.zhihu.dao.UserImgDao;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.ArticleGetVo;
import com.a7z.zhihu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lq
 * @create 2020/3/25-22:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserImgDao userImgDao;

    @Override
    public String addOne(Article article) {
        articleDao.addOne(article);
        return articleDao.queryIdByCover(article.getCover());

    }

    @Override
    public Article findOne(String aid) {
        return articleDao.findOne(aid);

    }

    @Override
    public List<ArticleGetVo> getListByTime() {
        ArrayList<ArticleGetVo> list = new ArrayList<>();

//        private String title;
//        private String userName;
//        private String userImg;
//        private int uid;
//        private String cover;
//        private String content;
//        private int articleId;
//        private int time;
//        private int agree;
//        private int disagree;
//        private int comment;
//        private int favorite;

        for (Article article : articleDao.queryListByTimeDescLimit10()) {
            int uid = article.getAuthor();
            User author = userDao.findOne(uid);
            ArticleGetVo getVo = new ArticleGetVo();
            getVo.setTitle(article.getTitle());
            getVo.setUserName(author.getName());
            getVo.setUserImg(userImgDao.queryAvatar(uid));
            getVo.setUid(uid);
            getVo.setCover(article.getCover());
            getVo.setContent(article.getContent());
            getVo.setArticleId(article.getArticleId());
            getVo.setTime(article.getTime());
//            getVo.setAgree();
//            getVo.setDisagree();
//            getVo.setComment();
//            getVo.setFavorite();



            list.add(getVo);
        }


        return list;
    }

    @Override
    public List<ArticleGetVo> getListByView() {
        ArrayList<ArticleGetVo> list = new ArrayList<>();
        for (Article article : articleDao.queryListByTimeDescLimit10()) {
            ArticleGetVo getVo = new ArticleGetVo();


            list.add(getVo);
        }


        return list;
    }
}
