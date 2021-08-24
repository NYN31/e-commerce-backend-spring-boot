package com.ecommercebackend.ecommercebackend.filter;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import java.util.Enumeration;

public class BuyerFeatureFilter implements Filter, javax.servlet.Filter {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(BuyerFeatureFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        LOG.info(
                req.getHeader("token"));


        Enumeration list =  req.getHeaderNames();



        chain.doFilter(request, response);

        LOG.info(
                req.getHeader("token"));
    }

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return false;
    }
}
