package com.flowmate.service;

import com.flowmate.model.Chat;
import com.flowmate.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImplementation implements ChatService{

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
