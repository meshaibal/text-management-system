package com.edia.text.management.web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.edia.text.management.persistence.conf.PersistenceLayerConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {WebMVCInitializer.PACKAGE_TEXT_MANAGEMENT_WEB})
@Import(PersistenceLayerConfig.class)
public class WebMVCInitializer extends WebMvcConfigurerAdapter{

	public static final String PACKAGE_TEXT_MANAGEMENT_WEB = "com.edia.text.management.web";
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
