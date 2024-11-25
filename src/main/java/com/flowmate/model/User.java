package com.flowmate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

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

//  Whenever we create new [project we increase size and when we delete , WE can decrease the size,
//  By doing so we will be able to track numbers of  project,By that we can tell user whether user is having free plan or not,
//  Or they may need to buy the paid plan, In free plan User can only create upto 3 projects, In paid plan user can create Unlimited project
    private int projectSize;


}
