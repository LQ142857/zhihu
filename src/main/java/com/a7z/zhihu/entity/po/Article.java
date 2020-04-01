package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/25-21:59
 */
public class Article implements Comparable {
    private int articleId;
    private String cover;
    private int author;
    private String title;
    private String content;
    private int time;
    private int views;
    private String status;
    private int topic;

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", cover='" + cover + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", status='" + status + '\'' +
                ", topic=" + topic +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Article article = (Article) o;
        return article.getTime() - this.getTime();
    }
}
