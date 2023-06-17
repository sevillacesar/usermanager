package com.sevilla.usermanager.dao;

import com.sevilla.usermanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from Users usr where usr.email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
