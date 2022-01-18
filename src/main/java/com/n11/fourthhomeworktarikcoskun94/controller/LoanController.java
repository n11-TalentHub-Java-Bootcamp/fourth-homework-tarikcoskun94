package com.n11.fourthhomeworktarikcoskun94.controller;

import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanMainSaveRequestDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanTotalAmountResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> save(@RequestBody LoanMainSaveRequestDTO loanMainSaveRequestDTO) {

        LoanResponseDTO loanResponseDTO = loanService.save(loanMainSaveRequestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(loanResponseDTO.getLoanId())
                .toUri();

        return ResponseEntity.created(uri).body(loanResponseDTO);
    }

    @GetMapping(value = "/date-filter")
    public ResponseEntity<List<LoanResponseDTO>> findAllByCreationDateBetween
            (
                    @RequestParam(value = "start-date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                    @RequestParam(value = "end-date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate
            ) {

        return ResponseEntity.ok(loanService.findAllByCreationDateBetween(startDate, endDate));
    }

    @GetMapping(value = "/unpaid/user-id/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> findAllByUserIdAndRemainingAmountGreaterThan(@PathVariable Long userId) {

        return ResponseEntity.ok(loanService.findAllByUserIdAndRemainingAmountGreaterThan(userId));
    }

    @GetMapping(value = "/matured/user-id/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(@PathVariable Long userId) {

        return ResponseEntity.ok(loanService.findAllByUserIdAndRemainingAmountGreaterThanAndMaturityDateLessThan(userId));
    }

    @GetMapping(value = "/unpaid-total/user-id/{userId}")
    public ResponseEntity<LoanTotalAmountResponseDTO> calculateUnpaidLoanTotalAmount(@PathVariable Long userId) {

        return ResponseEntity.ok(loanService.calculateUnpaidLoanTotalAmount(userId));
    }

    @GetMapping(value = "/matured-total/user-id/{userId}")
    public ResponseEntity<LoanTotalAmountResponseDTO> calculateMaturedLoanTotalAmount(@PathVariable Long userId) {

        return ResponseEntity.ok(loanService.calculateMaturedLoanTotalAmount(userId));
    }

    @GetMapping(value = "/interest-total/user-id/{userId}")
    public ResponseEntity<LoanTotalAmountResponseDTO> calculateInterestLoanTotalAmount(@PathVariable Long userId) {

        return ResponseEntity.ok(loanService.calculateInterestLoanTotalAmount(userId));
    }
}
