package Vaistra.Managment.MasterCSCV.repo;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country,Integer> {


    boolean existsByCountry(String name);

    Country findByCountry(String country);

    Page<Country> findByCountryContainingIgnoreCase(String keyword, Pageable pageable);
}
