package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Entities.Tag;
import com.Projet.Projet.Repositories.TagRepository;
import com.Projet.Projet.Services.FormationService;
import com.Projet.Projet.Services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;
    private FormationService formationService;

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(
                () -> new NoSuchElementException("aucune tag trouvée")
        );
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    @Override
    public Tag updateTag(Tag tag) {
        if (!tagRepository.existsById(tag.getId())){
            throw new NoSuchElementException("Aucune tag avec ID: " + tag.getId());
        }
        return tagRepository.save(tag);    }

    @Override
    public Tag affectFormationToTag(Long formationId, Long tagId) {
        Formation formation = formationService.getFormationById(formationId);
        Tag tag = getTagById(tagId);
        formation.getTags().add(tag);
        formationService.updateFormation(formation);
        tag.getFormations().add(formation);
        updateTag(tag);
        return tag;
    }

    @Override
    public Optional<Tag> getTagByName(String nom) {
        return tagRepository.getTagByNom(nom);
    }
}
