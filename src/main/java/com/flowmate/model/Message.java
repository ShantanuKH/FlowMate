package com.flowmate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Message {

    @Id  // Use jakarta.persistence.Id here for JPA entities
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generate primary key
    private Long id;

    private String content;
    private LocalDateTime createdAt;

//   Pehele @ManyToAny tha
    @ManyToOne
    private Chat chat;

//    One user can send many messages
    @ManyToOne
    private User sender;

}
