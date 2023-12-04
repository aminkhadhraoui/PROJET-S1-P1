package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag addTag(Tag tag);
    Tag getTagById(Long tagId);
    List<Tag> getAllTags();
    void deleteTag(Long tagId);
    Tag updateTag(Tag tag);
    Tag affectFormationToTag(Long formationId, Long tagId);
    Optional<Tag> getTagByName(String nom);
}
