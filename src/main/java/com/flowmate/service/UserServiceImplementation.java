package com.flowmate.service;


import com.flowmate.config.JwtProvider;
import com.flowmate.model.User;
import com.flowmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{


    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUSerProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromToken(jwt);




        return findUserBYEmail(email);
    }

    @Override
    public User findUserBYEmail(String email) throws Exception {

        User user=userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User Not Found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {

        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new Exception("UserNot Found");
        }
        return optionalUser.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {

        user.setProjectSize(user.getProjectSize()+number);


        return userRepository.save(user);
    }
}
