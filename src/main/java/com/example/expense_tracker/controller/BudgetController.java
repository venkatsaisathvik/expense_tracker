package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Budget;
import com.example.expense_tracker.repo.BudgetRepo;
import com.example.expense_tracker.repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetRepo br;

    @Autowired
    private ExpenseRepo er;

    @PostMapping("/addbudget")
    public ResponseEntity<Object> addbudget(@RequestBody Budget b){
        if(er.sumOfExpenses(b.getCategory().toString(),b.getUser().getUser_id())>b.getAmount()){
            Map<String,String> m = new HashMap<String,String>();
            m.put("Error","Expense already exceeds Budget");
            return new ResponseEntity<Object>(m, HttpStatus.NOT_ACCEPTABLE);
        }
        br.save(b);
        Map<String,String> m = new HashMap<String,String>();
        m.put("Success","Budget added successfully");
        return new ResponseEntity<Object>(m, HttpStatus.OK);
    }
}
