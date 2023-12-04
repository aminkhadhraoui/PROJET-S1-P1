package com.Projet.Projet.Controller;

import com.Projet.Projet.Configuration.ImageUpload;
import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping("/add")
    public Client addClient(@RequestPart("candidat") Client client,
                            @RequestPart("image") MultipartFile image) {
        Client client1 = clientService.addClient(client);
        String orgFileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String ext;
        int dotIndex = orgFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            ext = orgFileName.substring(dotIndex);
            String fileName = "user-" + client1.getId() + ext;
            String uploadDir = "../SkillUp-FE/src/assets/user-photos";
            ImageUpload.saveFile(uploadDir, fileName, image);
            client1.setImg(fileName);
        }
        return clientService.updateClient(client1);
    }

    @PutMapping("/update")
    public Client updateClient(@RequestPart("user") Client client,
                               @RequestPart("image") MultipartFile image) {
        String orgFileName = StringUtils.cleanPath(image.getOriginalFilename());
        if (!orgFileName.equals("")){
            String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
            String fileName = "user-" + client.getId() + ext;
            String uploadDir = "../SkillUp-FE/src/assets/user-photos";
            ImageUpload.saveFile(uploadDir, fileName, image);
            client.setImg(fileName);
        }
        return clientService.updateClient(client);
    }

    @PutMapping("/update/checkout")
    public Client updateClientCheckout(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Long clientId) {
        clientService.deleteClient(clientId);
    }

    @GetMapping("/formation/{fid}/client/{cid}")
    public Client addClientToFormation(@PathVariable("cid") Long clientId, @PathVariable("fid") Long formationId) {
        return clientService.addClientToFormation(clientId, formationId);
    }

    @GetMapping("/etat/{value}")
    public List<Client> getClientsByAccountNonLocked(@PathVariable("value") boolean value) {
        return clientService.getClientsByAccountNonLocked(value);
    }

    @GetMapping("/stats/clientsCount")
    public int getClientsCount(){
        return this.clientService.getClientsNumber();
    }

}
