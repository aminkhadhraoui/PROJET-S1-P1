package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Tag;
import com.Projet.Projet.Services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
@CrossOrigin(origins = "*")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("")
    public List<Tag> getAllTags(){
        return tagService.getAllTags();
    }
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") Long tagId){
        return tagService.getTagById(tagId);
    }
    @PostMapping("/add")
    public Tag addTag(@RequestBody Tag tag){
        return tagService.addTag(tag);
    }
    @PutMapping("/update")
    public Tag updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable("id") Long tagId){
        tagService.deleteTag(tagId);
    }
    @GetMapping("/{tid}/formation/{fid}")
    public Tag affectFormationToTag(@PathVariable("fid") Long formationId, @PathVariable("tid") Long tagId){
        return tagService.affectFormationToTag(formationId,tagId);
    }
    @GetMapping("/name/{name}")
    public Optional<Tag> getTagByName(@PathVariable("name") String name){
        return tagService.getTagByName(name);
    }
}
