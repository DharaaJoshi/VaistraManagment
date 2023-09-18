package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.UserDto;
import Vaistra.Managment.MasterCSCV.Service.CountryService;
import Vaistra.Managment.MasterCSCV.Service.UserService;
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

    public ResponseEntity<String>addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody @Valid UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id,userDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUser(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = "userId", required = false) String sortBy,
                                                 @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection){
        return new ResponseEntity<>(userService.getUser(pageNo,pageSize,sortBy,sortDirection),HttpStatus.OK);
    }

}
