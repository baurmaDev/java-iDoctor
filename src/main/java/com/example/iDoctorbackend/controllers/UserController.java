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
    public ResponseEntity<List<User>> getAllUsersByDoctorId(@PathVariable(value = "doctorId") Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + doctorId);
        }
        List<User> users = userRepository.findUsersByDoctorsId(doctorId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctorsByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + userId);
        }
        List<Doctor> tutorials = doctorRepository.findDoctorsByUsersId(userId);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
    @PostMapping("/doctors/{doctorId}/users")
    public ResponseEntity<User> addUser(@PathVariable(value = "doctorId") Long doctorId, @RequestBody User userRequest) {
//        User user = doctorRepository.findById(doctorId).map(doctor -> {
//            long userId = userRequest.getId();
//
//            // tag is existed
//            if (userId != 0L) {
//                User _user = userRepository.findById(userId)
//                        .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
//                doctor.addUser(_user);
//                doctorRepository.save(doctor);
//                return _user;
//            }
//
//            // add and create new Tag
//            doctor.addUser(userRequest);
//            return userRepository.save(userRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Doctor with id = " + doctorId));
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        doctor.map(d -> {
            d.addUser(userRequest);
            return userRepository.save(userRequest);
        });
        return new ResponseEntity<>(userRequest, HttpStatus.OK);


    }



//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

//    @PostMapping("/users")
//    public User setUser(@RequestBody User user){
//
//        return userRepository.save(user);
//    }
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " +  id));
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails){
//        User updateUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " + id));
//        updateUser.setFirstName(userDetails.getFirstName());
//        updateUser.setSecondName((userDetails.getSecondName()));
//        updateUser.setNumber(userDetails.getNumber());
//        userRepository.save(updateUser);
//        return ResponseEntity.ok(updateUser);
//    }
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with the id: " + id));
//        userRepository.delete(user);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    @DeleteMapping("/users")
//    public ResponseEntity<HttpStatus> deleteAll(){
//        userRepository.deleteAll();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
