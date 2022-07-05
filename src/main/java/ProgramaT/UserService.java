package ProgramaT;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	private final Log logger = LogFactory.getLog(UserService.class);
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/*public void saveTrancational(List<User> listUser){
		listUser.stream().peek(user -> logger.info("Usuario ingresado con transaccion: " + user)).forEach(
				userRepository::save);*/
	
	@Transactional
	public void saveTransactional(List<User> listUsers) {
			 listUsers.stream().peek(user -> logger.info("El usuario ingresado con transacción" + user)).forEach(
					 user -> userRepository.save(user));
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User save(User newUser) {
		return userRepository.save(newUser);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	
	public User update(User newUser, Long id) {
		return userRepository.findById(id).map(
				user -> {
					user.setEmail(newUser.getEmail());
					user.setBirthDate(newUser.getBirthDate());
					user.setName(newUser.getName());
					return userRepository.save(user);
				}
				).get();
	}
	
	
}
