package com.example.expense_tracker.repo;

import com.example.expense_tracker.model.Budget;
import com.example.expense_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BudgetRepo extends JpaRepository<Budget,Long> {

    @Query(value = "select amount from budget where category = ?1 and user_id= ?2",nativeQuery = true)
    Long findCategory(String category, Long user_id);
}
