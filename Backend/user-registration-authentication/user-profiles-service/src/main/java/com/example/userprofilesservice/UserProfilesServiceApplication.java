package com.example.userprofilesservice;

import com.example.userprofilesservice.filter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class UserProfilesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfilesServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getfilter() {
		UrlBasedCorsConfigurationSource urlconfig = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config=new CorsConfiguration();

		config.setAllowCredentials(true);
		List<String> all = Arrays.asList("*");

		config.setAllowedOrigins(all);
		config.setAllowedMethods(all);
		config.setAllowedHeaders(all);

		urlconfig.registerCorsConfiguration("/**", config);

		FilterRegistrationBean fbean = new FilterRegistrationBean(new CorsFilter(urlconfig));
		fbean.setFilter(new Filter());
		fbean.addUrlPatterns("/api/v1/users/*");

		return fbean;
	}


}
