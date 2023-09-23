package Vaistra.Managment.ManageBank.Service.ServiceImpl;

import Vaistra.Managment.ManageBank.Dao.Bank;
import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Repository.BankRepo;
import Vaistra.Managment.ManageBank.Service.BankService;
import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.Utils.AppUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

@Service
public class BankServiceImpl implements BankService {
    private final AppUtils appUtils;
    private final BankRepo bankRepo;
@Autowired
    public BankServiceImpl(AppUtils appUtils, BankRepo bankRepo) {
        this.appUtils = appUtils;
        this.bankRepo = bankRepo;
    }
    @Transactional
    @Override
    public BankDto addBank(BankDto bankDto/* MultipartFile file*/) throws IOException, FileSizeExceedException {
       // String extension = file.getOriginalFilename();
        if(bankRepo.existsByBankShortName(bankDto.getBankShortName().trim())){
            throw new DuplicateEntryException("Short name: " + bankDto.getBankShortName() + " already exist .");
        }

        if(bankRepo.existsByBankName(bankDto.getBankName().trim())){
            throw new DuplicateEntryException("Long name: " + bankDto.getBankName() + " already exist ");
        }
//
//        assert extension != null;
//        if(!appUtils.isSupportedExtension(extension)){
//            throw new ResourceNotFoundException("Only JPG,PNG allowed.");
//        }




        Bank bank = new Bank();

//        bank.setLogo();
        bank.setBankName(bankDto.getBankName().trim());
        bank.setBankShortName(bankDto.getBankShortName().trim());
        bank.setIsActive(bankDto.getIsActive());

       return appUtils.bankToDto(bankRepo.save(bank)) ;

    }

    @Override
    public BankDto updateBank(Integer bankId, BankDto bankDto, MultipartFile file) throws IOException {
        String extension = file.getOriginalFilename();

        Bank bank = bankRepo.findById(bankId).orElseThrow(()->new ResourceNotFoundException("Bank not found with id: " + bankId));

        Bank bankName = bankRepo.findByBankName(bankDto.getBankName().trim());

        if(bankName != null && !bankName.getId().equals(bank.getId())){
            throw new DuplicateEntryException("Bank : " + bankDto.getBankName() + " is already exist ...!");
        }

        Bank bankShortName = bankRepo.findByBankShortName(bankDto.getBankShortName().trim());

        if(bankShortName != null && !bankShortName.getId().equals(bank.getId())){
            throw new DuplicateEntryException("Bank short name : " + bankDto.getBankShortName() + " is already exist !");
        }



        assert extension != null;
        if(!appUtils.isSupportedExtension(extension)){
            throw new ResourceNotFoundException("Only JPG,PNG allowed..!");
        }



       // bank.setLogo(file.getBytes());
        bank.setBankName(bankDto.getBankName().trim());
        bank.setBankShortName(bankDto.getBankShortName().trim());
        bank.setIsActive(bankDto.getIsActive());

        ;

        return appUtils.bankToDto(bankRepo.save(bank));
    }


    @Override
    public String deleteBank(Integer bankId) {

            Bank bank = bankRepo.findById(bankId).orElseThrow(()->new ResourceNotFoundException("Bank not found with given id: " + bankId));
            bankRepo.deleteById(bankId);
            return "Record deleted successfully.";
        }




    @Override
    public HttpResponse getBankByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Bank> pageBank= bankRepo.findByBankNameContainingIgnoreCase(keyword, pageable);
        List<BankDto> Bank = appUtils.banksToDtos(pageBank.getContent());
        return HttpResponse.builder()
                .pageNumber(pageBank.getNumber())
                .pageSize(pageBank.getSize())
                .totalElements(pageBank.getTotalElements())
                .totalPages(pageBank.getTotalPages())
                .isLastPage(pageBank.isLast())
                .data(Bank)
                .build();
    }

    @Override
    public byte[] getBankLogo(Integer bankId) {
        Bank bank = bankRepo.findById(bankId).orElseThrow(()->new ResourceNotFoundException("Bank not found with given id: " + bankId));
        return null;
    }

    @Override
    public List<BankDto> getAllBank(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Bank> pageBank = bankRepo.findAll(pageable);
        return appUtils.banksToDtos(pageBank.getContent());
    }
    public String uploadBankCSV(MultipartFile file)  {


        if(file.isEmpty()){
            throw new ResourceNotFoundException(" CSV File not found.");
        }
        if(!Objects.equals(file.getContentType(), "text/csv")){
            throw new IllegalArgumentException("Invalid file type. Please upload a CSV file.");
        }

        try {
            List<Bank> banks= CSVParser.parse(file.getInputStream(), Charset.defaultCharset(), CSVFormat.DEFAULT)
                    .stream().skip(1) // Skip the first row
                    .map(record -> {
                        Bank bank = new Bank();
                        bank.setBankName(record.get(0).trim());
                        bank.setBankShortName(record.get(1).trim());
                        bank.setIsActive(Boolean.parseBoolean(record.get(2)));
                        return bank;
                    })
                    .toList();



            long uploadedRecordCount = banks.size();
            bankRepo.saveAll(banks);

            return "CSV file uploaded successfully. " + uploadedRecordCount + " records uploaded.";

        }catch (Exception e){
            return e.getMessage();
        }
    }
}
