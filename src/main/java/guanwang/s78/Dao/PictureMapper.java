package guanwang.s78.Dao;

import guanwang.s78.pojo.Picture;
import guanwang.s78.pojo.PictureType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 声明这是一个Mybatis的数据操作接口
@Repository // 向springCongfin声明这是一个数据处理类
public interface PictureMapper {

    @Select("<script>" +
            "select * from picture_table where 1 = 1 " +
            "<if test='p.picture_name != null and p.picture_name != &quot;&quot;'>" +
            " and picture_name like concat('%', #{p.picture_name}, '%') " +
            "</if>" +
            "<if test='p.type_id != null and p.type_id != &quot;&quot;'>" +
            " and type_id = #{p.type_id} " +
            "</if>" +
            "<if test='p.picture_url != null and p.picture_url != &quot;&quot;'>" +
            " and picture_url like concat('%', #{p.picture_url}, '%') " +
            "</if>" +
            "<if test='p.create_time != null and p.create_time != &quot;&quot;'>" +
            " and create_date &gt; #{p.create_time} " +
            "</if>" +
            "<if test='p.modify_time != null and p.modify_time != &quot;&quot;'>" +
            " and modify_date &lt; #{p.modify_time} " +
            "</if>" +
            "</script>")
//    @Select("select * from picture_table")
    @Results(id = "pictureMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "picture_name", column = "picture_name"),
            @Result(property = "picture_url", column = "picture_url"),
            @Result(property = "create_time", column = "create_date"),
            @Result(property = "modify_time", column = "modify_date"),
            @Result(property = "type_id", column = "type_id"),
            @Result(property = "foreign_id", column = "foreign_id"),
    })
        // 根据权限名称查询或查询所有
    List<Picture> getPictures(@Param("p") Picture picture);


    @Select("SELECT * FROM picture_table WHERE ID = #{id}")
    @ResultMap(value = "pictureMap")
    // 根据id获取单个图片
    Picture getOnePicture(@Param("id") Integer id);

    @Select("SELECT * FROM picture_type_table")
    @Results(id = "pictureTypeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "type_name", column = "type_name"),
    })
    List<PictureType> getPictureTypes();

}
