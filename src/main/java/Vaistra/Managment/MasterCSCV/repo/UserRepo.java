package Vaistra.Managment.MasterCSCV.repo;


import Vaistra.Managment.MasterCSCV.Dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    boolean existsByEmail(String email);


}
