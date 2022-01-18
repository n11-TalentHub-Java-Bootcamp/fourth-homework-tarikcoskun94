package com.n11.fourthhomeworktarikcoskun94.dto.loan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.fourthhomeworktarikcoskun94.enum_.LoanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LoanResponseDTO {

    private Long loanId;
    private BigDecimal loanMainAmount;
    private BigDecimal loanRemainingAmount;
    private BigDecimal loanInterestAmount;
    private LoanType loanType;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date loanMaturityDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date loanCreationDate;
    private Long loanSuperLoanId;
    private Long loanUserId;
    private Long loanCollectionId;
}
