package com.n11.fourthhomeworktarikcoskun94.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.n11.fourthhomeworktarikcoskun94.enum_.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {

    private Long userId;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userTcIdNumber;
    private Gender userGender;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
    @JsonFormat(pattern = "dd-M-yyyy HH:mm:ss", timezone = "Europe/Istanbul")
    private Date userRegistrationDate;
}
