package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.repo.CountryRepo;
import Vaistra.Managment.MasterCSCV.Service.CountryService;
import Vaistra.Managment.Utils.AppUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

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
        if (countryRepo.existsByCountry(countryDto.getCountry()))
            throw new DuplicateEntryException("Country with name '" + countryDto.getCountry() + "' already exist!");

        Country country = new Country();
        country.setCountry(countryDto.getCountry());
        country.setStatus(true);

        return appUtils.countryToDto(countryRepo.save(country));
    }



    @Override
    public CountryDto updateCountry(CountryDto countryDto, int id) {

        countryDto.setCountry(countryDto.getCountry().trim().toUpperCase());
        Country country = countryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));
        Country existedCountry = countryRepo.findByCountry(countryDto.getCountry());
        if (existedCountry != null)
            throw new DuplicateEntryException("Country with name '" + countryDto.getCountry() + "' already exist!");


        country.setCountry(countryDto.getCountry());


        return appUtils.countryToDto(countryRepo.save(country));
    }

    @Override
    public String deleteCountryById(int id) {
        countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country with Id '" + id + "' not found!"));

        countryRepo.deleteById(id);
        return "Country with Id '" + id + "' deleted";
    }


    @Override
    public List<CountryDto> getAllCountries(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Country> pageCountry = countryRepo.findAll(pageable);
        return appUtils.countriesToDtos(pageCountry.getContent());
    }



    @Override
    public String uploadCountryCSV(MultipartFile file) throws IOException {


        if(!Objects.equals(file.getContentType(), "csv")){
            throw new IllegalArgumentException("Invalid file type.upload a CSV file only.");
        }

        try {
            List<Country> countries = CSVParser.parse(file.getInputStream(), Charset.defaultCharset(), CSVFormat.DEFAULT)
                    .stream().skip(1)
                    .map(record -> {
                        Country country = new Country();
                        country.setCountry(record.get(0).trim());
                        country.setStatus(Boolean.parseBoolean(record.get(1)));
                        return country;
                    })
                    .toList();



            countryRepo.saveAll(countries);

            return "CSV file uploaded successfully. " ;

        }catch (Exception e){
            return e.getMessage();
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


    @Override
    public HttpResponse getCountryByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, Integer.MAX_VALUE, sort);

        Integer keyword2 = null;
        Boolean keyword3 = null;


        if (keyword.equalsIgnoreCase("true"))
            keyword3 = Boolean.TRUE;
        else if (keyword.equalsIgnoreCase("false")) {
            keyword3 = Boolean.FALSE;
        }

        try {
            keyword2 = Integer.parseInt(keyword);
        } catch (NumberFormatException e) {
            keyword2 = null;
        }
return null;

    }
    @Override
    public HttpResponse getCountry(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Country> countryPage = countryRepo.findAll(pageable);

        List<CountryDto> countries = appUtils.countriesToDtos(countryPage.getContent());

        return HttpResponse.builder()
                .pageNumber(countryPage.getNumber())
                .pageSize(countryPage.getSize())
                .totalElements(countryPage.getTotalElements())
                .totalPages(countryPage.getTotalPages())
                .isLastPage(countryPage.isLast())
                .data(countries)
                .build();
    }

}
