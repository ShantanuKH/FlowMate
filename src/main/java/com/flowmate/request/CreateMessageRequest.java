package com.flowmate.request;


import lombok.Data;

@Data
public class CreateMessageRequest {

    private Long senderId;

    private String content;

    private Long projectId;



}
