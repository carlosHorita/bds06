package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Optional<Movie> obj = movieRepository.findById(dto.getMovieId());
		Movie movieEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		User user = authService.authenticated();
		Review entity = new Review();
		entity.setText(dto.getText().trim());
		entity.setMovie(movieEntity);
		entity.setUser(user);
		entity = reviewRepository.save(entity);
		return new ReviewDTO(entity);

	}

}
