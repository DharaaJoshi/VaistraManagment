package Vaistra.Managment.ManageBank.Controller;

import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Service.BankBranchService;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
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
    public ResponseEntity<BankBranchDto> addBankBranch(@RequestBody @Valid BankBranchDto bankBranchDto) {
        return new ResponseEntity<>(bankBranchService.addBankBranch(bankBranchDto), HttpStatus.OK);
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BankBranchDto> updateBank(@PathVariable Integer id, @RequestPart @Valid BankBranchDto bankBranchDto) {
        return new ResponseEntity<>(bankBranchService.updateBankBranch(id, bankBranchDto), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<HttpResponse> getBank(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                @RequestParam(value = "sortBy", defaultValue = "branchId", required = false) String sortBy,
                                                @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
        return new ResponseEntity<>(bankBranchService.getBankBranch(pageNo, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

    @DeleteMapping("/{bankBranchId}")
    public ResponseEntity<String> deleteBank(@PathVariable Integer bankBranchId) {
        return new ResponseEntity<>(bankBranchService.deleteBankBranch(bankBranchId), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<HttpResponse> getBankBranch(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
//                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
//                                                      @RequestParam(value = "sortBy", defaultValue = "branchId", required = false) String sortBy,
//                                                      @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
//                                                      @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
//        {
//            return new ResponseEntity<>(bankBranchService.getBankBranchByKeyword(pageNo, pageSize, sortBy, sortDirection, keyword), HttpStatus.OK);
//        }
//    }
}
