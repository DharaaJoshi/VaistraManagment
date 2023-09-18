package Vaistra.Managment.MasterCSCV.repo;

import Vaistra.Managment.MasterCSCV.Dao.SubDistrict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictRepo extends JpaRepository<SubDistrict,Integer> {
     boolean existsBySubDistrictName(String subDistrictName);

    Page<SubDistrict> findAllByDistrict_Status(boolean b, Pageable pageable);



    Page<SubDistrict> findBySubDistrictNameOrDistrict_DistrictNameOrState_StateNameOrCountry_CountryOrSubDistrictId(Pageable pageable, String keyword, String keyword1, String keyword2, String keyword3, Integer keyword5, Boolean keyword6);
}
