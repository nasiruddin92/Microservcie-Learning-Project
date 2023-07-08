package com.naba.tech.servcie.controllers;


import com.naba.tech.servcie.entities.Rating;
import com.naba.tech.servcie.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return new ResponseEntity<Rating>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

    @GetMapping("/{ratingId}")
    ResponseEntity<Rating> getRatingById(@PathVariable String ratingId){
        return new ResponseEntity<Rating>(ratingService.getRatingById(ratingId),HttpStatus.OK);

    }

    @GetMapping
    ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<>(ratingService.getAllRating(),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getRatingByHotelId(hotelId),HttpStatus.OK);
    }
}
