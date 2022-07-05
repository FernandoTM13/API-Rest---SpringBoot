package ProgramaT;

import java.util.List;

public class GetUserImpl implements GetUser{

	private UserService userService;
	
	
	
	public GetUserImpl(UserService userService) {
		this.userService = userService;
	}



	@Override
	public List<User> getAll() {
		return userService.getAllUsers();
	}

}
