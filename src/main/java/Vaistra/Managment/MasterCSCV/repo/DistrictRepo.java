package Vaistra.Managment.MasterCSCV.repo;

import Vaistra.Managment.MasterCSCV.Dao.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepo extends JpaRepository<District,Integer> {
    List<District> findByState_StateId(int stateId);
//    List<District> findByState_Country_CountryId(int countryId);

    boolean existsByDistrictName(String name);
    



    Page<District> findByDistrictNameContainingIgnoreCase(String keyword, Pageable pageable);

    District findByDistrictName(String disName);
}
