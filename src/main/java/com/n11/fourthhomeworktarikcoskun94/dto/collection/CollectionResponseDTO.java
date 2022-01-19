package com.n11.fourthhomeworktarikcoskun94.dto.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.fourthhomeworktarikcoskun94.enum_.Gender;
import com.n11.fourthhomeworktarikcoskun94.enum_.LoanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CollectionResponseDTO {

    private Long collectionId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date collectionCreationDate;

    private Long userId;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userTcIdNumber;
    private Gender userGender;
    private String userEmail;
    private String userPhoneNumber;

    private Long loanId;
    private BigDecimal loanMainAmount;
    private BigDecimal loanRemainingAmount;
    private LoanType loanType;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date loanMaturityDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date loanCreationDate;
}
