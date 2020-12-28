package guanwang.s78.PageController;

import guanwang.s78.Dao.PictureMapper;
import guanwang.s78.pojo.Picture;
import guanwang.s78.pojo.PictureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PictureManageController
{

    @Autowired
    PictureMapper pictureMapper;

    @RequestMapping("/PictureManage/{operateType}")
    @ResponseBody
    public Map pictureManage(@PathVariable("operateType") String type, HttpServletRequest httpServletRequest){

        // 返回数据的总集合
        Map dataMap = new HashMap();

        // 查询所有图片或根据条件查询
        if (type.equals("SearchPermission")){
            Picture picture = new Picture();
            picture.setPicture_name(httpServletRequest.getParameter("p_name"));
            picture.setPicture_url(httpServletRequest.getParameter("p_url"));
            String p_typeId = httpServletRequest.getParameter("p_type");
            if (p_typeId != null && p_typeId != ""){
                picture.setType_id(Integer.valueOf(p_typeId));
            }
            picture.setCreate_time(httpServletRequest.getParameter("inputdate_start"));
            picture.setModify_time(httpServletRequest.getParameter("inputdate_end"));

            List<Picture> dbPictures = pictureMapper.getPictures(picture);
            List<Map> mapList = new ArrayList<Map>();
            for (Picture dbPicture : dbPictures) {
                Map map = new HashMap();
                map.put("id", dbPicture.getId());
                map.put("picture_name", dbPicture.getPicture_name());
                map.put("picture_url", dbPicture.getPicture_url());
//                switch (dbPicture.getType()){
//                    case 1: map.put("type", "轮播图片"); break;
//                    case 2: map.put("type", "课程图片"); break;
//                    case 3: map.put("type", "新闻图片"); break;
//                    default: map.put("type", "error");
//                }
                map.put("type_id", dbPicture.getType_id());
                map.put("create_time", dbPicture.getCreate_time());
                map.put("modify_time", dbPicture.getModify_time());
                mapList.add(map);
            }
            System.out.println("----");

            dataMap.put("data", mapList);

        // 根据图片id获取单个图片对象
        }else if ("GetOnePicture".equals(type)){
            String p_id = httpServletRequest.getParameter("id");
            if (p_id != null && p_id != ""){
                Integer picture_id = Integer.valueOf(p_id);
                Picture picture = pictureMapper.getOnePicture(picture_id);

                dataMap.put("data", picture);
            }

        }
        // 获取图片的类型
        else if ("PictureType".equals(type)){
            List<PictureType> list = pictureMapper.getPictureTypes();

            dataMap.put("data", list);
        }

        return dataMap;
    }

    @RequestMapping("/uploadImg_picture")
    public String uploadImg(@RequestParam("file") MultipartFile file){
        System.out.println("来了");
        // 输出文件的信息
        System.out.println(file.getName()); // form表单file控件的name
        System.out.println(file.getOriginalFilename()); // 源文件名
        System.out.println(file.getSize()); // 文件大小
        System.out.println(file.getContentType()); // 文件类型
        System.out.println(file.getResource().getFilename()); // 文件名
//        try {
//            System.out.println(file.getResource().getURI());
//            System.out.println(file.getResource().getURL());
//            System.out.println(file.getResource().getFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 1、创建一个保存上传文件的目录
        File savepath = new File("F:\\saiJie_img");
        // 如果这个文件夹不存在
        if (!savepath.exists()){
            // 创建多级目录
            savepath.mkdirs();
        }

        // 2、创建一个保存的文件名
        String saveFilename = savepath.getAbsolutePath() + "\\" + file.getOriginalFilename();
        System.out.println(saveFilename);

        // 3、创建一个真正需要保存的文件
        File saveFile = new File(saveFilename);


        try {
            // 4、将上传的文件中的数据保存到磁盘文件中
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "picture_create";
    }
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String Img(HttpServletRequest request){
        File file=new File("F:\\saiJie_img\\"+"1608617693554.png");

        return "";
    }

}
