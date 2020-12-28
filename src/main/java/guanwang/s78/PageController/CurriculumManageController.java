package guanwang.s78.PageController;

import guanwang.s78.Dao.CurriculumMapper;
import guanwang.s78.User.Curriculum;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CurriculumManageController {

    @Autowired
    CurriculumMapper curriculumMapper;

    @RequestMapping("/CurriculumManage/{operateType}")
    @ResponseBody
    public Map Manage(@PathVariable("operateType") String type, HttpServletRequest httpServletRequest){

        // 返回数据的总集合
        Map dataMap = new HashMap();

        // 查询所有图片或根据条件查询
        if (type.equals("SearchPermission")){

            Curriculum curriculum = new Curriculum();
            curriculum.setCurriculum_name(httpServletRequest.getParameter("curriculum_name"));

            List<Curriculum> dbCurriculums = curriculumMapper.getCurriculums(curriculum);
            System.out.println("---");

            dataMap.put("data", dbCurriculums);
        }
        // 根据id获取单个课程信息
        else if (type.equals("ShowModifyInfo")){
            String c_id = httpServletRequest.getParameter("curriculum_id");
            if (c_id != null && c_id != ""){
                Integer curriculum_id = Integer.valueOf(c_id);
                Curriculum curriculum = curriculumMapper.getOneCurriculums(curriculum_id);

                dataMap.put("data", curriculum);
            }
        }
        // 修改课程信息
        else if (type.equals("Modify")){
            System.out.println("sadfsafs");
            String c_id = httpServletRequest.getParameter("id");
            if (c_id != null && c_id != ""){
                Integer curriculum_id = Integer.valueOf(c_id);
                String curriculum_name = httpServletRequest.getParameter("curriculum_name");
                String course_introduction = httpServletRequest.getParameter("course_introduction");
                Curriculum curriculum = new Curriculum();
                curriculum.setId(curriculum_id);
                curriculum.setCurriculum_name(curriculum_name);
                curriculum.setCourse_introduction(course_introduction);

                int updateState = curriculumMapper.updateCurriculum(curriculum);
                // 判断是否修改成功
                if (updateState >= 1){
                    dataMap.put("data", "200");
                }
            }
        }

        return dataMap;
    }

    @RequestMapping("/uploadImg_curriculum")
    @ResponseBody
    public JSON uploadImg(@RequestParam("file") MultipartFile file){

        Map resultMap = new HashMap();
        List<String> list = new ArrayList<String>();
        String imgUrl;
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

        String ps = "F:/saiJie_img";
        // 1、创建一个保存上传文件的目录
        File savepath = new File(ps);
        // 如果这个文件夹不存在
        if (!savepath.exists()){
            // 创建多级目录
            savepath.mkdirs();
        }

        // 2、创建一个保存的文件名
        String[] strArr = file.getOriginalFilename().split("/");
        String fileName = strArr[strArr.length - 1];
        String saveFilename = ps + "/" + fileName;
        // 用当前时间戳做文件名
//        String saveFilename = savepath.getAbsolutePath() + "\\" + String.valueOf(new Date().getTime());
        System.out.println(saveFilename);

        // 3、创建一个真正需要保存的文件
        File saveFile = new File(saveFilename);
        if (!saveFile.exists()){
            // 创建多级目录
            try {
                saveFile.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try {

            // 4、将上传的文件中的数据保存到磁盘文件中
            file.transferTo(saveFile);

            imgUrl = "http://192.168.31.117:8000/" + fileName;
            System.out.println("filename"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("errno", e);
            JSON map= JSONArray.fromObject(resultMap);
            return map;
        }
        resultMap.put("errno", "0");
        list.add(imgUrl);
        resultMap.put("data", list);
        System.out.println("imgurl:"+imgUrl);
        JSON map= JSONArray.fromObject(resultMap);
        return map;
    }

    @RequestMapping("/uploadImg_curriculum1")
    @ResponseBody
    public JSON uploadImg1(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        System.out.println(multipartHttpServletRequest.getFileMap());
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Map resultMap = new HashMap(); // 总结果集
        List<String> list = new ArrayList<String>(); // 结果集中的data列表
        // 循环多个文件
        fileMap.forEach((k, v) -> {
            MultipartFile file = fileMap.get(k); // 文件

            String imgUrl = ""; // 图片的路径

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

            String ps = "F:/saiJie_img";
            // 1、创建一个保存上传文件的目录
            File savepath = new File(ps);
            // 如果这个文件夹不存在
            if (!savepath.exists()){
                // 创建多级目录
                savepath.mkdirs();
            }

            // 2、创建一个保存的文件名
            String[] strArr = file.getOriginalFilename().split("/");
            String fileName = strArr[strArr.length - 1];
            String saveFilename = ps + "/" + fileName;
            // 用当前时间戳做文件名
//        String saveFilename = savepath.getAbsolutePath() + "\\" + String.valueOf(new Date().getTime());
            System.out.println(saveFilename);

            // 3、创建一个真正需要保存的文件
            File saveFile = new File(saveFilename);
            if (!saveFile.exists()){
                // 创建多级目录
                try {
                    saveFile.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            try {

                // 4、将上传的文件中的数据保存到磁盘文件中
                file.transferTo(saveFile);
                // 保存成功就返回图片路径
                imgUrl = "http://192.168.31.117:8000/" + fileName;
                System.out.println("filename"+fileName);
            } catch (IOException e) {
                e.printStackTrace();
                resultMap.put("errno", e);
            }
            list.add(imgUrl);
        });

        resultMap.put("errno", "0");
        resultMap.put("data", list);
//        System.out.println("imgurl:"+imgUrl);
        JSON map= JSONArray.fromObject(resultMap);
        return map;
    }

    @RequestMapping("/aoligei")
    public void aoligei(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getParameter("AAA"));
    }

}
