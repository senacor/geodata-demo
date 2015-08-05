package com.senacor.geodata;

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
//            crlf.setIncludePayload(true);
            return crlf;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoUiBasedOnVaadingApplication.class, args);
    }
}
