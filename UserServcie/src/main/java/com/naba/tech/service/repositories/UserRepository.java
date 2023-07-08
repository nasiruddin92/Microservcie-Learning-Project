package com.naba.tech.service.repositories;

import com.naba.tech.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
