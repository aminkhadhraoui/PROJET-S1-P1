package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Configuration.AuthenticationRequest;
import com.Projet.Projet.Configuration.AuthenticationResponse;
import com.Projet.Projet.Configuration.JwtService;
import com.Projet.Projet.Entities.User;
import com.Projet.Projet.Repositories.UserRepository;
import com.Projet.Projet.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public User lockAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("introuvable"));
        user.setAccountNonLocked(!user.isAccountNonLocked());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
