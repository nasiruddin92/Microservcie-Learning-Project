package com.naba.tech.servcie.services;

import com.naba.tech.servcie.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    Rating getRatingById(String ratingId);

    List<Rating> getAllRating();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
