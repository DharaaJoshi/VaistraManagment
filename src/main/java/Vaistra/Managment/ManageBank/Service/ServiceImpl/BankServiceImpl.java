package Vaistra.Managment.ManageBank.Service.ServiceImpl;

import Vaistra.Managment.ManageBank.Dao.Bank;
import Vaistra.Managment.ManageBank.Dto.BankDto;
import Vaistra.Managment.ManageBank.Repository.BankRepo;
import Vaistra.Managment.ManageBank.Service.BankService;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private final AppUtils appUtils;
    private final BankRepo bankRepo;
@Autowired
    public BankServiceImpl(AppUtils appUtils, BankRepo bankRepo) {
        this.appUtils = appUtils;
        this.bankRepo = bankRepo;
    }

    @Override
    public BankDto addBank(BankDto bankDto, MultipartFile file) throws IOException {
        String extension = file.getOriginalFilename();
        if(bankRepo.existsByBankShortName(bankDto.getBankShortName().trim())){
            throw new DuplicateEntryException("Short name: " + bankDto.getBankShortName() + " already exist .");
        }

        if(bankRepo.existsByBankName(bankDto.getBankLongName().trim())){
            throw new DuplicateEntryException("Long name: " + bankDto.getBankLongName() + " already exist .");
        }
        if(file.isEmpty()){
            throw new ResourceNotFoundException("Logo file not found.");
        }

        assert extension != null;
        if(!appUtils.isSupportedExtension(extension)){
            throw new ResourceNotFoundException("Only JPG,PNG allowed..!");
        }


        Bank bank = new Bank();

        bank.setLogo(file.getBytes());
        bank.setBankName(bankDto.getBankLongName().trim());
        bank.setBankShortName(bankDto.getBankShortName().trim());
        bank.setIsActive(bankDto.getIsActive());



        return appUtils.bankToDto(bankRepo.save(bank));
    }

    @Override
    public String updateBank(Integer bankId, BankDto bankDto, MultipartFile file) {
        return null;
    }

    @Override
    public String deleteBank(Integer bankId) {
        return null;
    }

    @Override
    public HttpResponse getBank(int pageNo, int pageSize, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public HttpResponse getBankByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
        return null;
    }

    @Override
    public byte[] getBankLogo(Integer bankId) {
        return new byte[0];
    }

    @Override
    public List<BankDto> getAllActiveBank() {
        return null;
    }
}
