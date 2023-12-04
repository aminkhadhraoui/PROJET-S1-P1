package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.CentreFormation;
import com.Projet.Projet.Entities.Manager;
import com.Projet.Projet.Enum.Role;
import com.Projet.Projet.Repositories.ManagerRepository;
import com.Projet.Projet.Services.CentreFormationService;
import com.Projet.Projet.Services.ManagerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private ManagerRepository managerRepository;
    private CentreFormationService centreFormationService;
    private PasswordEncoder passwordEncoder;
    @Override
    public Manager addManager(Manager manager) {
        manager.setRole(Role.MANAGER);
        manager.setMdp(passwordEncoder.encode(manager.getMdp()));
        return managerRepository.save(manager);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public void deleteManager(Long managerId) {
        managerRepository.deleteById(managerId);
    }

    @Override
    public Manager getManagerById(Long managerId) {
        return managerRepository.findById(managerId).orElseThrow(
                () -> new NoSuchElementException("Aucun manager avec ID: " + managerId));
    }

    @Override
    @Transactional
    public Manager updateManager(Manager manager) {
        if (!managerRepository.existsById(manager.getId())){
            throw new NoSuchElementException("Aucun manager avec ID: " + manager.getId());
        }
        Manager managerUpdated = getManagerById(manager.getId());
        managerUpdated.setNom(manager.getNom());
        managerUpdated.setPrenom(manager.getPrenom());
        managerUpdated.setTel(manager.getTel());
        managerUpdated.setDateNaissance(manager.getDateNaissance());
        managerUpdated.setEmail(manager.getEmail());
        managerUpdated.setImg(manager.getImg());
        return managerUpdated;
    }

    @Override
    public Manager affectCenterToManager(Long centreId, Long managerId) {
        Manager manager = getManagerById(managerId);
        CentreFormation centreFormation = centreFormationService.getCentreFormationById(centreId);
        manager.getCentreFormation().add(centreFormation);
        updateManager(manager);
        centreFormation.setManager(manager);
        centreFormationService.updateCentreFormation(centreId,centreFormation);
        return manager;
    }

    @Override
    public List<Manager> getManagerByAccountNonLocked(boolean value) {
        return managerRepository.getManagerByAccountNonLocked(value);
    }

    @Override
    public int getManagersCount() {
        return managerRepository.getManagersNumber();
    public List<Manager> findFirst10OrderByIdDesc() {
        return managerRepository.findFirst10ByOrderByIdDesc();
    }
}
