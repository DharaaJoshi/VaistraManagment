package Vaistra.Managment.MasterCSCV.repo;

import Vaistra.Managment.MasterCSCV.Dao.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictRepo extends JpaRepository<SubDistrict,Integer> {
     boolean existsBySubDistrictName(String subDistrictName); }
