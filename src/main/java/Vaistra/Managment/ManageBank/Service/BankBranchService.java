package Vaistra.Managment.ManageBank.Service;

import Vaistra.Managment.ManageBank.Dto.BankBranchDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;

import java.util.List;

public interface BankBranchService {
    public BankBranchDto addBankBranch(BankBranchDto bankBranchDto);

    public BankBranchDto updateBankBranch(Integer branchId, BankBranchDto bankBranchDto);

    public String deleteBankBranch(Integer branchId);

    public HttpResponse getBankBranch(int pageNo, int pageSize, String sortBy, String sortDirection);

   // public HttpResponse getBankBranchByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);

    public List<BankBranchDto> getAllActiveBankBranch();
}
