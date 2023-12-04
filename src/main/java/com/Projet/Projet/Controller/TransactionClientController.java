package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.TransactionClient;
import com.Projet.Projet.Services.TransactionClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/client")
@CrossOrigin(origins = "*")
public class TransactionClientController {

    @Autowired
    private TransactionClientService transactionClientService;

    @GetMapping("")
    public List<TransactionClient> getAllTransactionsClient(){
        return transactionClientService.getAllTransactions();
    }
    @GetMapping("/{id}")
    public TransactionClient getTransactionClientById(@PathVariable("id") Long transactionId){
        return transactionClientService.getTransactionClientById(transactionId);
    }

    @GetMapping("/transactions/{id}")
    public List<TransactionClient> getTransactionsByClientId(@PathVariable("id") Long clientId){
        return transactionClientService.getTransactionsByClientId(clientId);
    }
    @PostMapping("/add")
    public TransactionClient addTransactionClient(@RequestBody TransactionClient transactionClient){
        return transactionClientService.addTransactionClient(transactionClient);
    }
    @PutMapping("/update")
    public TransactionClient updateTransactionClient(@RequestBody TransactionClient transactionClient){
        return transactionClientService.updateTransactionClient(transactionClient);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTransactionClient(@PathVariable("id") Long transactionId){
        transactionClientService.deleteTransactionClient(transactionId);
    }
    @GetMapping("/{cid}/transaction/{tid}")
    public TransactionClient addTransactionToClient(@PathVariable("cid") Long clientId, @PathVariable("tid") Long transactionId){
        return transactionClientService.addTransactionToClient(clientId,transactionId);
    }
    @GetMapping("/formation/{fid}/transaction/{tid}")
    public TransactionClient addTransactionToFormation(@PathVariable("fid") Long formationId, @PathVariable("tid") Long transactionId){
        return transactionClientService.addTransactionToFormation(formationId,transactionId);
    }

    @GetMapping("/stats/sumTransactionsClientPerCategory")
    List<Object[]> getSumTransactionsClientPerCategory(){
        return transactionClientService.getSumTransactionsClientPerCategory();
    @GetMapping("/course/{cid}")
    public List<TransactionClient> getTransactionClientsByFormation_Id(@PathVariable("cid") Long formationId){
        return transactionClientService.getTransactionClientsByFormation_Id(formationId);
    }

}
