package com.flowmate.repository;

import com.flowmate.model.Project;
import com.flowmate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>{


//    List of project created by user
//    List<Project>findByOwner(User user);


//    For searching the project
    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

//
//    @Query("SELECT p From Project p join p.team where t=:user")
//    List<Project>findProjectByTeam(@Param("user") User user);


    List<Project>findByTeamContainingOrOwner(User user, User owner);
}
