package com.flowmate.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
//import org.aspectj.bridge.Message;
//import com.flowmate.model.Message;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;



    @OneToOne
    private Project project;


//   One chat will be having many messages
    @JsonIgnore

//Pehele @OneToOne tha
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;


    @ManyToMany
    private List<User> user = new ArrayList<>();

}
