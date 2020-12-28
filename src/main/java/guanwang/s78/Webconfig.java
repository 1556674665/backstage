package guanwang.s78;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer{
//    @Bean
//    public FilterRegistrationBean register()
//    {
//        FilterRegistrationBean  registrationBean=new FilterRegistrationBean();
//        registrationBean.setFilter(new MyFiller());//添加过滤器
//        registrationBean.addUrlPatterns("/*");//拦截所有网页
//        return registrationBean;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Myinterpct()).addPathPatterns("ow");//添加拦截器,拦截show
//
//    }
}