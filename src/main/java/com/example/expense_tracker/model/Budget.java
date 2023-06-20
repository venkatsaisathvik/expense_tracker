package com.example.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "budget")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @Column(name = "bud_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bud_id;
    private Long amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "budget-ref")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Period period;

}
