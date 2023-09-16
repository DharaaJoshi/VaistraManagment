package Vaistra.Managment.MasterMines.Repository;

import Vaistra.Managment.MasterMines.Dao.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<Designation,Integer> {
    boolean existsByTypeOfDesignation(String typeOfDesignation);

    Designation findByTypeOfDesignation(String typeOfDesignation);
}
