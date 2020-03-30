package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/27-13:04
 */
public class Tag {

    private int tagId;
    private String name;
    private String status;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
