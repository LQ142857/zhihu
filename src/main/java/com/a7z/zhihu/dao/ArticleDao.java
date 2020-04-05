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
    /**
     * 向数据库中插入一篇文章
     *
     * @param article 文章po
     */
    @Insert({
            "INSERT INTO article (cover,author,title,content,time) " +
                    "VALUES(#{cover},#{author},#{title},#{content},#{time})"
    })
    void addOne(Article article);


    /**
     * 根据id搜索一篇文章信息
     *
     * @param aid 文章id
     * @return
     */
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

    /**
     * 根据封面搜索文章id
     *
     * @param cover 封面名称
     * @return
     */
    @Select({
            "SELECT article_id FROM article WHERE cover =#{cover}"
    })
    String queryIdByCover(String cover);

    /**
     * 搜索作者的所有文章数量
     *
     * @param id 作者id
     * @return
     */
    @Select({
            "select count(*) from article where author =#{id} and status='1'  "
    })
    int queryAllArticleCountByAuthorId(int id);

    /**
     * 搜索话题的所有文章数量
     *
     * @param id 话题id
     * @return
     */
    @Select({
            "select  count(*) from article where topic=#{id} and status='1' "
    })
    int queryAllArticleCountByTopicId(int id);

    /**
     * 查询话题旗下三条最热门文章的标题
     * @param id 话题id
     * @return
     */
    @Select({
            "SELECT title FROM article WHERE topic =#{id} AND `status` ='1' ORDER BY views DESC LIMIT 3"
    })
    List<String> queryTitleListByTopic(int id);

    /**
     * 按照时间降序搜索10篇文章
     *
     * @return
     */
    @Select({
            "SELECT * FROM article where status='1' ORDER BY time  DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByTimeDescLimit10();


    /**
     * 按照浏览量降序搜索10篇文章
     *
     * @return
     */
    @Select({
            "SELECT * FROM article where status='1' ORDER BY views  DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByViewDescLimit10();

    /**
     * 根据作者id按发布时间搜索其最近的10篇文章
     *
     * @param id 作者id
     * @return
     */
    @Select({
            "SELECT * FROM article where author=#{id} and status='1' ORDER BY time DESC LIMIT 10"
    })
    @ResultMap("Article")
    List<Article> queryListByAuthorDescLimit10(int id);

    /**
     * 根据话题发表时间降序查找旗下的文章
     *
     * @param id    话题id
     * @param start 开始起点
     * @param count 查询数量
     * @return
     */
    @Select({
            "SELECT * FROM article WHERE topic= #{id} and status ='1' ORDER BY time LIMIT #{start},#{count};"
    })
    @ResultMap("Article")
    List<Article> queryListByTopicIdOrderTime(int id, int start, int count);

    /**
     * 根据话题热度降序查找旗下的文章
     *
     * @param id    话题id
     * @param start 开始起点
     * @param count 查询数量
     * @return
     */
    @Select({
            "SELECT * FROM article WHERE topic= #{id} and status ='1' ORDER BY views desc LIMIT #{start},#{count};"
    })
    @ResultMap("Article")
    List<Article> queryListByTopicIdOrderViews(int id, int start, int count);

}

