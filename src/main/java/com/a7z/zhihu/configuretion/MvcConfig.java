package com.a7z.zhihu.configuretion;


import com.a7z.zhihu.util.UploadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Auther: lq
 * @Date: 2019-12-26 14:51
 * @Description:
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(UploadUtil.getUploadFilePath());
        registry.addResourceHandler(File.separator + "upload" + File.separator + "**").addResourceLocations("file:" + File.separator + UploadUtil.getUploadFilePath() + File.separator + "upload" + File.separator);
    }


}
