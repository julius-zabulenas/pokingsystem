package lt.vtmc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import lt.vtmc.models.Poke;
import lt.vtmc.models.User;

@CrossOrigin
@Repository
public interface PokeRepository extends JpaRepository<Poke, Long> {

	boolean existsByUserToAndUserFrom(User userTo, User userFrom);

	Poke findByUserToAndUserFrom(User userTo, User userFrom);

	void deleteByUserToAndUserFrom(Long userTo, Long userFrom);

	List<Poke> findByUserFrom(Long userFrom);

//	@Query(value = "SELECT users.first_name, users.last_name, users.city, users.email, pokes.poke_amount FROM users JOIN pokes ON users.id = pokes.user_id", nativeQuery = true)
	List<Poke> findByUserTo(Long userTo);
}
