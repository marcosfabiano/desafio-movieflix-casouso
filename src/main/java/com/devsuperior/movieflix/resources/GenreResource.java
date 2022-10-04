package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.GenreService;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

	@Autowired
	private AuthService authService;
	@Autowired
	private GenreService service;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() {
		Optional<User> user = Optional.ofNullable(authService.authenticated());
		User entity = user.orElseThrow(() -> new UnauthorizedException("Invalid User"));
		List<GenreDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
