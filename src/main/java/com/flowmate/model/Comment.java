package com.flowmate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Comment {

    @Id  // Use jakarta.persistence.Id here for JPA entities
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generate primary key
    private Long id;

    private String content;
    private LocalDateTime createdDateTime;


    @ManyToOne
    private User user;

    @ManyToOne
//    Many comment have same issue
    private Issue issue;
}
