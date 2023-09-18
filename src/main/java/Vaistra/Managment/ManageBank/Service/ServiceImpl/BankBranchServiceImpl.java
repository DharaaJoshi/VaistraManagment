package Vaistra.Managment.ManageBank.Service.ServiceImpl;

import Vaistra.Managment.ManageBank.Dao.Bank;
import Vaistra.Managment.ManageBank.Dao.BankBranch;
import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.ManageBank.Repository.BankBranchRepo;
import Vaistra.Managment.ManageBank.Repository.BankRepo;
import Vaistra.Managment.ManageBank.Service.BankBranchService;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.DistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBranchServiceImpl implements BankBranchService {


    private final AppUtils appUtils;
    private final BankRepo bankRepo;
    private final BankBranchRepo bankBranchRepo;
    private  final StateRepo stateRepo;
    private final DistrictRepo districtRepo;

    public BankBranchServiceImpl(AppUtils appUtils, BankRepo bankRepo, BankBranchRepo bankBranchRepo, StateRepo stateRepo, DistrictRepo districtRepo) {
        this.appUtils = appUtils;
        this.bankRepo = bankRepo;
        this.bankBranchRepo = bankBranchRepo;
        this.stateRepo = stateRepo;
        this.districtRepo = districtRepo;
    }

    @Override
    public BankBranchDto addBankBranch(BankBranchDto bankBranchDto) {
        if(bankBranchRepo.existsByBranchName(bankBranchDto.getBranchName().trim())){
            throw new DuplicateEntryException("Branch name: " + bankBranchDto.getBranchName() + " already exist .");
        }

        if(bankBranchRepo.existsByBranchCode(bankBranchDto.getBranchCode().trim())){
            throw new DuplicateEntryException("Branch Code: " + bankBranchDto.getBranchCode() + " already exist .");
        }

        if(bankBranchRepo.existsByBranchIfsc(bankBranchDto.getBranchIfsc().trim())){
            throw new DuplicateEntryException("Branch IFSC Code: " + bankBranchDto.getBranchIfsc() + " already exist .");
        }

        if(bankBranchRepo.existsByBranchMicr(bankBranchDto.getBranchMicr().trim())){
            throw new DuplicateEntryException("Branch MICR Code: " + bankBranchDto.getBranchMicr() + " already exist .");
        }
        BankBranch bankBranch = new BankBranch();

        Bank bank = bankRepo.findById(bankBranchDto.getBankId()).orElseThrow(()->new ResourceNotFoundException("Bank not found with given id: " + bankBranchDto.getBankId()));
        State state = stateRepo.findById(bankBranchDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with given id: " + bankBranchDto.getStateId()));
        District district = districtRepo.findById(bankBranchDto.getDistrictId()).orElseThrow(()->new ResourceNotFoundException("District not found with given id: " + bankBranchDto.getDistrictId()));

        bankBranch.setBank(bank);
        bankBranch.setState(state);
        bankBranch.setDistrict(district);
        bankBranch.setBranchName(bankBranchDto.getBranchName().trim());
        bankBranch.setBranchCode(bankBranchDto.getBranchCode().trim());
        bankBranch.setBranchAddress(bankBranchDto.getBranchAddress().trim());
        bankBranch.setBranchIfsc(bankBranchDto.getBranchIfsc().trim());
        bankBranch.setBranchPhoneNumber(bankBranchDto.getBranchPhoneNumber().trim());
        bankBranch.setBranchMicr(bankBranchDto.getBranchMicr().trim());
        bankBranch.setFromTiming(bankBranchDto.getFromTiming());
        bankBranch.setToTiming(bankBranchDto.getToTiming());
        bankBranch.setIsActive(bankBranchDto.getIsActive());

        return  appUtils.bankBranchToDto(bankBranchRepo.save(bankBranch));
    }

    @Override
    public BankBranchDto updateBankBranch(Integer branchId, BankBranchDto bankBranchDto) {
        return null;
    }

    @Override
    public BankBranchDto deleteBankBranch(Integer branchId) {
        return null;
    }

    @Override
    public HttpResponse getBankBranch(int pageNo, int pageSize, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public HttpResponse getBankBranchByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
        return null;
    }

    @Override
    public List<BankBranchDto> getAllActiveBankBranch() {
        return null;
    }
}
