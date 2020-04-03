package com.a7z.zhihu.dao;

import com.a7z.zhihu.entity.po.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lq
 * @create 2020/3/25-22:06
 */
@Repository
public interface ArticleDao {
    @Insert({
            "INSERT INTO article (cover,author,title,content,time) " +
                    "VALUES(#{cover},#{author},#{title},#{content},#{time})"
    })
    void addOne(Article article);


    @Select({
            "SELECT * FROM Article WHERE article_id =#{articleId}"
    })
    @Results(id = "Article", value = {
            @Result(id = true, property = "articleId", column = "article_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "author", column = "author"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "time"),
            @Result(property = "views", column = "views"),
            @Result(property = "status", column = "status"),
            @Result(property = "topic", column = "topic")
    })
    Article findOne(int aid);

    @Select({
            "SELECT article_id FROM article WHERE cover =#{cover}"
    })
    String queryIdByCover(String cover);

    @Select({
            "select count(*) from article where author =#{id} and status='1'  "
    })
    int queryAllArticleCount(int id);

    @Select({
            "SELECT * FROM article where status='1' ORDER BY time  DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByTimeDescLimit10();


    @Select({
            "SELECT * FROM article where status='1' ORDER BY views  DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByViewDescLimit10();

    @Select({
            "SELECT * FROM article where author=#{id} and status='1' ORDER BY time DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByAuthorDescLimit10(int id);


}

