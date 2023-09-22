package com.example.iDoctorbackend.controllers;

import com.example.iDoctorbackend.extensions.ResourceNotFoundException;
import com.example.iDoctorbackend.models.Doctor;

import com.example.iDoctorbackend.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor _doctor = doctorRepository.save(doctor);
        return new ResponseEntity<>(_doctor, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist with the id: " + id));
        return ResponseEntity.ok(doctor);
    }

    @Async
    @PutMapping("{id}")
    public CompletableFuture<Doctor> updateDoctor(@PathVariable long id, @RequestBody Doctor doctorDetails){
        Doctor updateDoctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " + id));
        updateDoctor.setFirstName(doctorDetails.getFirstName());
        updateDoctor.setSecondName((doctorDetails.getSecondName()));
        updateDoctor.setSpecialization(doctorDetails.getSpecialization());
        updateDoctor.setPrice(doctorDetails.getPrice());
        updateDoctor.setAddress(doctorDetails.getAddress());
        updateDoctor.setClinic_name(doctorDetails.getClinic_name());
        updateDoctor.setExpirienceYearAmount(doctorDetails.getExpirienceYearAmount());
        updateDoctor.setNumber(doctorDetails.getNumber());

        doctorRepository.save(updateDoctor);
        return CompletableFuture.completedFuture(updateDoctor);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exist with the id: " + id));
        doctorRepository.delete(doctor);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll(){
        doctorRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
