package guanwang.s78.PageController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import guanwang.s78.RedisUtils;
import guanwang.s78.Dao.UserMapper;
import guanwang.s78.pojo.Administrators_table;
import guanwang.s78.pojo.College_News_table;
import guanwang.s78.pojo.Enrollment_and_employment_table;
import guanwang.s78.pojo.Master_workstation_table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    UserMapper usermapper;

    @Resource
    private RedisUtils redisUtils;

    //登录
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        int zt = 0;
        for (Administrators_table user : usermapper.selectusername()) {
            if (Integer.toString(user.getUser_name()).equals(request.getParameter("username")) && user.getPassword().equals(request.getParameter("password"))) {
                zt = 1;
            }
        }
        if (zt == 1){
            redisUtils.set("user_name", request.getParameter("username"), 1440 * 60);
            model.addAttribute("name", redisUtils.get("user_name"));
            return "index";
        }else{
            return "login";
        }
    }

    //查看密码
    @RequestMapping("/selectpassword")
    public String selectpassword(Model model){
        model.addAttribute("password_",usermapper.selectpassword(redisUtils.get("user_name")));
        return "updatepassword";
    }

    //修改密码
    @RequestMapping("/updatepassword")
    public String updatepassword(HttpServletRequest request){
        usermapper.updatepassword(request.getParameter("password2"),redisUtils.get("user_name"));
        return "login";
    }

    //退出
    @RequestMapping("/tuichu")
    public String tuichu() {
        redisUtils.delete("user_name");
        return "login";
    }

    //查询管理员
    @RequestMapping("/selectadministrators")
    @ResponseBody
    public List<Administrators_table> selectadministrators(HttpServletRequest request){
        String user_name =request.getParameter("user");
        List<Administrators_table> admin = usermapper.selectAdministrators(user_name);
        return admin;
    }

    //删除管理员
    @RequestMapping("/deladministrators")
    @ResponseBody
    public JSON deladministrators(HttpServletRequest request){
        int a = usermapper.deladministrators(request.getParameter("id"));
        Map map = new HashMap();
        map.put("data", a);
        JSON b = new JSONObject(map);
        return b;
    }

    //添加管理员
    @RequestMapping("/insertadministrators")
    public String insertadministrators(HttpServletRequest request){
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.insertadministrators(dateString,dateString,request.getParameter("user_name"),request.getParameter("name"),request.getParameter("password"),request.getParameter("gender"));
        return "Administrators_table";
    }

    //展示管理员
    @RequestMapping("/selectAdministrators_s")
    public String selectAdministrators_s(HttpServletRequest request,Model model){
        model.addAttribute("administrators",usermapper.selectAdministrators_s(request.getParameter("id")));
        return "updateadministrators";
    }

    //修改管理员
    @RequestMapping("/updateadministrators")
    public String updateadministrators(HttpServletRequest request)  {
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.updateadministrators(request.getParameter("name"),request.getParameter("gender"),dateString,request.getParameter("id"));
        return "Administrators_table";
    }

    //查询学院新闻
    @RequestMapping("/selectCollege_News_table")
    @ResponseBody
    public List<College_News_table> selectCollege_News_table(HttpServletRequest request){
        String title =request.getParameter("user");
        List<College_News_table> admin = usermapper.selectCollege_News_table(title);
        return admin;
    }

    //删除学院新闻
    @RequestMapping("/delCollege_News_table")
    @ResponseBody
    public JSON delCollege_News_table(HttpServletRequest request){
        int a = usermapper.delCollege_News_table(request.getParameter("id"));
        Map map = new HashMap();
        map.put("data", a);
        JSON b = new JSONObject(map);
        return b;
    }

    //添加学校新闻
    @RequestMapping("/insertCollege_News_table")
    public ModelAndView insertCollege_News_table(HttpServletRequest request){
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.insertCollege_News_table(dateString,dateString,request.getParameter("title"),request.getParameter("source"),request.getParameter("content"));
        return new ModelAndView("College_News_table");
    }

    //展示学校新闻
    @RequestMapping("/selectCollege_News_table_s")
    public String selectCollege_News_table_s(HttpServletRequest request,Model model){
        model.addAttribute("College_News",usermapper.selectCollege_News_table_s(request.getParameter("id")));
        return "updateCollege_News_table";
    }

    //修改学校新闻
    @RequestMapping("/updateCollege_News_table")
    public String updateCollege_News_table(HttpServletRequest request)  {
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.updateCollege_News_table(request.getParameter("title"),request.getParameter("source"),request.getParameter("content"),dateString,request.getParameter("id"));
        return "College_News_table";
    }

    //查询招生就业
    @RequestMapping("/selectEnrollment_and_employment_table")
    @ResponseBody
    public List<Enrollment_and_employment_table> selectEnrollment_and_employment_table(HttpServletRequest request){
        String title =request.getParameter("user");
        List<Enrollment_and_employment_table> admin = usermapper.selectEnrollment_and_employment_table(title);
        return admin;
    }

    //删除招生就业
    @RequestMapping("/delEnrollment_and_employment_table")
    @ResponseBody
    public JSON delEnrollment_and_employment_table(HttpServletRequest request){
        int a = usermapper.delEnrollment_and_employment_table(request.getParameter("id"));
        Map map = new HashMap();
        map.put("data", a);
        JSON b = new JSONObject(map);
        return b;
    }

    //展示添加招生就业
    @RequestMapping("/insertEnrollment_and_employment_table1")
    public ModelAndView a(){
        return new ModelAndView("insertEnrollment_and_employment_table");
    }

    //添加招生就业
    @RequestMapping("/insertEnrollment_and_employment_table")
    public ModelAndView insertEnrollment_and_employment_table(HttpServletRequest request){
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.insertEnrollment_and_employment_table(dateString,dateString,request.getParameter("title"),request.getParameter("source"),request.getParameter("content"));
        return new ModelAndView("Enrollment_and_employment_table");
    }

    //展示招生就业
    @RequestMapping("/selectEnrollment_and_employment_table_s")
    public String selectEnrollment_and_employment_table_s(HttpServletRequest request,Model model){
        model.addAttribute("College_News",usermapper.selectEnrollment_and_employment_table_s(request.getParameter("id")));
        return "updateEnrollment_and_employment_table";
    }

    //修改招生就业
    @RequestMapping("/updateEnrollment_and_employment_table")
    public String updateEnrollment_and_employment_table(HttpServletRequest request)  {
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.updateEnrollment_and_employment_table(request.getParameter("title"),request.getParameter("source"),request.getParameter("content"),dateString,request.getParameter("id"));
        return "Enrollment_and_employment_table";
    }

    //查询硕士工作站
    @RequestMapping("/selectMaster_workstation_table")
    @ResponseBody
    public List<Master_workstation_table> selectMaster_workstation_table(HttpServletRequest request){
        String title =request.getParameter("user");
        List<Master_workstation_table> admin = usermapper.selectMaster_workstation_table(title);
        return admin;
    }

    //删除硕士工作站
    @RequestMapping("/delMaster_workstation_table")
    @ResponseBody
    public JSON delMaster_workstation_table(HttpServletRequest request){
        int a = usermapper.delMaster_workstation_table(request.getParameter("id"));
        Map map = new HashMap();
        map.put("data", a);
        JSON b = new JSONObject(map);
        return b;
    }

    //添加硕士工作站
    @RequestMapping("/insertMaster_workstation_table")
    public ModelAndView insertMaster_workstation_table(HttpServletRequest request){
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.insertMaster_workstation_table(dateString,dateString,request.getParameter("title"),request.getParameter("source"),request.getParameter("content"));
        return new ModelAndView("Master_workstation_table");
    }

    //展示硕士工作站
    @RequestMapping("/selectMaster_workstation_table_s")
    public String selectMaster_workstation_table_s(HttpServletRequest request,Model model){
        model.addAttribute("College_News",usermapper.selectMaster_workstation_table_s(request.getParameter("id")));
        return "updateMaster_workstation_table";
    }

    //修改硕士工作站
    @RequestMapping("/updateMaster_workstation_table")
    public String updateMaster_workstation_table(HttpServletRequest request)  {
        Date dateTime=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(dateTime);
        usermapper.updateMaster_workstation_table(request.getParameter("title"),request.getParameter("source"),request.getParameter("content"),dateString,request.getParameter("id"));
        return "Master_workstation_table";
    }
}