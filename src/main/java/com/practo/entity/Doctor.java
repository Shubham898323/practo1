package com.practo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "experience")
    private int experience;

    @Column(name = "ratings")
    private double ratings;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "description", length = 1000)
    private String description;
}

