package ru.alltime.dogovora.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.service.ContractServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@AllArgsConstructor

public class ContractController {

    private ContractServiceImpl contractService;

    @GetMapping()
    public ResponseEntity<List<Contract>> getContractList() {
        var contracts = contractService.findAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("{contractNum}")
    public ResponseEntity<Contract> getContract(@PathVariable String contractNum) {
        var contract = contractService.findContractByContractNum(contractNum);
        return ResponseEntity.ok(contract);
    }
}
