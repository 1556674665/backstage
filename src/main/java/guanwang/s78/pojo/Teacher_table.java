package guanwang.s78.pojo;

import java.sql.Timestamp;

public class Teacher_table {
    private int id;
    private Timestamp create_date;
    private Timestamp modify_date;
    private String name;
    private String brief_introduction;
    private int age;
    private int gender;
    private int QQ_number;
    private String wechat_number;
    private String introduce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getModify_date() {
        return modify_date;
    }

    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getQQ_number() {
        return QQ_number;
    }

    public void setQQ_number(int QQ_number) {
        this.QQ_number = QQ_number;
    }

    public String getWechat_number() {
        return wechat_number;
    }

    public void setWechat_number(String wechat_number) {
        this.wechat_number = wechat_number;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
