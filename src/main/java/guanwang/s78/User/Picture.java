package guanwang.s78.User;

public class Picture {

    private Integer id;
    private String picture_name;
    private String picture_url;
    private String create_time;
    private String modify_time;
    private Integer type_id;
    private Integer foreign_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(Integer foreign_id) {
        this.foreign_id = foreign_id;
    }
}
