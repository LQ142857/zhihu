package com.a7z.zhihu.service.impl;

import com.a7z.zhihu.dao.ArticleTagDao;
import com.a7z.zhihu.dao.TagDao;
import com.a7z.zhihu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lq
 * @create 2020/3/27-13:38
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;
    @Autowired
    ArticleTagDao articleTagDao;

    @Override
    public void addTag(String[] name) {
        for (String s : name) {
            String id = tagDao.findIdByName(s);
            if (null == id || id.equals("")) {
                tagDao.addOne(s);
            }
        }
    }

    @Override
    public void addTagsForArticle(String aid, String[] tid) {
        for (String s : tid) {
            String id = tagDao.findIdByName(s);
            articleTagDao.addTagsForArticle(aid, id);
        }

    }

    @Override
    public List<String> findArticleTags(String aid) {
        return articleTagDao.findTagsByAid(aid);
    }


}
