package com.a7z.zhihu.service.impl;

import com.a7z.zhihu.dao.*;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.po.User;
import com.a7z.zhihu.entity.vo.Get.ArticleDetailGetVo;
import com.a7z.zhihu.entity.vo.Get.ArticleInfoGetVo;
import com.a7z.zhihu.entity.vo.Get.SettingArticleGetVo;
import com.a7z.zhihu.entity.vo.pojo.IdTitle;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.util.DateKit;
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
    public void changeStatus(int id) {
        articleDao.updateStatus(id);
    }

    @Override
    public Article findOneArticle(int aid) {
        return articleDao.findOne(aid);

    }

    @Override
    public ArticleDetailGetVo findOneArticleVo(int id) {
        Article article = articleDao.findOne(id);
        return articlePo2vo(article);
    }

    @Override
    public int findAuthorArticleCount(int id) {
        return articleDao.queryAuthorArticleCount(id);
    }

    @Override
    public int getApprove(int id) {
        return attitudeDao.getAllAttitudeCount(id, "1", "1");
    }

    public int getDisapprove(int id) {
        return attitudeDao.getAllAttitudeCount(id, "1", "0");
    }

    @Override
    public List<ArticleDetailGetVo> getListByTime() {

        return transform(articleDao.queryListByTimeDescLimit10());
    }

    @Override
    public List<ArticleDetailGetVo> getListByView() {

        return transform(articleDao.queryListByViewDescLimit10());
    }

    @Override
    public List<ArticleDetailGetVo> getListByIdo(List<Integer> IdList) {
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

    @Override
    public List<IdTitle> findMyArticles(int author, int start) {
        return articleDao.queryListMyArticlesIdTitle(author, start);
    }

    @Override
    public List<ArticleInfoGetVo> findListArticleInfo(int id, int start) {
        List<ArticleInfoGetVo> list = articleDao.queryArticleInfo(id, start);
        for (ArticleInfoGetVo vo : list) {
            vo.setTime(DateKit.formatDateByUnixTime(Long.valueOf(vo.getTime()), "yyyy-MM-dd"));
        }
        return list;
    }

    @Override
    public List<SettingArticleGetVo> findSettingArticleGetVo(int id, int start) {
        return articleDao.querySettingArticleGetVo(id, start);
    }

    /**
     * 表中article数据转换成前台数据
     *
     * @param articleList
     * @return
     */
    private ArrayList<ArticleDetailGetVo> transform(List<Article> articleList) {
        ArrayList<ArticleDetailGetVo> list = new ArrayList<>();
        for (Article article : articleList) {
            ArticleDetailGetVo vo = articlePo2vo(article);
            list.add(vo);
        }
        return list;
    }

    private ArticleDetailGetVo articlePo2vo(Article article) {
        ArticleDetailGetVo getVo = new ArticleDetailGetVo();
        getVo.setTitle(article.getTitle());
        User user = userDao.findOneByUid(article.getAuthor());
        getVo.setUserName(user.getName());
        String s = userImgDao.queryAvatar(user.getUid());
        if (s != null) {
            getVo.setUserImg("/upload/users/" + s);

        } else {
            getVo.setUserImg("/upload/users/defaultUser.jpg");
        }
        getVo.setUid(user.getUid());
        getVo.setCover("/upload/articles/" + article.getCover());
        getVo.setContent(article.getContent());
        getVo.setArticleId(article.getArticleId());
        getVo.setTime(article.getTime());
        getVo.setViews(article.getViews());
        getVo.setApprove(attitudeDao.getAllAttitudeCount(article.getArticleId(), "1", "1"));
        getVo.setDisapprove(attitudeDao.getAllAttitudeCount(article.getArticleId(), "1", "0"));
        getVo.setComment(commentDao.findAllCommentsCount(article.getArticleId(), "1"));
        getVo.setFavorite(attentionDao.getPartAttentionCount(article.getArticleId(), "1"));
        getVo.setFollow(attentionDao.getPartAttentionCount(article.getArticleId(), "2"));
        return getVo;

    }


}
