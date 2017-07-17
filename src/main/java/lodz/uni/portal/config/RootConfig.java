package lodz.uni.portal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "lodz.uni.portal", 
		"lodz.uni.portal.logic.utils" })
public class RootConfig {

}
