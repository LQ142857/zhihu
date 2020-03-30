package com.a7z.zhihu.dao;

import com.a7z.zhihu.entity.po.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
            "SELECT * FROM Article WHERE aid =#{aid}"
    })
    @Results(id = "Article", value = {
            @Result(id = true, property = "articleId", column = "articleId"),
            @Result(property = "title", column = "title"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "author", column = "author"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "time"),
            @Result(property = "views", column = "views"),
            @Result(property = "status", column = "status")
    })
    Article findOne(String aid);

    @Select({
            "SELECT article_id FROM article WHERE cover =#{cover}"
    })
    String queryIdByCover(String cover);
}

