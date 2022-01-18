package com.n11.fourthhomeworktarikcoskun94.converter.loan;

import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanMainSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.Loan;
import com.n11.fourthhomeworktarikcoskun94.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
        (
                unmappedTargetPolicy = ReportingPolicy.IGNORE,
                imports = MapperUtil.class
        )
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "loanId", source = "id")
    @Mapping(target = "loanMainAmount", source = "mainAmount")
    @Mapping(target = "loanRemainingAmount", source = "remainingAmount")
    @Mapping(target = "loanInterestAmount", source = "interestAmount")
    @Mapping(target = "loanType", source = "type")
    @Mapping(target = "loanMaturityDate", source = "maturityDate")
    @Mapping(target = "loanCreationDate", source = "creationDate")
    @Mapping(target = "loanSuperLoanId", source = "superLoan.id")
    @Mapping(target = "loanUserId", source = "user.id")
    @Mapping(target = "loanCollectionId", source = "collection.id")
    LoanResponseDTO convertToLoanResponseDTO(Loan loan);

    List<LoanResponseDTO> convertToLoanResponseDTOList(List<Loan> loanList);

    @Mapping(target = "mainAmount", source = "loanMainAmount")
    @Mapping(target = "remainingAmount", source = "loanMainAmount")
    @Mapping(target = "type", constant = "MAIN")
    @Mapping(target = "maturityDate", source = "loanMaturityDate")
    @Mapping(target = "creationDate", expression = "java( MapperUtil.getCurrentDate() )")
    @Mapping(target = "user.id", source = "loanUserId")
    Loan convertToLoan(LoanMainSaveRequestDTO loanMainSaveRequestDTO);
}
