package Vaistra.Managment.ManageBank.Repository;

import Vaistra.Managment.ManageBank.Dao.BankBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchRepo extends JpaRepository<BankBranch,Integer> {
    boolean existsByBranchName(String trim);

    boolean existsByBranchCode(String trim);

    boolean existsByBranchIfsc(String trim);

    boolean existsByBranchMicr(String trim);

    BankBranch findByBranchName(String trim);

    BankBranch findByBranchCode(String trim);

    BankBranch findByBranchIfsc(String trim);

    BankBranch findByBranchMicr(String trim);

    List<BankBranch> findAllByIsActive(boolean b);


   // Page<BankBranch> findByBankName_StateNameOrDistrict_DistrictNameOrBranchNameOrBranchCodeOrBranchAddressOrBranchIfOrBranchPhoneNumberOrBranchIdOrIsActive(Pageable pageable, String keyword, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6, String keyword7, Integer keyword9, Boolean keyword10);
}
