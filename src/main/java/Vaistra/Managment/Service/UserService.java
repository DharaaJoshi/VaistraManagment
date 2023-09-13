package Vaistra.Managment.Service;

import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Dto.UserDto;

import java.util.List;

public interface UserService{
    UserDto addUser(UserDto userDto);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDirection);
    UserDto updateUser(UserDto userDto, int id);

    String hardDeleteUserById(int id);

   // Boolean verifyToken(String token);
}
