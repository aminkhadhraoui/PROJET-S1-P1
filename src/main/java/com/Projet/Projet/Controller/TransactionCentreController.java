package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.TransactionCentre;
import com.Projet.Projet.Services.TransactionCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transaction/centre")
@CrossOrigin(origins = "*")
public class TransactionCentreController {
    @Autowired
    private TransactionCentreService transactionCentreService;

    @GetMapping("")
    public List<TransactionCentre> getAllTransactionCentre(){
        return transactionCentreService.getAllTransactions();
    }
    @GetMapping("/{id}")
    public TransactionCentre getTransactionCentreById(@PathVariable("id") Long transactionId){
        return transactionCentreService.getTransactionById(transactionId);
    }
    @PostMapping("/add")
    public TransactionCentre addTransactionCentre(@RequestBody TransactionCentre transactionCentre){
        return transactionCentreService.addTransaction(transactionCentre);
    }
    @PutMapping("/update")
    public TransactionCentre updateTransactionCentre(@RequestBody TransactionCentre transactionCentre){
        return transactionCentreService.updateTransaction(transactionCentre);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteTransactionCentre(@PathVariable("id") Long transactionId){
        transactionCentreService.deleteTransaction(transactionId);
    }
    @GetMapping("/{cid}/transaction/{tid}")
    public TransactionCentre addTransactionToCentre(@PathVariable("cid") Long centreId, @PathVariable("tid") Long transactionId){
        return transactionCentreService.addTransactionToCentre(centreId,transactionId);
    }
    @GetMapping("/abonnement/{aid}/transaction/{tid}")
    public TransactionCentre addTransactionToAbonnement(@PathVariable("aid") Long abonnementId, @PathVariable("tid") Long transactionId){
        return transactionCentreService.addTransactionToAbonnement(abonnementId,transactionId);
    }

    @GetMapping("/manager/{id}")
    public List<TransactionCentre> getTransactionCentresByCentreFormation_Manager_Id(@PathVariable("id") Long managerId){
        return transactionCentreService.getTransactionCentresByCentreFormation_Manager_Id(managerId);
    }


    @GetMapping("/stats/transactionsSum")
    public double getTransactionsCentresSum(){
        return transactionCentreService.getTransactionsCentresSum();

    @GetMapping("/sales/day")
    public Double getTotalForDay() {
        return transactionCentreService.getTotalForDay();
    }

    @GetMapping("/sales/week")
    public Double getTotalForWeek() {
        return transactionCentreService.getTotalForWeek();
    }

    @GetMapping("/sales/month")
    public Double getTotalForMonth() {
        return transactionCentreService.getTotalForMonth();
    }
}
