package com.n11.fourthhomeworktarikcoskun94.entity;

import com.n11.fourthhomeworktarikcoskun94.enum_.LoanType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "loan_id_seq", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "main_amount", nullable = false)
    private BigDecimal mainAmount;

    @Column(name = "remaining_amount", nullable = false)
    private BigDecimal remainingAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LoanType type;

    @Column(name = "maturity_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date maturityDate;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
            (
                    fetch = FetchType.LAZY
            )
    @JoinColumn
            (
                    name = "super_loan_id",
                    foreignKey = @ForeignKey(name = "fk_loan_loan_id")
            )
    private Loan superLoan;

    @ManyToOne
            (
                    fetch = FetchType.LAZY
            )
    @JoinColumn
            (
                    name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_loan_user_id"),
                    nullable = false
            )
    private User user;

    @ManyToOne
            (
                    fetch = FetchType.LAZY
            )
    @JoinColumn
            (
                    name = "collection_id",
                    foreignKey = @ForeignKey(name = "fk_loan_collection_id")
            )
    private Collection collection;
}
