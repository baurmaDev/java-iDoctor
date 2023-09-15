package com.example.iDoctorbackend.repositories;

import com.example.iDoctorbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByDoctorsId(Long doctorId);
}
