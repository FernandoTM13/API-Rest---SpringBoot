package ProgramaT;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {
	
	private String name = "Fernando";
	private String randomValue = "121";
	
	
	@Bean
	public MyBean myBean() {
		return new MyBeanTwoImpl(name, randomValue);
	}

	@Bean
	public DataSource dataSource() {
	        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("org.h2.Driver");
	        dataSourceBuilder.url("jdbc:h2:mem:test");
	        dataSourceBuilder.username("SA");
	        dataSourceBuilder.password("");
	        return dataSourceBuilder.build();
	    }
	
}
