package com.n11.fourthhomeworktarikcoskun94.dto.user;

import com.n11.fourthhomeworktarikcoskun94.enum_.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequestDTO {

    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userTcIdNumber;
    private Gender userGender;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
}
