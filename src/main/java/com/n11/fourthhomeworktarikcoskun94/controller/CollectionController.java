package com.n11.fourthhomeworktarikcoskun94.controller;

import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionInterestAmountResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.collection.CollectionResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworktarikcoskun94.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "collections")
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping(value = "/collect/{loanId}")
    public ResponseEntity<List<CollectionResponseDTO>> makeCollection(@PathVariable Long loanId) {

        return ResponseEntity.ok(collectionService.makeCollection(loanId));
    }

    @GetMapping(value = "/date-filter")
    public ResponseEntity<List<CollectionResponseDTO>> findAllByCollectionCreationDateBetween
            (
                    @RequestParam(value = "start-date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                    @RequestParam(value = "end-date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate
            ) {

        return ResponseEntity.ok(collectionService.findAllByCollectionCreationDateBetween(startDate, endDate));
    }

    @GetMapping(value = "/user-id/{userId}")
    public ResponseEntity<List<CollectionResponseDTO>> findAllByUserIdAndCollectionIdIsNotNull(@PathVariable Long userId) {

        return ResponseEntity.ok(collectionService.findAllByUserIdAndCollectionIdIsNotNull(userId));
    }

    @GetMapping(value = "/collected-total/user-id/{userId}")
    public ResponseEntity<CollectionInterestAmountResponseDTO> calculateCollectionInterestAmount(@PathVariable Long userId) {

        return ResponseEntity.ok(collectionService.calculateCollectionInterestAmount(userId));
    }
}
