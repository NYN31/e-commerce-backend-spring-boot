package com.ecommercebackend.ecommercebackend.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestFilterConfig {

    @Bean
    public FilterRegistrationBean<BuyerFeatureFilter> registerBuyerFeatureFilter(){
        FilterRegistrationBean<BuyerFeatureFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BuyerFeatureFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
