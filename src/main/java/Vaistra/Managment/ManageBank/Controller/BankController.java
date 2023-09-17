package Vaistra.Managment.ManageBank.Controller;

import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Service.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity<BankDto> addBank(@RequestPart @Valid BankDto bankDto, @RequestPart MultipartFile file) throws IOException {
        return new ResponseEntity<>(bankService.addBank(bankDto,file), HttpStatus.OK);
    }

}
