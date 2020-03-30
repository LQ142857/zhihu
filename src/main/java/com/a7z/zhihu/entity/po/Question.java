package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/27-19:57
 */
public class Question {
    private int questionId;
    private String content;
    private int reply;
    private int view;
    private int author;
    private String time;
    private String anonymous;
    private String status;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", content='" + content + '\'' +
                ", reply=" + reply +
                ", view=" + view +
                ", author=" + author +
                ", time='" + time + '\'' +
                ", anonymous='" + anonymous + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
