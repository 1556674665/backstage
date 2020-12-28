package guanwang.s78.User;

import java.sql.Timestamp;

public class School_introduction_table {
    private int id;
    private Timestamp create_date;
    private Timestamp modify_date;
    private String brief_introduction;
    private String teaching_advantages;
    private String college_honors;
    private String school_name;

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }

    public String getTeaching_advantages() {
        return teaching_advantages;
    }

    public void setTeaching_advantages(String teaching_advantages) {
        this.teaching_advantages = teaching_advantages;
    }

    public String getCollege_honors() {
        return college_honors;
    }

    public void setCollege_honors(String college_honors) {
        this.college_honors = college_honors;
    }

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

    public String getBrief_introduction() {
        return brief_introduction;
    }

}
