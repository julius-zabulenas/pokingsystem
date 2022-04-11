package lt.vtmc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.vtmc.models.Poke;
import lt.vtmc.repositories.PokeRepository;
import lt.vtmc.repositories.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/poke")
public class PokeController {

	private PokeRepository pokeRepository;
	private UserRepository userRepository;

	public PokeController(PokeRepository pokeRepository, UserRepository userRepository) {
		this.pokeRepository = pokeRepository;
		this.userRepository = userRepository;
	}

	@PutMapping
	public ResponseEntity<?> pokeUser(@RequestBody Poke requestPoke) {
		Poke poke = new Poke(requestPoke.getUserTo(), requestPoke.getUserFrom(), requestPoke.getPokeAmount());

		if (pokeRepository.existsByUserToAndUserFrom(requestPoke.getUserTo(), requestPoke.getUserFrom())) {
			Poke pokeFromDb = pokeRepository.findByUserToAndUserFrom(requestPoke.getUserTo(),
					requestPoke.getUserFrom());

			pokeFromDb.setPokeAmount(pokeFromDb.getPokeAmount() + requestPoke.getPokeAmount());
			pokeRepository.save(pokeFromDb);

			return ResponseEntity.ok("done dis");
		}

		pokeRepository.save(poke);
		return ResponseEntity.ok("helo");
	}

	@GetMapping("/search")
	public ResponseEntity<?> showPeople(String keyword) {
		return ResponseEntity.ok().body(userRepository.findByKeyword(keyword));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPeopleToPoke(@PathVariable Long id) {
		return ResponseEntity.ok().body(pokeRepository.findByUserFrom(id));
	}

	@GetMapping("/received/{id}")
	public ResponseEntity<?> getPokes(@PathVariable Long id) {
		return ResponseEntity.ok().body(pokeRepository.findByUserTo(id));
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		return ResponseEntity.ok().body(userRepository.findById(id));
	}

	@GetMapping("/users")
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok().body(userRepository.findAll());
	}
}
