package com.a7z.zhihu.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author lq
 * @create 2020/3/27-13:05
 */
@Repository
public interface TagDao {

    @Insert({
            "INSERT INTO tag (name) VALUE(#{name})"
    })
    void addOne(String name);


    @Select({
            "SELECT tag_id FROM tag WHERE name =#{name}"
    })
    String findIdByName(String name);


    @Select({
            "SELECT name FROM tag WHERE tag_id =#{id}"
    })
    String findNameById(int id);


}
