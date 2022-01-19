package com.n11.fourthhomeworktarikcoskun94.repository;

import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionInterestAmountResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCreationDateBetween(Date startDate, Date endDate);

    List<Loan> findAllByUserIdAndRemainingAmountGreaterThan(Long userId, BigDecimal gtLimit);

    List<Loan> findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(Long userId, BigDecimal gtLimit, Date ltDate);

    /**
     * Below the queries is for loans that was collected.
     */
    List<Loan> findAllByCollectionCreationDateBetween(Date startDate, Date endDate);

    List<Loan> findAllByUserIdAndCollectionIdIsNotNull(Long userId);


    @Query(" select l " +
            " from Loan l " +
            " where l.type = 'INTEREST' and l.collection.id is not NULL and l.user.id = :userId ")
    List<Loan> findAllByUserIdAndTypeEqualsInterest(@Param("userId") Long userId);
}
