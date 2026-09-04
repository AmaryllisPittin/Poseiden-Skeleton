package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating findById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating ID: " + id));
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating update(Integer id, Rating rating) {
        rating.setRatingId(id);
        return ratingRepository.save(rating);
    }

    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
