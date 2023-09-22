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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        bankBranch.setBranchName(bankBranchDto.getBranchName());
        bankBranch.setBranchCode(bankBranchDto.getBranchCode());
        bankBranch.setBranchAddress(bankBranchDto.getBranchAddress());
        bankBranch.setBranchIfsc(bankBranchDto.getBranchIfsc().trim());
        bankBranch.setBranchPhoneNumber(bankBranchDto.getBranchPhoneNumber());
        bankBranch.setBranchMicr(bankBranchDto.getBranchMicr());
        bankBranch.setFromTiming(bankBranchDto.getFromTiming());
        bankBranch.setToTiming(bankBranchDto.getToTiming());
        bankBranch.setIsActive(bankBranchDto.getIsActive());

        return  appUtils.bankBranchToDto(bankBranchRepo.save(bankBranch));
    }

    @Override
    public BankBranchDto updateBankBranch(Integer branchId, BankBranchDto bankBranchDto) {
        BankBranch bankBranch = bankBranchRepo.findById(branchId).orElseThrow(()->new ResourceNotFoundException("Bank Branch not found with given id: " + branchId));

        BankBranch bankBranchName= bankBranchRepo.findByBranchName(bankBranchDto.getBranchName().trim());


        if(bankBranchName != null)
            throw new DuplicateEntryException("Branch with name '"+bankBranchDto.getBranchName()+"' already exist!");


        BankBranch bankBranchCode = bankBranchRepo.findByBranchCode(bankBranchDto.getBranchCode().trim());

        if(bankBranchCode != null ){
            throw new DuplicateEntryException("Bank Branch Code: " + bankBranchDto.getBranchCode() + " is already exist!");
        }

        BankBranch bankBranchIFSCCode= bankBranchRepo.findByBranchIfsc(bankBranchDto.getBranchIfsc().trim());

        if(bankBranchIFSCCode != null ){
            throw new DuplicateEntryException("Bank Branch IFSC Code: " + bankBranchDto.getBranchIfsc() + " is already exist !");
        }

        BankBranch bankBranchMICRCode = bankBranchRepo.findByBranchMicr(bankBranchDto.getBranchMicr().trim());

        if(bankBranchMICRCode != null ){
            throw new DuplicateEntryException("Bank Branch MICR Code: " + bankBranchDto.getBranchMicr() + " is already exist !");
        }
        Bank bank = bankRepo.findById(bankBranchDto.getBankId()).orElseThrow(()->new ResourceNotFoundException("Bank not found with  id: " + bankBranchDto.getBankId()));
        State state = stateRepo.findById(bankBranchDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with  id: " + bankBranchDto.getStateId()));
        District district = districtRepo.findById(bankBranchDto.getDistrictId()).orElseThrow(()->new ResourceNotFoundException("District not found with  id: " + bankBranchDto.getDistrictId()));

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

        return appUtils.bankBranchToDto(bankBranchRepo.save(bankBranch));


    }

    @Override
    public String deleteBankBranch(Integer branchId) {
        BankBranch bankBranch = bankBranchRepo.findById(branchId).orElseThrow(()->new ResourceNotFoundException("Bank Branch not found with given id: " + branchId));
        bankBranchRepo.deleteById(branchId);
        return "Record deleted successfully.";
    }

    @Override
    public HttpResponse getBankBranch(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<BankBranch> bankBranchPage = bankBranchRepo.findAll(pageable);

        List<BankBranchDto> bankBranches = appUtils.bankBranchesToDtos(bankBranchPage.getContent());

        return HttpResponse.builder()
                .pageNumber(bankBranchPage.getNumber())
                .pageSize(bankBranchPage.getSize())
                .totalElements(bankBranchPage.getTotalElements())
                .totalPages(bankBranchPage.getTotalPages())
                .isLastPage(bankBranchPage.isLast())
                .data(bankBranches)
                .build();    }

//    @Override
//    public HttpResponse getBankBranchByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
//        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
//                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, Integer.MAX_VALUE, sort);
//
//        Integer keyword9 = null;
//        Boolean keyword10 = null;
//
//        try {
//            keyword9 = Integer.parseInt(keyword);
//        } catch (NumberFormatException e) {
//            keyword9 = null;
//        }
//
//        if(keyword.equalsIgnoreCase("true"))
//            keyword10 = Boolean.TRUE;
//        else if (keyword.equalsIgnoreCase("false")) {
//            keyword10 = Boolean.FALSE;
//        }
//
//     //   Page<BankBranch> bankBranchPage = bankBranchRepo.findByBank_BankNameor_StateNameOrDistrict_DistrictNameOrBranchNameOrBranchCodeOrBranchAddressOrBranchIfOrBranchPhoneNumberOrBranchIdOrIsActive(pageable,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword9,keyword10);
//
//        List<BankBranchDto> bankBranches = appUtils.bankBranchesToDtos(bankBranchPage.getContent());
//
//        return HttpResponse.builder()
//                .pageNumber(bankBranchPage.getNumber())
//                .pageSize(bankBranchPage.getSize())
//                .totalElements(bankBranchPage.getTotalElements())
//                .totalPages(bankBranchPage.getTotalPages())
//                .isLastPage(bankBranchPage.isLast())
//                .data(bankBranches)
//                .build();
//
//    }

    @Override
    public List<BankBranchDto> getAllActiveBankBranch() {
        List<BankBranch> bankBranches = bankBranchRepo.findAllByIsActive(true);

        if (bankBranches.isEmpty())
            throw new ResourceNotFoundException("Branch Data not available.!");

        return appUtils.bankBranchesToDtos(bankBranches);
    }
}
