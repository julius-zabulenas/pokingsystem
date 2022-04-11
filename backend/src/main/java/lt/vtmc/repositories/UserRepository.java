package lt.vtmc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import lt.vtmc.models.User;

@RepositoryRestResource(excerptProjection = InlineUser.class)
@CrossOrigin
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
	List<User> findByFirstNameStartsWith(@Param("name") String name);

	@Query(value = "SELECT * FROM users WHERE first_name LIKE %:keyword%", nativeQuery = true)
	List<User> findByKeyword(@Param("keyword") String keyword);
}
