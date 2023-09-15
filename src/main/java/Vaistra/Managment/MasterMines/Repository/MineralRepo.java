package Vaistra.Managment.MasterMines.Repository;

import Vaistra.Managment.MasterMines.Dao.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MineralRepo extends JpaRepository<Mineral,Integer> {
}
