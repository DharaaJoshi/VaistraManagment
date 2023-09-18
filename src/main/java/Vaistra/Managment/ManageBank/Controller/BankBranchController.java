package Vaistra.Managment.ManageBank.Controller;

import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Service.BankBranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("bankBranch")
public class BankBranchController {

    private final BankBranchService bankBranchService;

    public BankBranchController(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }
    @PostMapping
    public ResponseEntity<BankBranchDto> addBank(@RequestBody @Valid BankBranchDto bankBranchDto) {
        return new ResponseEntity<>(bankBranchService.addBankBranch(bankBranchDto), HttpStatus.OK);
    }
}
