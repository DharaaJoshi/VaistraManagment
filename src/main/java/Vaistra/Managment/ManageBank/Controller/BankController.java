package Vaistra.Managment.ManageBank.Controller;

import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Service.BankService;
import Vaistra.Managment.ManageBank.Service.ServiceImpl.FileSizeExceedException;
import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("bank")
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping

    public ResponseEntity<BankDto> addBank( /*@RequestPart  MultipartFile image*/@RequestBody @Valid BankDto bankDto) throws IOException, FileSizeExceedException {
        return new ResponseEntity<>(bankService.addBank(bankDto/*image*/), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDto> updateBank(@PathVariable Integer id, @RequestPart @Valid BankDto bankDto, @RequestPart MultipartFile file) throws IOException {
        return new ResponseEntity<>(bankService.updateBank(id, bankDto, file), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<HttpResponse> getBankByKeyword(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                         @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                         @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
                                                         @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        return new ResponseEntity<>(bankService.getBankByKeyword(pageNo, pageSize, sortBy, sortDirection, keyword), HttpStatus.OK);
    }

    //    @GetMapping("/{bankId}/logo")
//    public ResponseEntity<byte[]> getBankLogo(@PathVariable Integer bankId) {
//        byte[] imageBytes = bankService.getBankLogo(bankId);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//
//        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//    }
    @DeleteMapping("/{bankId}")
    public ResponseEntity<String> deleteBank(@PathVariable Integer bankId) {
        return new ResponseEntity<>(bankService.deleteBank(bankId), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<BankDto>> getAllBank(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(bankService.getAllBank(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);


    }
    @PostMapping("/csv")
    public ResponseEntity<String> uploadBankCSV(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(bankService.uploadBankCSV(file),HttpStatus.OK);
    }
}
