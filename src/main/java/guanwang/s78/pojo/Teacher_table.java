package guanwang.s78.pojo;

import java.sql.Timestamp;

public class Teacher_table {
    private int id;
    private Timestamp create_date;
    private Timestamp modify_date;
    private String name;
    private String introduce;
    private String position;
    private String technical_expertise;
    private String project_experience;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTechnical_expertise() {
        return technical_expertise;
    }

    public void setTechnical_expertise(String technical_expertise) {
        this.technical_expertise = technical_expertise;
    }

    public String getProject_experience() {
        return project_experience;
    }

    public void setProject_experience(String project_experience) {
        this.project_experience = project_experience;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
