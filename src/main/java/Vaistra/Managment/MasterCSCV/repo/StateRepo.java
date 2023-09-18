package Vaistra.Managment.MasterCSCV.repo;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dao.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepo extends JpaRepository<State,Integer> {
    Page<State> findByCountry(Country country, Pageable p);
    State findByStateName(String name);
    boolean existsByStateName(String name);
    Page<State> findAllByCountry_Status(boolean b, Pageable p);
    Page<State> findByStateNameContainingIgnoreCase(String keyword, Pageable p);




    Page<State> findByStateNameOrCountry_CountryOrStateId(Pageable pageable, String keyword, String keyword1, Integer keyword3, Boolean keyword4);
}
