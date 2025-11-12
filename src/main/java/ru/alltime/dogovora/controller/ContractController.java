package ru.alltime.dogovora.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.ContractRequestDTO;
import ru.alltime.dogovora.dto.ContractResponseDTO;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.service.ContractServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@AllArgsConstructor

public class ContractController {

    private ContractServiceImpl contractService;

    @GetMapping()
    public ResponseEntity<List<ContractResponseDTO>> getContractList() {
        var contracts = contractService.findAllContracts();
        return ResponseEntity.ok(contracts);
    }

// todo uploadContractFile, downloadContractFile

    @GetMapping("{contractNum}")
    public ResponseEntity<ContractResponseDTO> getContract(@PathVariable String contractNum) {
        var contract = contractService.findContractByContractNum(contractNum);
        return ResponseEntity.ok(contract);
    }

    @PostMapping("create-contract")
    public ResponseEntity<ContractResponseDTO> createContract(ContractRequestDTO contractRequestDTO) {
        var contract = contractService.createContract(contractRequestDTO);
        return ResponseEntity.ok(contract);
    }
    @PutMapping("update-contract")
    public ResponseEntity<ContractResponseDTO> updateContract(ContractRequestDTO contractRequestDTO) {
        var contract = contractService.updateContract(contractRequestDTO);
        return ResponseEntity.ok(contract);
    }
}
