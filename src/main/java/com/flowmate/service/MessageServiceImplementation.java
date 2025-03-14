package com.flowmate.service;

import com.flowmate.model.Chat;
import com.flowmate.model.Message;
import com.flowmate.model.User;
import com.flowmate.repository.MessageRepository;
import com.flowmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;



@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProjectService projectService;


    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(()-> new Exception("User not found with id"+ senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();

//        Message message = new Message();
        com.flowmate.model.Message message = new com.flowmate.model.Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
//        Message savedMessage = messageRepository.save(message);
        com.flowmate.model.Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);


        return savedMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc= messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());


        return findByChatIdOrderByCreatedAtAsc;
    }
}
