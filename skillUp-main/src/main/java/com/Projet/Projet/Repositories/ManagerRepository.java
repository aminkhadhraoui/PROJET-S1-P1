package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    List<Manager> getManagerByAccountNonLocked(boolean value);

    @Query("SELECT count(*) FROM Manager")
    int getManagersNumber();
    List<Manager> findFirst10ByOrderByIdDesc();

}
