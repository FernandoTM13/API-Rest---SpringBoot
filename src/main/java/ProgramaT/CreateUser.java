package ProgramaT;

import org.springframework.stereotype.Component;

@Component
public class CreateUser {

	UserService userService;
	
	public CreateUser(UserService userService) {
		this.userService = userService;
	}

	public User save(User newUser) {
		return userService.save(newUser);
	}
		
}
