package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Abonnement;
import com.Projet.Projet.Entities.CentreFormation;
import com.Projet.Projet.Entities.TransactionCentre;
import com.Projet.Projet.Repositories.TransactionCentreRepository;
import com.Projet.Projet.Services.AbonnementService;
import com.Projet.Projet.Services.CentreFormationService;
import com.Projet.Projet.Services.TransactionCentreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TransactionCentreServiceImpl implements TransactionCentreService {
    private TransactionCentreRepository transactionCentreRepository;
    private CentreFormationService centreFormationService;
    private AbonnementService abonnementService;
    @Override
    public TransactionCentre addTransaction(TransactionCentre transactionCentre) {
        transactionCentre.setDate(LocalDate.now());
        transactionCentre.setHeure(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute());
        return transactionCentreRepository.save(transactionCentre);
    }

    @Override
    public List<TransactionCentre> getAllTransactions() {
        return transactionCentreRepository.findAll();
    }

    @Override
    public TransactionCentre getTransactionById(Long transactionId) {
        return transactionCentreRepository.findById(transactionId).orElseThrow(
                () -> new NoSuchElementException("Aucune Transaction avec ID: "+transactionId));
    }

    @Override
    public TransactionCentre updateTransaction(TransactionCentre transactionCentre) {
        if (!transactionCentreRepository.existsById(transactionCentre.getId())){
            throw new NoSuchElementException("Aucune transaction avec ID: "+transactionCentre.getId());
        }
        return transactionCentreRepository.save(transactionCentre);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionCentreRepository.deleteById(transactionId);
    }

    @Override
    public TransactionCentre addTransactionToCentre(Long centreId, Long transactionId) {
        TransactionCentre transactionCentre = getTransactionById(transactionId);
        CentreFormation centreFormation = centreFormationService.getCentreFormationById(centreId);
        transactionCentre.setCentreFormation(centreFormation);
        updateTransaction(transactionCentre);
        centreFormation.getTransactions().add(transactionCentre);
        centreFormationService.updateCentreFormation(centreId,centreFormation);
        return transactionCentre;
    }

    @Override
    public TransactionCentre addTransactionToAbonnement(Long abonnementId, Long transactionId) {
        TransactionCentre transactionCentre = getTransactionById(transactionId);
        Abonnement abonnement = abonnementService.getAbonnementById(abonnementId);
        transactionCentre.setAbonnement(abonnement);
        updateTransaction(transactionCentre);
        abonnement.setTransaction(transactionCentre);
        abonnementService.editAbonnement(abonnementId, abonnement);
        return transactionCentre;
    }

    @Override
    public List<TransactionCentre> getTransactionCentresByCentreFormation_Manager_Id(Long managerId) {
        return transactionCentreRepository.getTransactionCentresByCentreFormation_Manager_Id(managerId);
    }

    @Override
    public double getTransactionsCentresSum() {
        return transactionCentreRepository.getTransactionsCentresSum();
    }

    public Double getTotalForDay() {
        return transactionCentreRepository.getTotalForDay(LocalDate.now());
    }

    @Override
    public Double getTotalForWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return transactionCentreRepository.getTotalForWeek(startOfWeek, endOfWeek);
    }


    @Override
    public Double getTotalForMonth() {
        return transactionCentreRepository.getTotalForMonth(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());
    }


}
