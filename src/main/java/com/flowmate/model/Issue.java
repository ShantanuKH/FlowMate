package com.flowmate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Issue {

    @Id  // Use jakarta.persistence.Id here for JPA entities
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generate primary key
    private Long id;  // Changed the field name to lowercase "id" to follow Java naming conventions

    @ManyToOne
    private User assignee;  // Many issues have the same user as the assignee

}
