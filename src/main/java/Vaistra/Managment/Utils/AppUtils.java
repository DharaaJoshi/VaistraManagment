package Vaistra.Managment.Utils;

import Vaistra.Managment.Dao.Country;
import Vaistra.Managment.Dao.User;
import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AppUtils {



    private final ModelMapper modelMapper;

    @Autowired
    public AppUtils(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public CountryDto countryToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    public Country dtoToCountry(CountryDto dto) {
        return modelMapper.map(dto, Country.class);    }

    public List<CountryDto> countriesToDtos(List<Country> countries) {
        java.lang.reflect.Type targetListType = new TypeToken<List<CountryDto>>() {}.getType();
        return modelMapper.map(countries, targetListType);
    }

    public List<Country> dtosToCountries(List<CountryDto> dtos) {
        java.lang.reflect.Type targetListType = new TypeToken<List<Country>>() {}.getType();
        return modelMapper.map(dtos, targetListType);
    }
    public UserDto userToDto(User user)
    {
        return modelMapper.map(user, UserDto.class);
    }
    public User dtoToUser(UserDto userDto)
    {
        return modelMapper.map(userDto, User.class);
    }

    public List<UserDto> usersToDtos(List<User> users) {
        java.lang.reflect.Type targetListType = new TypeToken<List<UserDto>>() {}.getType();
        return modelMapper.map(users, targetListType);
    }



}
