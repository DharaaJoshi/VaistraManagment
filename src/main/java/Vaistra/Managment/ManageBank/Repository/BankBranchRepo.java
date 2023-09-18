package Vaistra.Managment.ManageBank.Repository;

import Vaistra.Managment.ManageBank.Dao.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBranchRepo extends JpaRepository<BankBranch,Integer> {
    boolean existsByBranchName(String trim);

    boolean existsByBranchCode(String trim);

    boolean existsByBranchIfsc(String trim);

    boolean existsByBranchMicr(String trim);
}
