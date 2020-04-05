package com.a7z.zhihu.service;

import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.vo.Get.ArticleDetailGetVo;

import java.util.List;

/**
 * @author lq
 * @create 2020/3/25-22:40
 */
public interface ArticleService {
    /**
     * @param article
     * @return文章主键
     */
    String addOne(Article article);

    Article findOneArticle(int id);

    ArticleDetailGetVo findOneArticleVo(int id);

    int getAllArticleCount(int id);

    int getDisapprove(int id);

    int getApprove(int id);

    List<ArticleDetailGetVo> getListByTime();

    List<ArticleDetailGetVo> getListByView();

    List<ArticleDetailGetVo> getListByIdo(List<Integer> IdList);


}
