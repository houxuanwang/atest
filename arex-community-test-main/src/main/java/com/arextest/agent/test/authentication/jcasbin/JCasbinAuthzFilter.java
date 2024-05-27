package com.arextest.agent.test.authentication.jcasbin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "JCasbinAuthzFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class JCasbinAuthzFilter implements Filter {

    static {

        try {
            File configFile = new File("./model1.conf");
            InputStream in = JCasbinAuthzFilter.class.getClassLoader().getResourceAsStream("model.conf");
            FileUtils.copyInputStreamToFile(Objects.requireNonNull(in, "config.xml文件找不到"), configFile);
            File configFile1 = new File("./policy1.csv");
            InputStream in1 = JCasbinAuthzFilter.class.getClassLoader().getResourceAsStream("policy.csv");
            FileUtils.copyInputStreamToFile(Objects.requireNonNull(in1, "config.xml文件找不到"), configFile1);
        } catch (IOException e) {
            log.error("copy exception", e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String user = Optional.ofNullable(request.getParameter("username")).orElse("");
        String path = Optional.ofNullable(request.getRequestURI()).orElse("");
        String method = Optional.ofNullable(request.getMethod()).orElse("");

        log.info(String.format("user = %s, path = %s, method = %s", user, path, method));

        String resPath = "./";
        Enforcer enforcer = new Enforcer(resPath + "model1.conf", resPath + "policy1.csv");
        boolean pass = enforcer.enforce(user, path, method);
        log.info(String.format("pass = %s", pass));

        if (path.contains("anon") || !path.contains("JCasbin")) {
            log.info("匿名访问");
            chain.doFilter(request, response);
        } else if (pass) {
            log.info("允许访问");
            chain.doFilter(request, response);
        } else {
            log.info("无权访问");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write("{\"msg\":\"无权访问\"}");
        }
    }

    @Override
    public void destroy() {
    }

}