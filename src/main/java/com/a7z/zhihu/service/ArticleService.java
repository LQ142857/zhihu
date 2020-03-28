package com.a7z.zhihu.service;

import com.a7z.zhihu.entity.po.Article;

/**
 * @author lq
 * @create 2020/3/25-22:40
 */
public interface ArticleService {

    void addOne(Article article);

    Article findOne(String id);


}
