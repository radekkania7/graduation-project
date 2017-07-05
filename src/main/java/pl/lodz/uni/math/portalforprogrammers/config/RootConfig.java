package pl.lodz.uni.math.portalforprogrammers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "pl.lodz.uni.math.portalforprogrammers", 
		"lodz.uni.portal.logic.utils" })
public class RootConfig {

}
