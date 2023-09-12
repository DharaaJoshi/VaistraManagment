package Vaistra.Managment.Service.ServiceImpl;

import Vaistra.Managment.Dao.Country;
import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Exception.DuplicateEntryException;
import Vaistra.Managment.Exception.ResourceNotFoundException;
import Vaistra.Managment.Repository.CountryRepo;
import Vaistra.Managment.Service.CountryService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;
    private final AppUtils appUtils;

    @Autowired
    public CountryServiceImpl(CountryRepo countryRepo, AppUtils appUtils) {
        this.countryRepo = countryRepo;
        this.appUtils = appUtils;
    }




    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        if(countryRepo.existsByCountry(countryDto.getCountry()))
            throw new DuplicateEntryException("Country with name '"+countryDto.getCountry()+"' already exist!");

        Country country = new Country();
        country.setCountry(countryDto.getCountry());
        country.setStatus(true);

        return appUtils.countryToDto(countryRepo.save(country));
    }

    public CountryDto getCountryById (int id){
            return appUtils.countryToDto(countryRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Country with id '" + id + "' Not Found!")));
        }



        @Override
        public List<CountryDto> getCountryByName ( int pageNumber, int pageSize, String sortBy, String SortDirection){
            return null;
        }

        @Override
        public CountryDto updateCountry (CountryDto countryDto,int id){
//            Country country = countryRepo.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));
//            Country existedCountry = countryRepo.findByCountry(countryDto.getCountry());
//            if(existedCountry != null)
//                throw new DuplicateEntryException("Country with name '"+countryDto.getCountry()+"' already exist!");
//
//            country.setCountry(countryDto.getCountry().toUpperCase());
//            country.setStatus(countryDto.isStatus());
//            country.setDeleted(countryDto.isDeleted());
//            return appUtils.countryToDto(countryRepo.save(country));

            countryDto.setCountry(countryDto.getCountry().trim().toUpperCase());
            Country country = countryRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));
            Country existedCountry = countryRepo.findByCountry(countryDto.getCountry());
            if(existedCountry != null)
                throw new DuplicateEntryException("Country with name '"+countryDto.getCountry()+"' already exist!");


            country.setCountry(countryDto.getCountry());


            return appUtils.countryToDto(countryRepo.save(country));        }

        @Override
        public String deleteCountryById ( int id){
            countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));

            countryRepo.deleteById(id);
            return "Country with Id '" + id + "' deleted";
        }
    public String softDeleteCountryById(int id) {

        Country country = countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));
        country.setDeleted(true);
        countryRepo.save(country);
        return "Country with Id '" + id + "' Soft Deleted";

    }

    @Override
    public String restoreCountryById(int id) {
        Country country = countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));
        country.setDeleted(false);
        countryRepo.save(country);
       return "Country with id '" + id + "' restored!";


    }

    @Override
    public List<CountryDto>getAllCountries(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Country> pageCountry = countryRepo.findAll(pageable);
        return appUtils.countriesToDtos(pageCountry.getContent());
    }



}

