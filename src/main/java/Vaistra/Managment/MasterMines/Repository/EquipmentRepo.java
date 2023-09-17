package Vaistra.Managment.MasterMines.Repository;

import Vaistra.Managment.MasterMines.Dao.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment,Integer> {
    boolean existsByEquipment(String equipment);

    Equipment findByEquipment(String equipment);


}
