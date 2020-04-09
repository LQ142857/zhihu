package com.a7z.zhihu.service;

import java.util.List;

/**
 * @author lq
 * @create 2020/3/27-13:32
 */
public interface TagService {

    void addTag(String[] name);

    void addTagsForArticle(String aid,String[] tags);

    List<String> findArticleTags(String aid);




}
