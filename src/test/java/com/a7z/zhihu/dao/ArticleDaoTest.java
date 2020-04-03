package com.a7z.zhihu.dao;

import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.vo.ArticleGetVo;
import com.a7z.zhihu.entity.vo.CommentRootVo;
import com.a7z.zhihu.service.ArticleService;
import com.a7z.zhihu.service.CommentService;
import com.a7z.zhihu.util.DateKit;
import com.a7z.zhihu.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

/**
 * @author lq
 * @create 2020/3/25-22:28
 */

@SpringBootTest
class ArticleDaoTest {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;

    @Test
    void testDateKit() {
        CommentRootVo allChildComment = commentService.getAllChildComment(10014, 10004, "1");
        System.out.println(allChildComment);

    }

    @Test
    void testInsert() {
        List<Article> articles = articleDao.queryListByTimeDescLimit10();
        for (Article article : articles) {
            System.out.println(article);
        }


    }

    @Test
    void testUtil() {
        try {
            MailUtil.sendMail("测试", "欢迎来到7z", "2235648251@qq.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
