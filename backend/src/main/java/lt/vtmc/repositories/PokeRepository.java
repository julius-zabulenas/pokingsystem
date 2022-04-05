package lt.vtmc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.vtmc.models.Poke;

public interface PokeRepository extends JpaRepository<Poke, Long> {

	boolean existsByUserIdAndUserFromId(Long userId, Long userFromId);

	Poke findByUserIdAndUserFromId(Long userId, Long userFromId);

	void deleteByUserIdAndUserFromId(Long userId, Long userFromId);

	List<Poke> findByUserFromId(Long userFromId);

//	@Query(value = "SELECT users.first_name, users.last_name, users.city, users.email, pokes.poke_amount FROM users JOIN pokes ON users.id = pokes.user_id", nativeQuery = true)
	List<Poke> findByUserId(Long userId);
}
