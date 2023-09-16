package Vaistra.Managment.MasterMines.Repository;

import Vaistra.Managment.MasterMines.Dao.entity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepo extends JpaRepository<entity, Integer> {

    boolean existsByEntityType(String entityType);

    entity findByEntityType(String entityType);
}
