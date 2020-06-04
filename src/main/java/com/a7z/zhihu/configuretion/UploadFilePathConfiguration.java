package com.a7z.zhihu.configuretion;


import com.a7z.zhihu.util.UploadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 设置上传的路径可以被访问
 * @author lq
 * @create 2020/3/14-13:54
 */
@Configuration
public class UploadFilePathConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/"+ UploadUtil.getUploadFilePath()+"/upload/");
//        registry.addResourceHandler(File.separatorChar+"upload"+File.separator+"**").addResourceLocations("file:"+File.separator+ UploadUtil.getUploadFilePath()+File.separator+"upload"+File.separator);


    }


    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }



}
