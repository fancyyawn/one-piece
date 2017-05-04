package top.zhacker.ms.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.zhacker.ms.auth.jwt.interceptor.BlogLoginInterceptor;

@SpringBootApplication
public class BlogApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new BlogLoginInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        ir.excludePathPatterns("/api/tokens/**");
        // 配置不拦截的路径
//        ir.excludePathPatterns("/**.html");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
