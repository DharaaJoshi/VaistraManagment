package Vaistra.Managment.MasterMines.Repository;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterMines.Dao.Mineral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MineralRepo extends JpaRepository<Mineral,Integer> {
    boolean existsByMineralName(String mineralName);

    Mineral findByMineralName(String mineralName);

    Page<Country> findByMineralNameContainingIgnoreCase(String keyword, Pageable pageable);
}
