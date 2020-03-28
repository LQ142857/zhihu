package com.a7z.zhihu.entity.json;

/**顶部导航栏,三个按钮(首页,话题,等你来答)的active情况
 * @author lq
 * @create 2020/3/24-16:11
 */
public class HeaderPageJson {
    private String index;
    private String topic;
    private String answer;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "HeaderPageVo{" +
                "index='" + index + '\'' +
                ", column='" + topic + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
