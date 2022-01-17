package com.n11.fourthhomeworktarikcoskun94.controller;

import com.n11.fourthhomeworktarikcoskun94.dto.user.UserResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.user.UserSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(value = "/tc-id-number/{tcIdNumber}")
    public ResponseEntity<UserResponseDTO> findByTcIdNumber(@PathVariable String tcIdNumber) {

        return ResponseEntity.ok(userService.findByTcIdNumber(tcIdNumber));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserSaveRequestDTO userSaveRequestDTO) {

        UserResponseDTO userResponseDTO = userService.save(userSaveRequestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(userResponseDTO.getUserId())
                .toUri();

        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        String responseMessage = "User has been deleted: " + id;

        return ResponseEntity.ok(responseMessage);
    }
}