package com.n11.fourthhomeworktarikcoskun94.dto.loan;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanTotalAmountResponseDTO {

    private BigDecimal loanTotalAmount;
    private Long loanUserId;
}
