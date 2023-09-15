package com.example.iDoctorbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "second_name", nullable = true)
    private String secondName;

    @Column(name = "number", nullable = true)
    private String number;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "users")
    @JsonIgnore
    private Set<Doctor> doctors = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "users")
//    @JsonIgnore
//    private Set<Doctor> doctors = new HashSet<>();

}