package guanwang.s78.Dao;

import guanwang.s78.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //判断管理员登录
    List<Administrators_table> selectusername();
    //查找密码
    String selectpassword(String user_name);
    //修改密码
    int updatepassword(String password,String name);

    //查询管理员
    List<Administrators_table> selectAdministrators(String user_name);
    //删除管理员
    int deladministrators(String id);
    //添加管理员
    int insertadministrators(String create_date, String modify_date, String user_name, String name, String password, String gender);
    //展示管理员
    Administrators_table selectAdministrators_s(String id);
    //修改管理员
    int updateadministrators(String name,String gender,String modify_date,String id);

    //查询学校新闻
    List<College_News_table> selectCollege_News_table(String title);
    //删除学校新闻
    int delCollege_News_table(String id);
    //添加学校新闻
    int insertCollege_News_table(String create_date, String modify_date, String title, String source, String content);
    //展示学校新闻
    College_News_table selectCollege_News_table_s(String id);
    //修改学校新闻
    int updateCollege_News_table(String title,String source,String content,String modify_date,String id);

    //查询招生就业
    List<Enrollment_and_employment_table> selectEnrollment_and_employment_table(String title);
    //删除招生就业
    int delEnrollment_and_employment_table(String id);
    //添加招生就业
    int insertEnrollment_and_employment_table(String create_date, String modify_date, String title, String source, String content);
    //展示招生就业
    Enrollment_and_employment_table selectEnrollment_and_employment_table_s(String id);
    //修改招生就业
    int updateEnrollment_and_employment_table(String title,String source,String content,String modify_date,String id);

    //查询硕士工作站
    List<Master_workstation_table> selectMaster_workstation_table(String title);
    //删除硕士工作站
    int delMaster_workstation_table(String id);
    //添加硕士工作站
    int insertMaster_workstation_table(String create_date, String modify_date, String title, String source, String content);
    //展示硕士工作站
    Master_workstation_table selectMaster_workstation_table_s(String id);
    //修改硕士工作站
    int updateMaster_workstation_table(String title,String source,String content,String modify_date,String id);

    //查询学校介绍
    List<School_introduction_table> selectSchool_introduction_table(String school_name);
    //删除学校介绍
    int delSchool_introduction_table(String id);
    //添加学校介绍
    int insertSchool_introduction_table(String create_date, String modify_date, String school_name, String college_honors, String teaching_advantages,String brief_introduction);
    //展示学校介绍
    School_introduction_table selectSchool_introduction_table_s(String id);
    //修改学校介绍
    int updateSchool_introduction_table(String brief_introduction,String school_name,String college_honors,String teaching_advantages,String modify_date,String id);

    //查询来校路线
    List<Route_to_school_table> selectRoute_to_school_table(String school_name);
    //删除来校路线
    int delRoute_to_school_table(String id);
    //添加来校路线
    int insertRoute_to_school_table(String school_name, String registration_telephone, String school_address, String school_website, String nearby_bus_stop,String nearby_bus,String modify_date,String create_date);
    //展示来校路线
    Route_to_school_table selectRoute_to_school_table_s(String id);
    //修改来校路线
    int updateRoute_to_school_table(String school_name,String registration_telephone,String school_address,String school_website,String nearby_bus_stop,String nearby_bus,String modify_date,String id);

}
