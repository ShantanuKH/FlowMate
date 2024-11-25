package com.flowmate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity

//For Getter Setter we use @Data
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long Id;

    private String fullName;
    private String email;
    private String password;



    @JsonIgnore
//    This means that one user amy have many issues
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue>assignIssues = new ArrayList<>();

//  Whenever we create new [project we increase size and when we delete , WE can decrease the size,
//  By doing so we will be able to track numbers of  project,By that we can tell user whether user is having free plan or not,
//  Or they may need to buy the paid plan, In free plan User can only create upto 3 projects, In paid plan user can create Unlimited project
    private int projectSize;




}
