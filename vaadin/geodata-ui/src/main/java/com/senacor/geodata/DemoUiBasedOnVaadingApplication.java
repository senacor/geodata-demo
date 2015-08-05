package com.senacor.geodata;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.JamonPerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableHystrix
public class DemoUiBasedOnVaadingApplication {

    @org.springframework.context.annotation.Configuration
    public static class Configuration {
        @Bean
        public CommonsRequestLoggingFilter requestLoggingFilter() {
            CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
            crlf.setIncludeClientInfo(true);
            crlf.setIncludeQueryString(true);
            return crlf;
        }

        @Bean
        public JamonPerformanceMonitorInterceptor jamonPerformanceMonitorInterceptor() {
            return new JamonPerformanceMonitorInterceptor();
        }

        @Bean
        public Advisor performanceAdvisor() {
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            // apply aspect to views, only
            pointcut.setExpression("execution(public * com.senacor.geodata.views.*.*(..))");
            return new DefaultPointcutAdvisor(pointcut, jamonPerformanceMonitorInterceptor());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoUiBasedOnVaadingApplication.class, args);
    }
}
