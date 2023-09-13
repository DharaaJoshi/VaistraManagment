package Vaistra.Managment.Config;



import Vaistra.Managment.Dao.User;
import Vaistra.Managment.Exception.ResourceNotFoundException;
import Vaistra.Managment.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@Component
//public class UserServiceDetailsImpl implements UserDetailsService {
//
//    private final UserRepo userRepo;
//
//    @Autowired
//    public UserServiceDetailsImpl(UserRepo userRepo ){
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = this.userRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User with username '"+username+"' not found!"));
//        return user;
//    }
//}
