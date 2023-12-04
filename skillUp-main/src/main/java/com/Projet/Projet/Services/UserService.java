package com.Projet.Projet.Services;

import com.Projet.Projet.Configuration.AuthenticationRequest;
import com.Projet.Projet.Configuration.AuthenticationResponse;
import com.Projet.Projet.Entities.User;

import java.util.Optional;

public interface UserService{

    AuthenticationResponse login(AuthenticationRequest request);
    User lockAccount(Long userId);
    Optional<User> findByEmail(String email);


}
