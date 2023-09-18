package Vaistra.Managment.Config;


import Vaistra.Managment.MasterCSCV.Dao.User;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserRepo userRepo;



    public UserServiceDetailsImpl(UserRepo userRepository){
        this.userRepo = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User not found...!"));
        return user;
    }
}
