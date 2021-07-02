package com.hassan.main.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.hassan.main.core"})
public class WebConfig implements WebMvcConfigurer {

    /* commented out due to not needing any other property beside Messages
    @Autowired
    @Qualifier("appProperties")
    protected Properties properties;

    @Bean(name = "appProperties")
    public PropertiesFactoryBean environmentProperties(){
        PropertiesFactoryBean propertiesFactoryBean =  new PropertiesFactoryBean();
        propertiesFactoryBean.setLocations(
                new ClassPathResource("config/env.properties")
        );
        return propertiesFactoryBean;
    }*/

    @Bean(name = "messageSource")
    public MessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource messageSource =  new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:lang/prj/project",
                "classpath:lang/tsk/taskInformation",
                "classpath:lang/common"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
