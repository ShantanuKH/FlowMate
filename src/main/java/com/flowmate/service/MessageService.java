package com.flowmate.service;

import com.flowmate.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;



    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
