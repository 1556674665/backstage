package guanwang.s78;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import guanwang.s78.RedisUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Myinterpct implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法前
        ServletContext context = request.getServletContext();
        WebApplicationContext webApplicationContext =  WebApplicationContextUtils.getWebApplicationContext(context);
        RedisUtils redisUtils = (RedisUtils)webApplicationContext.getBean("redisUtils");
        String user=redisUtils.get("user_id");
        if(user!=null || !"".equals(user) || !"null".equals(user)){
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //方法后
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}