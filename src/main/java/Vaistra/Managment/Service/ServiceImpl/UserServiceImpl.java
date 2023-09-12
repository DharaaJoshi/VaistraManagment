package Vaistra.Managment.Service.ServiceImpl;

import Vaistra.Managment.Dao.User;

import Vaistra.Managment.Dto.UserDto;
import Vaistra.Managment.Exception.DuplicateEntryException;
import Vaistra.Managment.Repository.UserRepo;
import Vaistra.Managment.Service.UserService;
import Vaistra.Managment.Utils.AppUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AppUtils appUtils;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, AppUtils appUtils) {
        this.userRepo = userRepo;
        this.appUtils = appUtils;
    }

    @Override
    public UserDto addUser(@NotNull UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail()))
            throw new DuplicateEntryException("User with email '" + userDto.getEmail() + "' already exist!");
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setStatus(true);
        return appUtils.userToDto(userRepo.save(user));


    }
}