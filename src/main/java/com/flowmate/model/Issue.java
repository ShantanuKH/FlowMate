package com.flowmate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Issue {

    @Id  // Use jakarta.persistence.Id here for JPA entities
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generate primary key
    private Long id;


//    Information of the Issue
    private String title;
    private String description;
    private String status;
    private Long projectID;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();

    @ManyToOne


    private User assignee;  // Many issues have the same user as the assignee

//  We gave json ignore so that recursion problem should get arise
    @JsonIgnore
    @ManyToOne
//    Many issue have same project
    private Project project;

    /*List of comments*/


//    By using mapped by we are telling spring boot that don't create separate table just go to the Comment i which the issue field
    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
//    Whenever we will put new comment it will automatically get refreshed here
    private List<Comment> comments= new ArrayList<>();

}
