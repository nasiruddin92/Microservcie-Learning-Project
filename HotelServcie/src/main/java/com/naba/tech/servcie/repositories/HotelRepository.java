package com.naba.tech.servcie.repositories;

import com.naba.tech.servcie.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
