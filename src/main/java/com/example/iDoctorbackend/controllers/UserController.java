package com.example.iDoctorbackend.controllers;

import com.example.iDoctorbackend.extensions.ResourceNotFoundException;
import com.example.iDoctorbackend.models.Doctor;
import com.example.iDoctorbackend.models.User;
import com.example.iDoctorbackend.repositories.DoctorRepository;
import com.example.iDoctorbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v2")
public class UserController {


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/doctors/{doctorId}/users")
    public ResponseEntity<List<User>> getAllUsersByDoctorId(@PathVariable Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found with id = " + doctorId);
        }
        List<User> users = userRepository.findUsersByDoctorsId(doctorId);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/doctors/{doctorId}/users")
    public ResponseEntity<User> addUser(@PathVariable Long doctorId, @RequestBody User userRequest) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> {
                    doctor.addUser(userRequest);
                    userRepository.save(userRequest);
                    return ResponseEntity.ok(userRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id = " + doctorId));
    }




}
