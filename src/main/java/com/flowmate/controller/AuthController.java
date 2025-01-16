package com.flowmate.controller;

import com.flowmate.config.JwtProvider;
import com.flowmate.model.User;
import com.flowmate.repository.UserRepository;
import com.flowmate.request.LoginRequest;
import com.flowmate.response.AuthResponse;
import com.flowmate.service.CustomUserDetailsImplementation;
import com.flowmate.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImplementation customUserDetailsImplementation;


    private SubscriptionService subscriptionService''


    @PostMapping("/signup")

//    If user already exists

    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist=userRepository.findByEmail(user.getEmail());
        if(isUserExist!=null){
            throw new Exception("Email already Exist ");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("Password cannot be null or empty");
        }

//  New User
        User createdUser = new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser=userRepository.save(createdUser);

//        Create Free subscription on creating account or Say signUp

        subscriptionService.createSubscription(savedUser);


//        Authentication

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());



        SecurityContextHolder.getContext().setAuthentication(authentication);

        //        Generate JWT Token and set

        String jwt = JwtProvider.generateToken(authentication);

//        SignUp Method is Created
        AuthResponse response = new AuthResponse();
        response.setMessage("SignUp Success");
        response.setJwt(jwt);


        return new ResponseEntity<>(response, HttpStatus.CREATED);



    }


    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest){


        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.generateToken(authentication);

//        SignUp Method is Created
        AuthResponse response = new AuthResponse();
        response.setMessage("Signin Successful");
        response.setJwt(jwt);


        return new ResponseEntity<>(response, HttpStatus.CREATED);



    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsImplementation.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException(("Invalid username" ));
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password Doesn't match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


    }

}
