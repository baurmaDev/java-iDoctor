package com.example.iDoctorbackend;

import com.example.iDoctorbackend.models.Doctor;
import com.example.iDoctorbackend.models.User;
import com.example.iDoctorbackend.repositories.DoctorRepository;
import com.example.iDoctorbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IDoctorBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IDoctorBackendApplication.class, args);
	}

	@Autowired
	private DoctorRepository doctorRepository;


	@Override
	public void run(String... args) throws Exception {

	}
}
