package com.wallet.api.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        logger.info("Adding swagger resource files");

        // TODO add any resource files if any
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        logger.info("Adding interceptors");

        //TODO add interceptors here if any
    }
}
