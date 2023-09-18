package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dao.User;
import Vaistra.Managment.MasterCSCV.Dto.AuthRequest;
import Vaistra.Managment.MasterCSCV.Dto.AuthResponse;
import Vaistra.Managment.Security.JwtHelper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private  JwtHelper jwtService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        User userDetails = (User) userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtService.generateToken(userDetails);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .name(userDetails.getName()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Credential");
        }

    }
}

