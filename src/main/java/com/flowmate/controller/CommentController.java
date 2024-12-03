package com.flowmate.controller;


import com.flowmate.model.Comment;
import com.flowmate.model.User;
import com.flowmate.request.CreateCommentRequest;
import com.flowmate.response.MessageResponse;
import com.flowmate.service.CommentService;
import com.flowmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;



    @Autowired
    private UserService userService;



    @PostMapping()
    public ResponseEntity<Comment> createComment(

            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception{
        User user = userService.findUSerProfileByJwt(jwt);

        Comment createComment = commentService.createComment(
                req.getIssueId(),
                user.getId(),
                req.getContent()
                );
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }


    @DeleteMapping("/{commentId")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization")
            String jwt
    ) throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);
        commentService.deleteComment(commentId  ,user.getId());
        MessageResponse res= new MessageResponse();
        res.setMessage("Comment deleted successfully");
return new ResponseEntity<>(res, HttpStatus.OK);


    }
@GetMapping("/{issueId")
public ResponseEntity<List<Comment>> getCommentByIssueId(
        @PathVariable Long issueId){
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);}
}
