package com.example.expense_tracker.repo;

import com.example.expense_tracker.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expenses,Long> {
    @Query(value = "select sum(amount) from expenses where category = ?1 and user_id = ?2",nativeQuery = true)
    Long sumOfExpenses(String category, Long user_id);
}
