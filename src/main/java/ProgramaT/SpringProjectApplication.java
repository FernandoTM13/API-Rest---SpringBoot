package ProgramaT;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;


@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner{
	
	private final Log logger = LogFactory.getLog(this.getClass());
	private MyBean myBean;
	private MyComponent myComponent;
	private UserRepository userRepository;
	private UserService userService;
	
	
	@Autowired
	UserRestController userRestController;
	
	public SpringProjectApplication(MyBean myBean, @Qualifier("Cualquiernombre") MyComponent myComponent, UserRepository userRepository,
			UserService userService){
		this.myBean = myBean;
		this.myComponent = myComponent;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(myBean.hello());
		myComponent.printSomething();
		SaveData();
		getInformationJPLUser();
		saveWithErrorTransactional();
		System.out.println("Apartado de prueba");
		pruebaLista();
	}

	private void getInformationJPLUser() {
		logger.info("Se encontro el siguiente usuario:"+ userRepository.findUserByEmail("user2@gmail.com")
		.orElseThrow(()-> new RuntimeException("No se encontro al usuario") ));
		
		userRepository.findAndSort("user", Sort.by("id").ascending()).stream().forEach(user -> logger.info("Persona" + user));
		
		userRepository.findByName("user2").stream().forEach(user -> logger.info("Persona Encontrada" + user));
		
		logger.info("La persona encontrada de FindByEmailAndAName es: " + 
				userRepository.findByEmailAndName("fernando@gmail.com", "Fernando Villegas").orElseThrow(
						()-> new RuntimeException("No se encontro a esta persona")));
		
		userRepository.findByEmailLike("%gmail.com").stream().forEach( user -> logger.info("Persona findByEmailLike" + user));
		
		userRepository.findBybirthDateBetween(LocalDate.of(2000, 1, 1), LocalDate.of(2004, 9, 10)).stream().forEach(
				user-> logger.info("Persona encontrada con findBybirthDateBetween" + user));
		
		
		userRepository.findByNameLikeOrderByIdDesc("user%").stream().forEach(user-> logger.info("Persona findByNameLikeOrderById " + user));
		
		
		logger.info("El getAllbybirth: " + userRepository.getAllBybirthDateAndEmail( LocalDate.of(2001, 06, 16),"fernando@gmail.com").orElseThrow(
				()-> new RuntimeException("No se pudo encontrar getALLbyBithDate")));
		
	}
	
	
	public void SaveData() {
		User user1 = new User("Fernando Villegas", "fernando@gmail.com", LocalDate.of(2001, 06, 16) );
		User user2 = new User("user2", "user2@gmail.com", LocalDate.of(2001, 06, 16) );
		User user3 = new User("user3", "user3@gmail.com", LocalDate.of(2003, 06, 13) );
		User user4 = new User("user4", "user4@gmail.com", LocalDate.of(2004, 06, 14) );
		User user5 = new User("user5", "user5@gmail.com", LocalDate.of(2005, 06, 15) );
		User user6 = new User("user6s", "user6@gmail.com", LocalDate.of(2006, 06, 16) );
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6);
		list.stream().forEach(userRepository::save);
		
	}
	
	
	public void saveWithErrorTransactional() {
		
		User test1 = new User("TestT1", "test1@gmail.com", LocalDate.of(2010, 10, 11));
		User test2 = new User("TestT2", "test2@gmail.com", LocalDate.of(2010, 10, 12));
		User test3 = new User("TestT3", "test3@gmail.com", LocalDate.of(2010, 10, 13));
		User test4 = new User("TestT4", "test4@gmail.com", LocalDate.of(2010, 10, 14));

		List<User> listt = Arrays.asList(test1, test2, test3, test4);
		
		try {
			userService.saveTransactional(listt);
		} catch (Exception e) {
			userService.getAllUsers().stream().forEach(user -> logger.info("Personas ingresadas transaccional:   " + user ));
		} 
		
		
	}
	
	
	public void pruebaLista() {
		userRestController.get().stream().forEach(user -> logger.info("Esta es una persona PRUEBA: " + user));
	}
	
	
	
	
	
}
