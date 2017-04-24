package pl.lodz.uni.math.portalforprogrammers.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import pl.lodz.uni.math.portalforprogrammers.service.converter.SportNameToSportObjectConverter;
import pl.lodz.uni.math.portalforprogrammers.service.converter.TownNameToTownObjectConverter;

@Configuration
@EnableWebMvc
@ComponentScan( {"pl.lodz.uni.math.portalforprogrammers.web", 
	"pl.lodz.uni.math.portalforprogrammers.service.converter"} )
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired 
	SportNameToSportObjectConverter sportNameToSportObjectConverter;
	
	@Autowired
	TownNameToTownObjectConverter townNameToTownObjectConverter;
	
	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}
			 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public TilesConfigurer TilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] {
				"/WEB-INF/layout/tiles.xml"
		});
		tiles.setCheckRefresh(true);
		return tiles;
	}
	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
	
	//czemu nie dziala z tak zdefiniowanym beanem ?.
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(40240);
		resolver.setResolveLazily(true);
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
	
	/*
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(sportNameToSportObjectConverter);
		registry.addConverter(townNameToTownObjectConverter);
	}
}
