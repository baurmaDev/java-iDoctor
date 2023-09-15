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
//		Doctor doctor = new Doctor();
//		doctor.setFirstName("Bauyrzhan");
//		doctor.setSecondName("Tursynbek");
//		doctor.setAddress("Манаса 34/1");
//		doctor.setClinic_name("Emirmed");
//		doctor.setExpirienceYearAmount(17);
//		doctor.setPrice(8000);
//		doctor.setSpecialization("Терапевт");
//		doctor.setNumber("87086836315");
//		doctorRepository.save(doctor);
//
//		Doctor doctor1 = new Doctor();
//		doctor1.setFirstName("Mamyrov");
//		doctor1.setSecondName("Mamyrbek");
//		doctor1.setAddress("Манаса 34/1");
//		doctor1.setClinic_name("Emirmed");
//		doctor1.setExpirienceYearAmount(17);
//		doctor1.setPrice(8000);
//		doctor1.setSpecialization("Терапевт");
//		doctor1.setNumber("87086836315");
//		doctorRepository.save(doctor1);
	}
}
