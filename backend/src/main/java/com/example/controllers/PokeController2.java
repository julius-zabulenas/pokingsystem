package com.example.controllers;

import com.example.models.Poke;
import com.example.repositories.PokeRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
public class PokeController2 {

	private PokeRepository pokeRepository;

	public PokeController2(PokeRepository pokeRepository) {
		this.pokeRepository = pokeRepository;
	}

	@PutMapping("/pokes")
	public ResponseEntity<?> pokeUser(@RequestBody Poke requestPoke) {
		Poke poke = new Poke(requestPoke.getUserTo(), requestPoke.getUserFrom(), requestPoke.getPokeAmount());

		if (pokeRepository.existsByUserToAndUserFrom(requestPoke.getUserTo(), requestPoke.getUserFrom())) {
			Poke pokeFromDb = pokeRepository.findByUserToAndUserFrom(requestPoke.getUserTo(),
					requestPoke.getUserFrom());

			pokeFromDb.setPokeAmount(pokeFromDb.getPokeAmount() + requestPoke.getPokeAmount());
			pokeRepository.save(pokeFromDb);

			return ResponseEntity.ok("Poke count updated");
		}

		pokeRepository.save(poke);
		return ResponseEntity.ok("First poke created");
	}
}
