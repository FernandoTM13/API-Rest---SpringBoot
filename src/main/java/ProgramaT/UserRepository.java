package ProgramaT;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from User u where u.email=?1")
	Optional<User> findUserByEmail(String email);	
	
	@Query("Select u from User u where u.name like ?1%")
	List<User> findAndSort(String name, Sort sort);
	
	List<User> findByName(String name);
	
	Optional<User> findByEmailAndName(String email, String name);

	List<User> findByEmailLike(String email);
	
	List<User> findBybirthDateBetween(LocalDate begin, LocalDate end);
	
	List<User> findByNameLikeOrderByIdDesc(String name);
	
	
	@Query("Select new ProgramaT.UserDTO(u.id, u.name, u.birthDate) from User u where u.birthDate =:parametroFecha and u.email =:parametroEmail")
	Optional<UserDTO> getAllBybirthDateAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email);
	
	
	
}
