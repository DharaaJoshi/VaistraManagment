package Vaistra.Managment.Repository;

import Vaistra.Managment.Dao.Country;
import Vaistra.Managment.Dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country,Integer> {


    boolean existsByCountry(String name);

    Country findByCountry(String country);

    Optional<Object> findByCountry(Country country);
}
