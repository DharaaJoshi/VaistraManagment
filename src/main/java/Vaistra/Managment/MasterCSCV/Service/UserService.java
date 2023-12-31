package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.UserDto;

import java.util.List;

public interface UserService{
    public String addUser(UserDto userDto);

    public String updateUser(Integer id, UserDto userDto);

    public String deleteUser(Integer id);

    public List<UserDto> getUser(int pageNo, int pageSize, String sortBy, String sortDirection);
}
