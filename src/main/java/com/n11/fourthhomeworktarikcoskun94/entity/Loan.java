package com.n11.fourthhomeworktarikcoskun94.entity;

import com.n11.fourthhomeworktarikcoskun94.enum_.LoanType;
import com.n11.fourthhomeworktarikcoskun94.util.DateUtil;
import com.n11.fourthhomeworktarikcoskun94.util.InterestRateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "loan_id_seq", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "main_amount", nullable = false, scale = 2)
    private BigDecimal mainAmount;

    @Column(name = "remaining_amount", nullable = false, scale = 2)
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

    @Transient
    private BigDecimal interestAmount;

    @PrePersist
    @PreUpdate
    private void runBeforePersist() {

        this.mainAmount = this.mainAmount.setScale(2, RoundingMode.HALF_EVEN);
        this.remainingAmount = this.remainingAmount.setScale(2, RoundingMode.HALF_EVEN);
        this.maturityDate = DateUtil.goToEndOfTheDate(this.maturityDate);
    }

    @PostLoad
    private void runOnLoad() {

        this.calculateInterestAmount();
    }

    private void calculateInterestAmount() {

        Date today = DateUtil.goToEndOfTheDate(new Date());
        int remainingAmountCompareResult = this.remainingAmount.compareTo(BigDecimal.ZERO);

        /**
         * If loan type is MAIN, remaining amount is grater than 0 and maturity date has passed,
         * calculate the interest amount.
         */
        if (this.type == LoanType.MAIN && remainingAmountCompareResult == 1 && today.after(this.maturityDate)) {

            Long diffDays = DateUtil.getDaysBetween(this.maturityDate, today);
            Double rate = InterestRateUtil.getInterestRateByDate(this.maturityDate);

            BigDecimal calculatedInterestAmount = this.remainingAmount.multiply(new BigDecimal(rate)).multiply(new BigDecimal(diffDays));
            calculatedInterestAmount = calculatedInterestAmount.setScale(2, RoundingMode.HALF_EVEN);

            /**
             * If calculated interest amount less than 1,
             * assign 1 as constant.
             * */
            if (calculatedInterestAmount.compareTo(BigDecimal.ONE) == -1) {
                this.interestAmount = BigDecimal.ONE;
            } else {
                this.interestAmount = calculatedInterestAmount;
            }
        } else {

            this.interestAmount = BigDecimal.ZERO;
        }
    }
}
