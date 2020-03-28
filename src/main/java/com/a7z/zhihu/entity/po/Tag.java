package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/27-13:04
 */
public class Tag {

    private String tagId;
    private String name;


    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId='" + tagId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
