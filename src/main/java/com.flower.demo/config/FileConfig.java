package com.flower.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置文件映射
 * @author DGUT-ZZF
 * @create 2021-07-09 11:29
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/photo/**").addResourceLocations(
                "file:"+ System.getProperty("user.dir")+ System.getProperty("file.separator")+
                                "photo"+ System.getProperty("file.separator")
        );

    }

}
