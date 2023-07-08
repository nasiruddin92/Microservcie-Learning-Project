package com.naba.tech.service.external.services;


import com.naba.tech.service.entities.Hotel;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
   Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
