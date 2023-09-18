package Vaistra.Managment.Config;

import Vaistra.Managment.MasterCSCV.Dao.User;
import Vaistra.Managment.MasterCSCV.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {


    private UserRepo userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppConfig(UserRepo userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeAdminUser() {
        User user = userRepository.findByEmailIgnoreCase("admin@gmail.com");

        if (user == null) {
            user = new User();

            user.setEmail("admi@gmail.com");
            user.setPassword(passwordEncoder.encode("123456789"));
            user.setName("Admin");
            userRepository.save(user);
        }
    }

}


