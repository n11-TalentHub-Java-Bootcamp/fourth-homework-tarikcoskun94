package com.n11.fourthhomeworktarikcoskun94.dto.loan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LoanMainSaveRequestDTO {

    private BigDecimal loanMainAmount;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Europe/Istanbul")
    private Date loanMaturityDate;
    private Long loanUserId;
}
