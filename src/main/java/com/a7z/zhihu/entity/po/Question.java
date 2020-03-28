package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/27-19:57
 */
public class Question {
    private String id;
    private String content;
    private int reply;
    private int view;
    private String author;
    private int time;
    private String anonymous;

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", reply='" + reply + '\'' +
                ", view='" + view + '\'' +
                ", author='" + author + '\'' +
                ", time=" + time +
                ", anonymous='" + anonymous + '\'' +
                '}';
    }
}
