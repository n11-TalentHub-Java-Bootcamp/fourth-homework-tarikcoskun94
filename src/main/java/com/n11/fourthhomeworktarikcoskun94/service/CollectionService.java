package com.n11.fourthhomeworktarikcoskun94.service;

import com.n11.fourthhomeworktarikcoskun94.converter.collection.CollectionMapper;
import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionInterestAmountResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.Collection;
import com.n11.fourthhomeworktarikcoskun94.entity.Loan;
import com.n11.fourthhomeworktarikcoskun94.enum_.LoanType;
import com.n11.fourthhomeworktarikcoskun94.exception.LoanNotFoundException;
import com.n11.fourthhomeworktarikcoskun94.repository.CollectionRepository;
import com.n11.fourthhomeworktarikcoskun94.repository.LoanRepository;
import com.n11.fourthhomeworktarikcoskun94.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final LoanRepository loanRepository;
    private final UserService userService;

    @Transactional
    public List<CollectionResponseDTO> makeCollection(Long loanId) {

        List<Loan> loanList = new ArrayList<>();

        /** Create a collection */
        Collection collection = new Collection();
        collection.setCreationDate(new Date());
        Collection savedCollection = collectionRepository.save(collection);

        Loan loanDB = loanRepository.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan is not found by ID: " + loanId));

        /** Set the collection to main loan and update it. */
        int remainingAmountCompareResult = loanDB.getRemainingAmount().compareTo(BigDecimal.ZERO);
        if (loanDB.getType() == LoanType.MAIN && remainingAmountCompareResult == 1) {

            loanDB.setRemainingAmount(new BigDecimal("0"));
            loanDB.setCollection(savedCollection);

            loanList.add(loanDB);
        }

        /** Create a interest loan if interestAmount is available. */
        /** Create a collection for just created interest loan. */
        int interestAmountCompareResult = loanDB.getInterestAmount().compareTo(BigDecimal.ZERO);
        if (loanDB.getType() == LoanType.MAIN && interestAmountCompareResult == 1) {

            Loan newInterestLoan = new Loan();

            newInterestLoan.setMainAmount(loanDB.getInterestAmount());
            newInterestLoan.setRemainingAmount(new BigDecimal("0"));
            newInterestLoan.setType(LoanType.INTEREST);
            newInterestLoan.setMaturityDate(savedCollection.getCreationDate());
            newInterestLoan.setCreationDate(savedCollection.getCreationDate());
            newInterestLoan.setSuperLoan(loanDB);
            newInterestLoan.setUser(loanDB.getUser());
            newInterestLoan.setCollection(savedCollection);

            loanList.add(newInterestLoan);
        }

        List<Loan> savedLoanList = loanRepository.saveAll(loanList);

        return CollectionMapper.INSTANCE.convertToCollectionResponseDTOList(savedLoanList);
    }

    public List<CollectionResponseDTO> findAllByCollectionCreationDateBetween(Date startDate, Date endDate) {

        Date startDate00 = DateUtil.goToStartOfTheDate(startDate);
        Date endDate59 = DateUtil.goToEndOfTheDate(endDate);

        List<Loan> loanList = loanRepository.findAllByCollectionCreationDateBetween(startDate00, endDate59);

        return CollectionMapper.INSTANCE.convertToCollectionResponseDTOList(loanList);
    }

    public List<CollectionResponseDTO> findAllByUserIdAndCollectionIdIsNotNull(Long userId) {

        userService.existsById(userId);

        List<Loan> loanList = loanRepository.findAllByUserIdAndCollectionIdIsNotNull(userId);

        return CollectionMapper.INSTANCE.convertToCollectionResponseDTOList(loanList);
    }

    public CollectionInterestAmountResponseDTO calculateCollectionInterestAmount(Long userId) {

        userService.existsById(userId);

        BigDecimal collectionInterestAmount = new BigDecimal("0");
        List<Loan> loanList = loanRepository.findAllByUserIdAndTypeEqualsInterest(userId);

        for (Loan loan : loanList) {
            collectionInterestAmount = collectionInterestAmount.add(loan.getMainAmount());
        }

        CollectionInterestAmountResponseDTO collectionInterestAmountResponseDTO = new CollectionInterestAmountResponseDTO();
        collectionInterestAmountResponseDTO.setCollectionInterestAmount(collectionInterestAmount);
        collectionInterestAmountResponseDTO.setUserId(userId);

        return collectionInterestAmountResponseDTO;
    }
}
