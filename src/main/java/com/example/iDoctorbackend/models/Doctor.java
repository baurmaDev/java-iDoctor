package com.example.iDoctorbackend.models;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "second_name", nullable = true)
    private String secondName;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "clinic_name")
    private String clinic_name;

    @Column
    private int expirienceYearAmount;

    @Column(name = "price", nullable = true)
    private int price;

    @Column(name = "specialization", nullable = true)
    private String specialization;

    @Column(name = "number", nullable = true)
    private String number;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "registrations",
            joinColumns =  @JoinColumn(name = "doctor_id") ,
            inverseJoinColumns =  @JoinColumn(name = "user_id") )
    private Set<User> users = new HashSet<>();

    public Doctor(Long id, String firstName, String secondName, String password, String address, String clinic_name, int expirienceYearAmount, int price, String specialization, String number) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.address = address;
        this.clinic_name = clinic_name;
        this.expirienceYearAmount = expirienceYearAmount;
        this.price = price;
        this.specialization = specialization;
        this.number = number;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getDoctors().add(this);
    }


}
