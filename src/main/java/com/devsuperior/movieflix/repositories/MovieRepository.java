package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT obj FROM Movie obj WHERE "
            + ":genreId = 0L OR obj.genre.id = :genreId "
            + "ORDER BY obj.title ASC")
    Page<Movie> findByGenre(Long genreId, Pageable pageable);
    @Query("SELECT obj FROM Review obj WHERE "
            + "obj.user.id = :id " )
    List<Review> findReview(Long id);
}




