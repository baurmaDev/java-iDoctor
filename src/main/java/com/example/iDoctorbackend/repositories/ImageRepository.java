package com.example.iDoctorbackend.repositories;

import java.util.Optional;

import com.example.iDoctorbackend.models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByName(String name);

}
