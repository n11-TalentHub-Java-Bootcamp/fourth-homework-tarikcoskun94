package com.n11.fourthhomeworktarikcoskun94.service;

import com.n11.fourthhomeworktarikcoskun94.converter.loan.LoanMapper;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanMainSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanTotalAmountResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.Loan;
import com.n11.fourthhomeworktarikcoskun94.exception.LoanNotFoundException;
import com.n11.fourthhomeworktarikcoskun94.exception.UserNotFoundException;
import com.n11.fourthhomeworktarikcoskun94.repository.LoanRepository;
import com.n11.fourthhomeworktarikcoskun94.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserService userService;

    public LoanResponseDTO save(LoanMainSaveRequestDTO loanMainSaveRequestDTO) {

        Loan requestLoan = LoanMapper.INSTANCE.convertToLoan(loanMainSaveRequestDTO);
        Loan responseLoan = loanRepository.save(requestLoan);

        return LoanMapper.INSTANCE.convertToLoanResponseDTO(responseLoan);
    }

    public List<LoanResponseDTO> findAllByCreationDateBetween(Date startDate, Date endDate) {

        Date startDate00 = DateUtil.goToStartOfTheDate(startDate);
        Date endDate59 = DateUtil.goToEndOfTheDate(endDate);

        List<Loan> loanList = loanRepository.findAllByCreationDateBetween(startDate00, endDate59);
        List<LoanResponseDTO> loanResponseDTOList = LoanMapper.INSTANCE.convertToLoanResponseDTOList(loanList);

        return loanResponseDTOList;
    }

    public List<LoanResponseDTO> findAllByUserIdAndRemainingAmountGreaterThan(Long userId) {

        /** Check whether the user exist on DB. If not, it will throw exception. */
        userService.existsById(userId);

        List<Loan> loanList = loanRepository.findAllByUserIdAndRemainingAmountGreaterThan(userId, new BigDecimal("0"));
        List<LoanResponseDTO> loanResponseDTOList = LoanMapper.INSTANCE.convertToLoanResponseDTOList(loanList);

        return loanResponseDTOList;
    }

    public List<LoanResponseDTO> findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(Long userId) {

        /** Check whether the user exist on DB. If not, it will throw exception. */
        userService.existsById(userId);

        List<Loan> loanList = loanRepository.findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(userId, new BigDecimal("0"), new Date());
        List<LoanResponseDTO> loanResponseDTOList = LoanMapper.INSTANCE.convertToLoanResponseDTOList(loanList);

        return loanResponseDTOList;
    }

    public LoanTotalAmountResponseDTO calculateUnpaidLoanTotalAmount(Long userId) {

        BigDecimal loanTotalAmount = new BigDecimal("0");
        List<LoanResponseDTO> loanResponseDTOList = this.findAllByUserIdAndRemainingAmountGreaterThan(userId);

        for (LoanResponseDTO loanResponseDTO : loanResponseDTOList) {
            loanTotalAmount = loanTotalAmount.add(loanResponseDTO.getLoanRemainingAmount()).add(loanResponseDTO.getLoanInterestAmount());
        }

        LoanTotalAmountResponseDTO loanTotalAmountResponseDTO = new LoanTotalAmountResponseDTO();
        loanTotalAmountResponseDTO.setLoanTotalAmount(loanTotalAmount);
        loanTotalAmountResponseDTO.setLoanUserId(userId);

        return loanTotalAmountResponseDTO;
    }

    public LoanTotalAmountResponseDTO calculateMaturedLoanTotalAmount(Long userId) {

        BigDecimal loanTotalAmount = new BigDecimal("0");
        List<LoanResponseDTO> loanResponseDTOList = this.findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(userId);

        for (LoanResponseDTO loanResponseDTO : loanResponseDTOList) {
            loanTotalAmount = loanTotalAmount.add(loanResponseDTO.getLoanRemainingAmount()).add(loanResponseDTO.getLoanInterestAmount());
        }

        LoanTotalAmountResponseDTO loanTotalAmountResponseDTO = new LoanTotalAmountResponseDTO();
        loanTotalAmountResponseDTO.setLoanTotalAmount(loanTotalAmount);
        loanTotalAmountResponseDTO.setLoanUserId(userId);

        return loanTotalAmountResponseDTO;
    }

    public LoanTotalAmountResponseDTO calculateInterestLoanTotalAmount(Long userId) {

        BigDecimal loanTotalAmount = new BigDecimal("0");
        List<LoanResponseDTO> loanResponseDTOList = this.findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(userId);

        for (LoanResponseDTO loanResponseDTO : loanResponseDTOList) {
            loanTotalAmount = loanTotalAmount.add(loanResponseDTO.getLoanInterestAmount());
        }

        LoanTotalAmountResponseDTO loanTotalAmountResponseDTO = new LoanTotalAmountResponseDTO();
        loanTotalAmountResponseDTO.setLoanTotalAmount(loanTotalAmount);
        loanTotalAmountResponseDTO.setLoanUserId(userId);

        return loanTotalAmountResponseDTO;
    }
}
