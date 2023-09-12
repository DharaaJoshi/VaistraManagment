package Vaistra.Managment.Service;

import Vaistra.Managment.Dto.CountryDto;

import java.util.List;

public interface CountryService {



    public CountryDto addCountry(CountryDto countryDto);
    public CountryDto getCountryById(int id);
    public List<CountryDto>getCountryByName(int pageNumber,int pageSize,String sortBy, String SortDirection);
    public CountryDto updateCountry(CountryDto country,int id);
    public String deleteCountryById(int id);
    public String softDeleteCountryById(int id);
    public String restoreCountryById(int id);


    List<CountryDto> getAllCountries(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
