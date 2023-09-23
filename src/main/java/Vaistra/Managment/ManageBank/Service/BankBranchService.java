package Vaistra.Managment.ManageBank.Service;

import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BankBranchService {
    public BankBranchDto addBankBranch(BankBranchDto bankBranchDto);

    public BankBranchDto updateBankBranch(Integer branchId, BankBranchDto bankBranchDto);

    public String deleteBankBranch(Integer branchId);




    HttpResponse getBankBranchByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);




    List<BankBranchDto> getAllBankBranch(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    String uploadBankBranchCSV(MultipartFile file);
}
