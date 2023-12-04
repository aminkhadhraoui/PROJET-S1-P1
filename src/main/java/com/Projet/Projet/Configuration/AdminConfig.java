package com.Projet.Projet.Configuration;

import com.Projet.Projet.Entities.Admin;
import com.Projet.Projet.Enum.Role;
import com.Projet.Projet.Repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminConfig implements CommandLineRunner {

    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setMdp(passwordEncoder.encode("mmmmmmmm"));
            admin.setRole(Role.ADMIN);
            admin.setEmail("admin@admin.com");
            admin.setNom("Admin");
            admin.setPrenom("Super");
            adminRepository.save(admin);
        }
    }
}
