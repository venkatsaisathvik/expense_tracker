package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expenses;
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
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepo er;
    @Autowired
    private BudgetRepo br;

    @PostMapping("/addexpensetouser")
    public ResponseEntity<Object> addexpense(@RequestBody Expenses e){
        Long total_exp_amount = er.sumOfExpenses(e.getCategory().toString(), e.getUser().getUser_id());
        Long budget_amount = br.findCategory(e.getCategory().toString(),e.getUser().getUser_id());
        System.out.println(budget_amount);
        if(budget_amount != null && (total_exp_amount+e.getAmount())>=budget_amount){
            Map<String,String> m = new HashMap<String,String>();
            m.put("Error","Expense exceeds budget");
            return new ResponseEntity<Object>(m,HttpStatus.NOT_ACCEPTABLE);
        }
        else if(budget_amount==null) {
            er.save(e);
            Map<String, String> m = new HashMap<String, String>();
            m.put("Success", "Expense successfully added but Budget is not specified");
            return new ResponseEntity<Object>(m, HttpStatus.OK);
        }
        else{
            er.save(e);
            Map<String, String> m = new HashMap<String, String>();
            m.put("Success", "Expense successfully added");
            return new ResponseEntity<Object>(m, HttpStatus.OK);
        }
    }
}
