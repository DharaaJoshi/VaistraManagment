package Vaistra.Managment.Controller;

import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Dto.UserDto;
import Vaistra.Managment.Service.CountryService;
import Vaistra.Managment.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(CountryService countryService, UserService userService) {
        this.userService = userService;


    }
    @PostMapping

    public ResponseEntity<UserDto>addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id)
    {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection)
    {
        return new ResponseEntity<>(userService.getAllUsers(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int id)
    {
        return new ResponseEntity<>(userService.updateUser(userDto, id), HttpStatus.OK);
    }


}
