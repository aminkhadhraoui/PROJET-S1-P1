package com.Projet.Projet.Controller;

import com.Projet.Projet.Configuration.AuthenticationRequest;
import com.Projet.Projet.Configuration.AuthenticationResponse;
import com.Projet.Projet.Entities.User;
import com.Projet.Projet.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/authenticate")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{id}")
    public User lockAccount(@PathVariable("id") Long userId) {
        return userService.lockAccount(userId);
    }

    @GetMapping("/email/{email}")
    public Optional<User> findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }
}