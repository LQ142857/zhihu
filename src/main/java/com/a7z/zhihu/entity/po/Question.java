package com.a7z.zhihu.entity.po;

/**
 * @author lq
 * @create 2020/3/27-19:57
 */
public class Question {
    private int questionId;
    private String content;
    private int view;
    private int author;
    private int time;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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
                ", view=" + view +
                ", author=" + author +
                ", time='" + time + '\'' +
                ", anonymous='" + anonymous + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
