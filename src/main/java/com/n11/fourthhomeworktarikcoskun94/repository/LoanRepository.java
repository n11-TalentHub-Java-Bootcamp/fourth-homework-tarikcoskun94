package com.n11.fourthhomeworktarikcoskun94.repository;

import com.n11.fourthhomeworktarikcoskun94.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCreationDateBetween(Date startDate, Date endDate);

    List<Loan> findAllByUserIdAndRemainingAmountGreaterThan(Long userId, BigDecimal gtLimit);

    List<Loan> findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(Long userId, BigDecimal gtLimit, Date ltDate);
}
