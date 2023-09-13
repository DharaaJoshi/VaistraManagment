package Vaistra.Managment.Service.ServiceImpl;

import Vaistra.Managment.Dao.User;

import Vaistra.Managment.Dto.UserDto;
import Vaistra.Managment.Exception.DuplicateEntryException;
import Vaistra.Managment.Exception.ResourceNotFoundException;
import Vaistra.Managment.Repository.UserRepo;
import Vaistra.Managment.Service.UserService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public UserDto addUser( UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail()))
            throw new DuplicateEntryException("User with email '" + userDto.getEmail() + "' already exist!");
      //  User user = new User(UUID.randomUUID().toString(), "Dhara Joshi", "joshidhara2003@gmail.com");
        User user=appUtils.dtoToUser(userDto);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setStatus(true);
        return appUtils.userToDto(userRepo.save(user));


    }
    @Override
    public UserDto getUserById(int id) {
        return appUtils.userToDto(userRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("User with id '"+id+"' not found!")));
    }

    @Override
    public List<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> pageUser = userRepo.findAll(pageable);

        return appUtils.usersToDtos(pageUser.getContent());
    }
    public UserDto updateUser(UserDto userDto, int id) {
        User user = userRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("User with id '"+id+"' not found!"));

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return appUtils.userToDto(userRepo.save(user));
    }
    public String hardDeleteUserById(int id) {
        userRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("User with id '"+id+"' not found!"));

        userRepo.deleteById(id);
        return "User with id  "+id+"' deleted!";
    }

//    @Override
//    public Boolean verifyToken(String token) {
//
//        Confirmation confirmation = confirmationRepository.findByToken(token);
//        if(confirmation == null)
//            throw new ResourceNotFoundException("Confirmation with token '"+token+"' not found");
//
//        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
//        user.setStatus(true);
//        userRepository.save(user);
//        return Boolean.TRUE;
//    }
}
