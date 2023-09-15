package com.example.iDoctorbackend.repositories;

import com.example.iDoctorbackend.models.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findDoctorsByUsersId(Long userId);
}
