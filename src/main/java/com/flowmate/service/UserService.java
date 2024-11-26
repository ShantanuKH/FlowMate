package com.flowmate.service;

import com.flowmate.model.User;

public interface UserService {

    User findUSerProfileByJwt(String jwt)throws Exception;


    User findUserBYEmail(String email) throws Exception;

    User findUserById(Long userId) throws Exception;

    User updateUsersProjectSize(User user, int number);
}
