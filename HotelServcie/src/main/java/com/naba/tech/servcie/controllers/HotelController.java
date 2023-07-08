package com.naba.tech.servcie.controllers;


import com.naba.tech.servcie.entities.Hotel;
import com.naba.tech.servcie.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotelById(@PathVariable String id){
        return new ResponseEntity<Hotel>(hotelService.getHotelById(id),HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Hotel>> getAllHotels(){
        return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.OK);
    }
}
