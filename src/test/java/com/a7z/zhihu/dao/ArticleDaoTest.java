package com.a7z.zhihu.dao;

import com.a7z.zhihu.entity.po.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lq
 * @create 2020/3/25-22:28
 */

@SpringBootTest
public class ArticleDaoTest {
    @Autowired
    ArticleDao articleDao;

    @Test
    public void testInsert() {
        Article article = new Article();

        article.setAid("2313");
        article.setCover("2313");
        article.setAuthor("2313");
        article.setTitle("2313");
        article.setContent("2313");
        article.setTime("2313");
        article.setViews(2313);
        article.setStatus("0");

        articleDao.addOne(article);

    }


}
