package com.naba.tech.servcie.services;

import com.naba.tech.servcie.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    Hotel getHotelById(String id);
    List<Hotel> getAllHotels();
}
