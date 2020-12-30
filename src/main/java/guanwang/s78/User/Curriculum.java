package guanwang.s78.User;

// 课程表映射类
public class Curriculum {

    private Integer id;
    private String create_date;
    private String modify_date;
    private String curriculum_name;
    private Integer picture_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getCurriculum_name() {
        return curriculum_name;
    }

    public void setCurriculum_name(String curriculum_name) {
        this.curriculum_name = curriculum_name;
    }

    public Integer getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(Integer picture_id) {
        this.picture_id = picture_id;
    }

    public void setCourse_introduction(String course_introduction) {
    }
}
