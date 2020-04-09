package com.a7z.zhihu.service;

import com.a7z.zhihu.entity.po.Article;
import com.a7z.zhihu.entity.vo.Get.ArticleDetailGetVo;
import com.a7z.zhihu.entity.vo.Get.ArticleInfoGetVo;
import com.a7z.zhihu.entity.vo.Get.SettingArticleGetVo;
import com.a7z.zhihu.entity.vo.pojo.IdTitle;

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

    void changeStatus(int id);

    Article findOneArticle(int id);

    ArticleDetailGetVo findOneArticleVo(int id);

    int findAuthorArticleCount(int id);

    int getDisapprove(int id);

    int getApprove(int id);

    List<ArticleDetailGetVo> getListByTime();

    List<ArticleDetailGetVo> getListByView();

    List<ArticleDetailGetVo> getListByIdo(List<Integer> IdList);

    List<IdTitle> findMyArticles(int author, int start);

    List<ArticleInfoGetVo> findListArticleInfo(int id, int start);

    List<SettingArticleGetVo> findSettingArticleGetVo(int id, int start);

}
