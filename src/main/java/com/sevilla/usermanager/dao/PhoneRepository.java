package com.sevilla.usermanager.dao;

import com.sevilla.usermanager.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, String> {
}
