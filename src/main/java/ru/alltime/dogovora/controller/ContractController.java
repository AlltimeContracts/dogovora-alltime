package ru.alltime.dogovora.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.ContractRequestDTO;
import ru.alltime.dogovora.dto.ContractResponseDTO;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.service.ContractServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contracts")
@AllArgsConstructor

public class ContractController {


    private ContractServiceImpl contractService;


    @GetMapping()
    public ResponseEntity<List<ContractResponseDTO>> getContractList() { // paging via requestParam
        var contracts = contractService.findAllContracts();
        return ResponseEntity.ok(contracts);
    }

// todo uploadContractFile, downloadContractFile



    @GetMapping("get-contracts-by-num")
    public ResponseEntity<List<ContractResponseDTO>> getContract(@RequestParam(required = true) String contractNum) {
        List<ContractResponseDTO> contracts = contractService.findContractsByContractNum(contractNum);
        return ResponseEntity.ok(contracts);
    }

    @PostMapping("create-contract")
    public ResponseEntity<ContractResponseDTO> createContract(@RequestBody ContractRequestDTO contractRequestDTO) {
        var contract = contractService.createContract(contractRequestDTO);
        return ResponseEntity.ok(contract);
    }

    @PutMapping("update-contract-info")
    public ResponseEntity<ContractResponseDTO> updateContract(@RequestBody ContractRequestDTO contractRequestDTO) {
        var contract = contractService.updateContract(contractRequestDTO);
        return ResponseEntity.ok(contract);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable UUID id){
        var contract = contractService.findContractById(id);
        contractService.deleteContractById(id);
        return ResponseEntity.ok("Contract with id: " + id + " successfully deleted");
    }
}
