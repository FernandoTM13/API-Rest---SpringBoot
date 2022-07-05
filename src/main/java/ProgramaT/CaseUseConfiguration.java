package ProgramaT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

	
	@Bean
	GetUser getUser(UserService userService) {
		return new GetUserImpl(userService);
	}
	
}
