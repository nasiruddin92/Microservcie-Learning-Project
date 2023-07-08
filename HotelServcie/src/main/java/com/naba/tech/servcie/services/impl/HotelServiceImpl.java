package com.naba.tech.servcie.services.impl;

import com.naba.tech.servcie.entities.Hotel;
import com.naba.tech.servcie.exceptions.ResourceNotFoundException;
import com.naba.tech.servcie.repositories.HotelRepository;
import com.naba.tech.servcie.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
       String hotelId= UUID.randomUUID().toString();
       hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotels not found on server which id: "+id) );
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
