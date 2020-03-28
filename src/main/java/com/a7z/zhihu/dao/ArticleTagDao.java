package com.a7z.zhihu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 中间表
 *
 * @author lq
 * @create 2020/3/27-13:18
 */
@Repository
public interface ArticleTagDao {

    @Select({
            "SELECT name \n" +
                    "from tag ,(SELECT tag_id FROM article_tag WHERE article_id =#{aid}) a\n" +
                    "WHERE tag.tag_id =a.tag_id"
    })
    List<String> findTagsByAid(String aid);


    @Insert({
            "INSERT INTO article_tag(article_id,tag_id) VALUES (#{aid},#{tid})"
    })
    void addTagsForArticle(String aid, String tid);

}
