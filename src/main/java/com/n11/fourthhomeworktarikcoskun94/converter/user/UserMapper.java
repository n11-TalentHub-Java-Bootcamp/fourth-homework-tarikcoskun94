package com.n11.fourthhomeworktarikcoskun94.converter.user;

import com.n11.fourthhomeworktarikcoskun94.dto.user.UserResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.user.UserSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.User;
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
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userFirstName", source = "firstName")
    @Mapping(target = "userMiddleName", source = "middleName")
    @Mapping(target = "userLastName", source = "lastName")
    @Mapping(target = "userTcIdNumber", source = "tcIdNumber")
    @Mapping(target = "userGender", source = "gender")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "userPhoneNumber", source = "phoneNumber")
    @Mapping(target = "userPassword", source = "password")
    @Mapping(target = "userRegistrationDate", source = "registrationDate")
    UserResponseDTO convertToUserResponseDTO(User user);

    List<UserResponseDTO> convertToUserResponseDTOList(List<User> userList);

    @Mapping(target = "firstName", source = "userFirstName")
    @Mapping(target = "middleName", source = "userMiddleName")
    @Mapping(target = "lastName", source = "userLastName")
    @Mapping(target = "tcIdNumber", source = "userTcIdNumber")
    @Mapping(target = "gender", source = "userGender")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "phoneNumber", source = "userPhoneNumber")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "registrationDate", expression = "java( MapperUtil.getCurrentDate() )")
    User convertToUser(UserSaveRequestDTO userSaveRequestDTO);
}
