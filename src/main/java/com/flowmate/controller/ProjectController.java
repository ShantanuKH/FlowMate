package com.flowmate.controller;


import com.flowmate.model.Chat;
import com.flowmate.model.Invitation;
import com.flowmate.model.Project;
import com.flowmate.model.User;
import com.flowmate.request.InviteRequest;
import com.flowmate.response.MessageResponse;
import com.flowmate.service.InvitationService;
import com.flowmate.service.ProjectService;
import com.flowmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private  UserService userService;


    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>>getProjects(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tags,
            @RequestParam("Authorization")String jwt
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        List<Project> projects=projectService.getProjectByTeam(user, category,tags);


            return new ResponseEntity<>(projects, HttpStatus.OK);

    }


//    For getting project by projectId
     @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectsById(
          @PathVariable Long projectId,
            @RequestParam("Authorization")String jwt
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        Project project=projectService.getProjectById(projectId);


        return new ResponseEntity<>(project, HttpStatus.OK);

    }




//    For Creating Project

    @PostMapping
    public ResponseEntity<Project>createProject(

            @RequestParam("Authorization")String jwt,
            @RequestBody Project project
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        Project createdProject=projectService.createProject(project,user);


        return new ResponseEntity<>(createdProject, HttpStatus.OK);



        }
    //        Update Project

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(
            @PathVariable Long projectId,
            @RequestParam("Authorization")String jwt,
            @RequestBody Project project
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        Project updatedProject=projectService.updateProject(project, projectId);


        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProject(
            @PathVariable Long projectId,
            @RequestParam("Authorization")String jwt
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());

        MessageResponse response = new MessageResponse("Project deleted Successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(
            @RequestParam(required = false)String keyword,
            @RequestParam("Authorization")String jwt
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        List<Project> projects=projectService.searchProjects(keyword, user);


        return new ResponseEntity<>(projects, HttpStatus.OK);

    }


    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat>getChatByProjectId(
            @PathVariable Long projectId,
            @RequestParam("Authorization")String jwt
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);


        return new ResponseEntity<>(chat, HttpStatus.OK);

    }

    //    To send the invitation

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestBody InviteRequest req,
            @RequestParam("Authorization")String jwt,
            @RequestBody Project project
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        MessageResponse res=new MessageResponse("User Invitation sent");


        return new ResponseEntity<>(res, HttpStatus.OK);

    }


//    Accept invitation project

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation> inviteProject(
            @RequestParam String token,
            @RequestParam("Authorization")String jwt,
            @RequestBody Project project
    )throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);

        Invitation invitation =  invitationService.acceptInvitation(token, user.getId());

        projectService.addUserToProject(invitation.getProjectId(), user.getId());

        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);

    }




}
