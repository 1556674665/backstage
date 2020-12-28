package guanwang.s78.Dao;

import guanwang.s78.pojo.Curriculum;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CurriculumMapper {

    @Select("<script>" +
            "select * from curriculum_table where 1 = 1 " +
            "<if test='c.curriculum_name != null and c.curriculum_name != &quot;&quot;'>" +
            " and curriculum_name like concat('%', #{c.curriculum_name}, '%') " +
            "</if>" +
            "</script>")
    @Results(id = "curriculumMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "create_date", column = "create_date"),
            @Result(property = "modify_date", column = "modify_date"),
            @Result(property = "curriculum_name", column = "curriculum_name"),
            @Result(property = "course_introduction", column = "course_introduction"),
    })
    // 根据权限名称查询或查询所有
    List<Curriculum> getCurriculums(@Param("c") Curriculum curriculum);

    @Select("SELECT * FROM curriculum_table WHERE id = #{id}")
    @ResultMap(value = "curriculumMap")
    // 根据id获取单个课程
    Curriculum getOneCurriculums(@Param("id") Integer id);

    @Update("UPDATE curriculum_table " +
            " SET curriculum_name = #{c.curriculum_name}, course_introduction = #{c.course_introduction} " +
            " WHERE id = #{c.id}")
    int updateCurriculum(@Param("c") Curriculum curriculum);





}
