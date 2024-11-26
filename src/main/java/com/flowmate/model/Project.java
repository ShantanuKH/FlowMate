package com.flowmate.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String name;
    private String description;
    private String category;



    private List<String> tags = new ArrayList<>();



    @JsonIgnore
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)
//    AS we have given mapped by, It will not go to separate project



//    For one project there will be only one chat and for one chat there will be only one project
    private Chat chat;

    @ManyToOne
//Many project can be made by same user
    private User owner;

@OneToMany(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)
//One user can arise many issues
    private List<Issue> issues = new ArrayList<>();

//Many user can work on many project
@ManyToMany
private List<User> team = new ArrayList<>();


}
