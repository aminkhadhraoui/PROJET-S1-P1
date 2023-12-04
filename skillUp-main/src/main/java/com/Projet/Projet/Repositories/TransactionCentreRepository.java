package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.TransactionCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionCentreRepository extends JpaRepository<TransactionCentre, Long> {

    List<TransactionCentre> getTransactionCentresByCentreFormation_Manager_Id(Long managerId);

    @Query("SELECT sum(valeur) FROM TransactionCentre")
    double getTransactionsCentresSum();
    @Query("SELECT SUM(t.valeur) FROM TransactionCentre t WHERE t.date = :date")
    Double getTotalForDay(@Param("date") LocalDate date);

    @Query("SELECT SUM(t.valeur) FROM TransactionCentre t WHERE t.date BETWEEN :startDate AND :endDate")
    Double getTotalForWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(t.valeur) FROM TransactionCentre t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    Double getTotalForMonth(@Param("year") int year, @Param("month") int month);

}
