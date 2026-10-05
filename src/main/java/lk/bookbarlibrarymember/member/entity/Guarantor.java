package lk.bookbarlibrarymember.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity // convert Guarantor class into Entity (persitence entity)
@Table(name = "guarantor") // Specifies the primary table for the annotated entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Guarantor {
    @Id // indicate primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @Column(name = "nic" ,unique = true)
    @Length(min=10, max=12, message = "NIC value length must be 10 or 12")
    @NotNull
    private String nic;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobileno", unique = true)
    @Length(min = 10, max = 10, message = "Mobile Number value must be 10")
    @NotNull
    private String mobileno;

    @NotNull
    private String address;

     private Integer addeduserid ;
    private LocalDate addeddatetime;

    private Integer updateduserid; 
    private LocalDate updateddatetime;

    private Integer deleteduserid; 
    private LocalDate deleteddatetime;

    @ManyToOne
    @JoinColumn(name = "guarantorstatus_id" , referencedColumnName="id")
    private GuarantorStatus guarantorstatus_id;
}
