package com.flowmate.service;

import com.flowmate.model.Chat;
import com.flowmate.model.Project;
import com.flowmate.model.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, User user)throws Exception;

    List<Project> getProjectByTeam(User user , String categoryu, String tag)throws Exception;


    Project getProjectById(Long projectId)throws Exception;



//    The project owner and the requesting user for deleting the project should be same , so we gave userId o in the parameter
    void deleteProject(Long projectId, Long userId)throws Exception;


    Project updateProject(Project updatedProject, Long id)throws Exception;


    void addUserToProject(Long projectId, Long userId) throws Exception;



    void removeUserfromProject(Long projectId, Long userId) throws Exception;



    Chat getChatByProjectId(Long projectId) throws Exception;



    List<Project> searchProjects(String keyword, User user) throws Exception;




}
