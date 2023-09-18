package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dto.UserDto;
import Vaistra.Managment.MasterCSCV.Dao.User;

import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Exception.UnauthorizedException;
import Vaistra.Managment.MasterCSCV.Service.UserService;
import Vaistra.Managment.MasterCSCV.repo.UserRepo;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AppUtils appUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, AppUtils appUtils, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.appUtils = appUtils;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String addUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateEntryException("Email: " + userDto.getEmail() + " already exist !");
        }

        User user = new User();

        user.setEmail(userDto.getEmail().trim());
        user.setPassword(passwordEncoder.encode(userDto.getPassword().trim()));
        user.setName(userDto.getName().trim());

        userRepo.save(user);

        return "User Registered successfully.";    }

    @Override
    public String updateUser(Integer id, UserDto userDto) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Email Id not found with given id: " + id));

        User userEmail = userRepo.findByEmailIgnoreCase(user.getEmail().trim());

        if(userEmail != null ){
            throw new DuplicateEntryException("Email: " + userDto.getEmail() + " is already exist !");
        }

        if(!user.getEmail().equals(userDto.getEmail()) || !userDto.getEmail().equals("admin@gmail.com")) {
            throw new UnauthorizedException("Email: " + userDto.getEmail() + " is not authorize ");
        }
        user.setEmail(userDto.getEmail().trim());
        user.setPassword(passwordEncoder.encode(userDto.getPassword().trim()));
        user.setName(userDto.getName().trim());

        userRepo.save(user);

        return "User data updated successfully.";
    }

    @Override
    public String deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Email Id not found with  id: " + id));
        userRepo.deleteById(id);

        return "User Id: " + id + " deleted successfully.";
    }

    @Override
    public List<UserDto> getUser(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);


        Page<User> users = userRepo.findAll(pageable);

        if(users.isEmpty())
            throw new ResourceNotFoundException("Sorry, No data found...!");

        return appUtils.usersToDtos(users.getContent());
    }
}
