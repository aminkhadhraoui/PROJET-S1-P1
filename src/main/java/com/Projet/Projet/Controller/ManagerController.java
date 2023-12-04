package com.Projet.Projet.Controller;

import com.Projet.Projet.Configuration.ImageUpload;
import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Entities.Manager;
import com.Projet.Projet.Repositories.ManagerRepository;
import com.Projet.Projet.Services.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ManagerController {
    private ManagerService managerService;
    private ManagerRepository managerRepository;

    @GetMapping("")
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public Manager getManagerById(@PathVariable("id") Long managerId) {
        return managerService.getManagerById(managerId);
    }

    @PostMapping("/add")
    public Manager addFormation(@RequestPart("manager") Manager manager,
                                  @RequestPart("image") MultipartFile image) {
        Manager manager1 = managerService.addManager(manager);
        String orgFileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String ext;
        int dotIndex = orgFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            ext = orgFileName.substring(dotIndex);
            String fileName = "user-" + manager1.getId() + ext;
            String uploadDir = "../SkillUp-FE/src/assets/user-photos";
            ImageUpload.saveFile(uploadDir, fileName, image);
            manager1.setImg(fileName);
        }
        return managerService.updateManager(manager1);
    }
    @PutMapping("/update")
    public Manager updateManager(@RequestPart("user") Manager manager,
                                 @RequestPart("image") MultipartFile image) {
        String orgFileName = StringUtils.cleanPath(image.getOriginalFilename());
        if (!orgFileName.equals("")){
            String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
            String fileName = "user-" + manager.getId() + ext;
            String uploadDir = "../SkillUp-FE/src/assets/user-photos";
            ImageUpload.saveFile(uploadDir, fileName, image);
            manager.setImg(fileName);
        }
        return managerService.updateManager(manager);
    }


    @PutMapping("/update/checkout")
    public Manager updateClientCheckout(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteManager(@PathVariable("id") Long managerId) {
        managerService.deleteManager(managerId);
    }

    @GetMapping("/{mid}/center/{cid}")
    public Manager affectCenterToManager(@PathVariable("cid") Long centerId, @PathVariable("mid") Long managerId) {
        return managerService.affectCenterToManager(centerId, managerId);
    }

    @GetMapping("/etat/{value}")
    public List<Manager> getManagerByAccountNonLocked(@PathVariable("value") boolean value) {
        return managerService.getManagerByAccountNonLocked(value);
    }

    @GetMapping("/stats/managersCount")
    public int getManagersCount(){
        return managerService.getManagersCount();
    @GetMapping("/new")
    public List<Manager> findFirst10OrderByIdDesc() {
        return managerService.findFirst10OrderByIdDesc();
    }
}
