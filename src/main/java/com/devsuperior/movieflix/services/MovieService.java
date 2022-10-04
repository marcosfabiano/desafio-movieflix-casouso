package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
	@Autowired
	private MovieRepository repository;

	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(movie);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		Page<Movie> page = repository.findByGenre(genreId, pageable);
		return page.map(x -> new MovieDTO(x));
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findReview(Long id) {
		List<Review> list = repository.findReview(id);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
}
