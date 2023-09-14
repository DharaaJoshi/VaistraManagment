package Vaistra.Managment.Repository;

import Vaistra.Managment.Dao.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepo extends JpaRepository<State,Integer> {
}
