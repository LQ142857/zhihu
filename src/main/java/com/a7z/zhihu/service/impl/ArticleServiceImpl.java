package com.a7z.zhihu.service.impl;

import com.a7z.zhihu.dao.*;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.Attitude;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.ArticleGetVo;
import com.a7z.zhihu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    AttitudeDao attitudeDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    AttentionDao attentionDao;

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

        return transform(articleDao.queryListByTimeDescLimit10());
    }

    @Override
    public List<ArticleGetVo> getListByView() {

        return transform(articleDao.queryListByViewDescLimit10());
    }

    @Override
    public List<ArticleGetVo> getListByIdo(List<Integer> IdList) {
        ArrayList<Article> list = new ArrayList<>();
        for (Integer id : IdList) {
            List<Article> articles = articleDao.queryListByAuthorDescLimit10(id);
            for (Article article : articles) {
                list.add(article);
            }
        }

        Collections.sort(list);
        for (Article article : list) {
            System.out.println(article);
        }
        return transform(list);
    }

    /**
     * 表中article数据转换成前台数据
     *
     * @param articleList
     * @return
     */
    private ArrayList<ArticleGetVo> transform(List<Article> articleList) {
        ArrayList<ArticleGetVo> list = new ArrayList<>();
        for (Article article : articleList) {
            int uid = article.getAuthor();
            User author = userDao.findOneByUid(uid);
            ArticleGetVo getVo = new ArticleGetVo();
            getVo.setTitle(article.getTitle());
            getVo.setUserName(author.getName());
            String s = userImgDao.queryAvatar(uid);
            if (s != null) {
                getVo.setUserImg("/upload/users/" + s);

            } else {
                getVo.setUserImg("/upload/users/defaultUser.jpg");
            }
            getVo.setUid(uid);
            getVo.setCover("/upload/articles/" + article.getCover());
            getVo.setContent(article.getContent());
            getVo.setArticleId(article.getArticleId());
            getVo.setTime(article.getTime());
            getVo.setAgree(attitudeDao.getAllAttentionCount(article.getArticleId(), "1", "1"));
            getVo.setDisagree(attitudeDao.getAllAttentionCount(article.getArticleId(), "1", "0"));
            getVo.setComment(commentDao.findAllCommentsCount(article.getArticleId(), "1"));
            getVo.setFavorite(attentionDao.getPartAttentionCount(article.getArticleId(), "1"));
            list.add(getVo);
        }
        return list;
    }
}
