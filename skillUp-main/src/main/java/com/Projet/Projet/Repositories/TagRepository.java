package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> getTagByNom(String nom);
    List<Tag> getTagsByFormations_Id(Long idFormation);
}
