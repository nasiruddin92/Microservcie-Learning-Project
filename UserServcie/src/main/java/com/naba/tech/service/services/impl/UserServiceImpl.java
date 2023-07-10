package com.naba.tech.service.services.impl;

import com.naba.tech.service.entities.Hotel;
import com.naba.tech.service.entities.Rating;
import com.naba.tech.service.entities.User;
import com.naba.tech.service.exceptions.ResourceNotFoundException;
import com.naba.tech.service.external.services.HotelService;
import com.naba.tech.service.repositories.UserRepository;
import com.naba.tech.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<User> userArrayList=new ArrayList<>();
        for (User user1:users) {
            User user = userRepository.findById( user1.getUserId()).orElseThrow( () -> new ResourceNotFoundException( "User Not Found On Server Which Id: " + user1.getUserId() ) );
            Rating[] ratingOfObjects = restTemplate.getForObject( "http://RATING-SERVCIE/ratings/user/" + user.getUserId(), Rating[].class );

            ArrayList<Rating> ratings = new ArrayList<>();
            assert ratingOfObjects != null;
            Collections.addAll( ratings, ratingOfObjects );
//            List<Rating> ratings=ratingService.getAllRatingByUserId(user.getUserId());

            List<Rating> ratingList = ratings.stream().map( rating -> {
//                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity( "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class );
//                System.out.println( "Print response code: " + forEntity.getStatusCode() );
                rating.setHotel(hotelService.getHotel(rating.getHotelId()));
                return rating;
            } ).collect( Collectors.toList() );

            user.setRatings( ratingList );
            user.setRatings( ratingList);
            userArrayList.add(user);
        }
        return userArrayList;
    }



    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById( userId ).orElseThrow( () -> new ResourceNotFoundException( "User Not Found On Server Which Id: " + userId ) );
        Rating[] ratingOfObjects = restTemplate.getForObject( "http://RATING-SERVCIE/ratings/user/" + user.getUserId(), Rating[].class);

        ArrayList<Rating> ratings=new ArrayList<>();
        assert ratingOfObjects != null;
        Collections.addAll( ratings, ratingOfObjects );

//        List<Rating> ratings=ratingService.getAllRatingByUserId(user.getUserId());

     List<Rating> ratingList= ratings.stream().map(rating ->{
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity( "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class );
//            Hotel hotel=forEntity.getBody();
//            System.out.println("Print response code: "+forEntity.getStatusCode());
            rating.setHotel(hotelService.getHotel(rating.getHotelId()));
            return rating;
        }).collect( Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void DeleteUserById(User user) {
        userRepository.delete(user);
    }
}
