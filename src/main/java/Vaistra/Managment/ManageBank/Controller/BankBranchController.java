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
import java.util.List;

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

    @PutMapping("{branchId}")
    public ResponseEntity<BankBranchDto> updateBank(@PathVariable Integer id, @RequestPart @Valid BankBranchDto bankBranchDto) {
        return new ResponseEntity<>(bankBranchService.updateBankBranch(id, bankBranchDto), HttpStatus.OK);

    }

    @GetMapping("search")
    public ResponseEntity<HttpResponse> getBank(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                @RequestParam(value = "sortBy", defaultValue = "branchId", required = false) String sortBy,
                                                @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
                                                @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        return new ResponseEntity<>(bankBranchService.getBankBranchByKeyword(pageNo, pageSize, sortBy, sortDirection, keyword), HttpStatus.OK);
    }

    @DeleteMapping("{bankBranchId}")
    public ResponseEntity<String> deleteBank(@PathVariable Integer bankBranchId) {
        return new ResponseEntity<>(bankBranchService.deleteBankBranch(bankBranchId), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<BankBranchDto>> getAllBankBranch(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(bankBranchService.getAllBankBranch(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);


    } @PostMapping("/csv")
    public ResponseEntity<String> uploadBankCSV(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(bankBranchService.uploadBankBranchCSV(file),HttpStatus.OK);
    }
}
