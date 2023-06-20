package com.example.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {
    @Id
    @Column(name = "exp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exp_id;
    private Long amount;
    private String expense_name;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "expense-ref")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

}
