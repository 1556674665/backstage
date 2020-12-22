package guanwang.s78;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import guanwang.s78.RedisUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFiller implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        ServletContext context = httpServletRequest.getServletContext();
        WebApplicationContext webApplicationContext =  WebApplicationContextUtils.getWebApplicationContext(context);
        RedisUtils redisUtils = (RedisUtils)webApplicationContext.getBean("redisUtils");

        int flag=httpServletRequest.getRequestURI().indexOf("/login");//在请求路径上找login:没找到返回-1
        if (flag!=-1)
        {
            filterChain.doFilter(servletRequest,servletResponse);//可以访问
        }else  if(redisUtils.get("user_name")!=null){
            String constring=httpServletRequest.getHeader("REFERER");//获取父url要是用户输入了，这个父url是不存在的
            if("".equals(constring)||null==constring){
//                httpServletResponse.sendRedirect("index.html");//返回index界面
                httpServletResponse.sendRedirect("login.html");//返回login界面
            }else {
                filterChain.doFilter(servletRequest,servletResponse);//可以访问
            }
        }else {
            httpServletResponse.sendRedirect("login.html");//返回login界面
        }
    }

    @Override
    public void destroy() {

    }
}