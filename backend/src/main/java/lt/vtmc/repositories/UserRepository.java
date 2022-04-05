package lt.vtmc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lt.vtmc.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM users WHERE first_name LIKE %:keyword%", nativeQuery = true)
	List<User> findByKeyword(@Param("keyword") String keyword);
}
