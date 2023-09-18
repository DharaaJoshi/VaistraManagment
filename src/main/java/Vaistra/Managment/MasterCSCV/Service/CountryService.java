package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CountryService {



    public CountryDto addCountry(CountryDto countryDto);

    public CountryDto updateCountry(CountryDto country,int id);
    public String deleteCountryById(int id);



    List<CountryDto> getAllCountries(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    public String uploadCountryCSV(MultipartFile file) throws IOException;

    HttpResponse getCountryByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);

    HttpResponse getCountry(int pageNo, int pageSize, String sortBy, String sortDirection);
}
