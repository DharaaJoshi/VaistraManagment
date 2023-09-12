package Vaistra.Managment.Controller;

import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Dto.UserDto;
import Vaistra.Managment.Service.CountryService;
import Vaistra.Managment.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(CountryService countryService, UserService userService) {
        this.userService = userService;


    }

    public ResponseEntity<UserDto>addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

}
