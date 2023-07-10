package com.naba.tech.service.controllers;


import com.naba.tech.service.entities.User;
import com.naba.tech.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    private int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name ="ratingHotelBreaker", fallbackMethod ="ratingHotelFallBack")
//    @Retry(name ="ratingHotelService", fallbackMethod ="ratingHotelFallBack")
    @RateLimiter(name ="userRateLimiter",fallbackMethod ="ratingHotelFallBack")
    ResponseEntity<User> getUserById(@PathVariable String userId){
        logger.info("Retry count: {}",retryCount);
        retryCount++;
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
        logger.info( "FallBack is executed because servcie is down !!" + ex.getMessage() );
        User user = User.builder()
                .userId( "1234567" )
                .name( "Dump" )
                .email( "dumy1221hb@gmail.com" )
                .about( "This is fallback service !!" )
                .build();

        return new ResponseEntity<>( user, HttpStatus.OK );
    }

    @GetMapping()
    ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
}
