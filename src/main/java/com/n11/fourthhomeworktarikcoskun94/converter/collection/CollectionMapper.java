package com.n11.fourthhomeworktarikcoskun94.converter.collection;

import com.n11.fourthhomeworktarikcoskun94.converter.loan.LoanMapper;
import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionResponseDTO;
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
public interface CollectionMapper {

    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    @Mapping(target = "collectionId", source = "collection.id")
    @Mapping(target = "collectionCreationDate", source = "collection.creationDate")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFirstName", source = "user.firstName")
    @Mapping(target = "userMiddleName", source = "user.middleName")
    @Mapping(target = "userLastName", source = "user.lastName")
    @Mapping(target = "userTcIdNumber", source = "user.tcIdNumber")
    @Mapping(target = "userGender", source = "user.gender")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userPhoneNumber", source = "user.phoneNumber")
    @Mapping(target = "loanId", source = "id")
    @Mapping(target = "loanMainAmount", source = "mainAmount")
    @Mapping(target = "loanRemainingAmount", source = "remainingAmount")
    @Mapping(target = "loanType", source = "type")
    @Mapping(target = "loanMaturityDate", source = "maturityDate")
    @Mapping(target = "loanCreationDate", source = "creationDate")
    CollectionResponseDTO convertToCollectionResponseDTO(Loan loan);

    List<CollectionResponseDTO> convertToCollectionResponseDTOList(List<Loan> loanList);
}
