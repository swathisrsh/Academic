package com.example.Student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
    private String name;
    private String phone;
    private String address;
}
