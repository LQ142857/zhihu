package com.a7z.zhihu.service.impl;

import com.a7z.zhihu.dao.ArticleDao;
import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lq
 * @create 2020/3/25-22:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public String addOne(Article article) {
        articleDao.addOne(article);
        return articleDao.queryIdByCover(article.getCover());

    }

    @Override
    public Article findOne(String aid) {
        return articleDao.findOne(aid);

    }
}
