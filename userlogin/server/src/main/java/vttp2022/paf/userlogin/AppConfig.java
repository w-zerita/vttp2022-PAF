package vttp2022.paf.userlogin;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vttp2022.paf.userlogin.filters.AuthenticationFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerFilters() {

        // create an instance of an authentication filter
        AuthenticationFilter authFilter = new AuthenticationFilter();

        // create an instance of an registration filter
        FilterRegistrationBean<AuthenticationFilter> regFilter = 
            new FilterRegistrationBean<>();
        regFilter.setFilter(authFilter);
        regFilter.addUrlPatterns("/protected/*");
        return regFilter;
    }
}
