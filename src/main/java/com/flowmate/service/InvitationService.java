package com.flowmate.service;

import com.flowmate.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    public void sendInvitation(String email, Long projectId) throws MessagingException;



    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String userMail);




    void deleteToken (String token);


}
