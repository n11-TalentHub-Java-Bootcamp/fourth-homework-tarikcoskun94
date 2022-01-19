package com.n11.fourthhomeworktarikcoskun94.service;

import com.n11.fourthhomeworktarikcoskun94.converter.user.UserMapper;
import com.n11.fourthhomeworktarikcoskun94.dto.user.UserResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.user.UserSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.entity.User;
import com.n11.fourthhomeworktarikcoskun94.exception.UserExistingFieldException;
import com.n11.fourthhomeworktarikcoskun94.exception.UserNotFoundException;
import com.n11.fourthhomeworktarikcoskun94.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> findAll() {

        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = UserMapper.INSTANCE.convertToUserResponseDTOList(userList);

        return userResponseDTOList;
    }

    public UserResponseDTO findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User is not found by ID: " + id));

        return UserMapper.INSTANCE.convertToUserResponseDTO(user);
    }

    public UserResponseDTO findByTcIdNumber(String tcIdNumber) {

        User user = userRepository.findByTcIdNumber(tcIdNumber).orElseThrow(() -> new UserNotFoundException("User is not found by TC ID Number: " + tcIdNumber));

        return UserMapper.INSTANCE.convertToUserResponseDTO(user);
    }

    public UserResponseDTO save(UserSaveRequestDTO userSaveRequestDTO) {

        /** START: Checking of request fields. */
        // Checks whether request fields are used by another.
        List<String> errorMessages = new ArrayList<>();

        if (userRepository.existsByTcIdNumber(userSaveRequestDTO.getUserTcIdNumber())) {
            errorMessages.add("TC ID Number is already exist: " + userSaveRequestDTO.getUserTcIdNumber());
        }
        if (userRepository.existsByEmail(userSaveRequestDTO.getUserEmail())) {
            errorMessages.add("Email is already exist: " + userSaveRequestDTO.getUserEmail());
        }
        if (userRepository.existsByPhoneNumber(userSaveRequestDTO.getUserPhoneNumber())) {
            errorMessages.add("Phone number is already exist: " + userSaveRequestDTO.getUserPhoneNumber());
        }

        // If any error message available, throw exception.
        if (!errorMessages.isEmpty()) {
            throw new UserExistingFieldException(errorMessages);
        }
        /** END: Checking of request fields. */

        User requestUser = UserMapper.INSTANCE.convertToUser(userSaveRequestDTO);
        User responseUser = userRepository.save(requestUser);

        return UserMapper.INSTANCE.convertToUserResponseDTO(responseUser);
    }

    @Transactional
    public void deleteById(Long id) {

        this.existsById(id);

        userRepository.deleteById(id);
    }

    public void existsById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User is not found by ID: " + id);
        }
    }
}
