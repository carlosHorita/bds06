package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

		@Autowired
		private GenreService service;
	
		
		@GetMapping
		public ResponseEntity<List<GenreDTO>> findAll(
				@RequestParam(name = "genreId", defaultValue = "0") Long genreId,
				Pageable pageable) {
			List<GenreDTO> page = service.findAll();
			return ResponseEntity.ok().body(page);
		}		
		
				
}