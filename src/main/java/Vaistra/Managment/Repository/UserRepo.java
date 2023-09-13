package Vaistra.Managment.Repository;


import Vaistra.Managment.Dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    boolean existsByEmail(String email);


}
