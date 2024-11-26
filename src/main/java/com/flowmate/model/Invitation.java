package com.flowmate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Invitation {

    @Id  // Use jakarta.persistence.Id here for JPA entities
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generate primary key
    private Long id;


//  Using token user can join the Team(project)
    private String token;
    private String email;
    private  Long projectId;
}
