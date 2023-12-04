package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Manager;

import java.util.List;

public interface ManagerService {

    Manager addManager(Manager manager);
    List<Manager> getAllManagers();
    void deleteManager(Long managerId) ;
    Manager getManagerById(Long managerId);
    Manager updateManager(Manager manager);
    Manager affectCenterToManager(Long centreId, Long managerId);
    List<Manager> getManagerByAccountNonLocked(boolean value);
    List<Manager> findFirst10OrderByIdDesc();

    int getManagersCount();

}
