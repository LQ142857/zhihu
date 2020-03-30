package com.a7z.zhihu.service;

import com.a7z.zhihu.entity.po.Article;

/**
 * @author lq
 * @create 2020/3/25-22:40
 */
public interface ArticleService {
    /**
     *
     * @param article
     * @return文章主键
     */
    String addOne(Article article);

    Article findOne(String id);


}
