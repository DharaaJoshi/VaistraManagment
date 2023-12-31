package Vaistra.Managment.ManageBank.Repository;

import Vaistra.Managment.ManageBank.Dao.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepo extends JpaRepository<Bank,Integer> {
     Page<Bank> findByBankNameContainingIgnoreCase(String keyword, Pageable pageable) ;


    boolean existsByBankShortName(String trim);



    boolean existsByBankName(String trim);

    Bank findByBankName(String trim);

    Bank findByBankShortName(String trim);

    List<Bank> findAllByIsActive(boolean b);
}
