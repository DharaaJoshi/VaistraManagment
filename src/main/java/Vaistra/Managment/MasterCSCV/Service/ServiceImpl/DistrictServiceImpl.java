package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dao.District;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Service.DistrictService;
import Vaistra.Managment.MasterCSCV.repo.CountryRepo;
import Vaistra.Managment.MasterCSCV.repo.DistrictRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.Utils.AppUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

@Service
public class DistrictServiceImpl implements DistrictService {



    private final AppUtils appUtils;
    private final DistrictRepo districtRepo;

    private final StateRepo stateRepo;

    private final CountryRepo countryRepo;
    public DistrictServiceImpl(AppUtils appUtils, StateRepo stateRepo, DistrictRepo DistrictRepo, DistrictRepo districtRepo,CountryRepo countryRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;
        this.countryRepo= countryRepo;
        this.districtRepo = districtRepo;
    }

    @Override
    public DistrictDto addDistrict(DistrictDto districtDto) {
        districtDto.setDistrictName(districtDto.getDistrictName().trim().toUpperCase());

        if(districtRepo.existsByDistrictName(districtDto.getDistrictName()))
            throw new DuplicateEntryException("District with name '"+districtDto.getDistrictName()+"' already exist!");



        State state = stateRepo.findById(districtDto.getStateId())
                .orElseThrow(()->new ResourceNotFoundException("State with Id '"+districtDto.getStateId()+" not found!"));



        District district = new District();
        district.setDistrictName(districtDto.getDistrictName());
        district.setState(state);
        district.setCountry(state.getCountry());

          district.setStatus(true);

        return appUtils.districtToDto(districtRepo.save(district));
    }


    @Override
    public List<DistrictDto> getAllDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<District> pageDistrict = districtRepo.findAll(pageable);
        return appUtils.districtsToDtos(pageDistrict.getContent());
    }


    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto, int id) {

        districtDto.setDistrictName(districtDto.getDistrictName().trim().toUpperCase());
        // HANDLE IF DISTRICT EXIST BY ID
        District district = districtRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("District with id '"+id+"' not found!"));

        if(districtRepo.existsByDistrictName(districtDto.getDistrictName()))
            throw new DuplicateEntryException("District with name '"+districtDto.getDistrictName()+"' already exist!");

        State state = stateRepo.findById(districtDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("State not found with given id: " + districtDto.getStateId()));
        Country country = countryRepo.findById(districtDto.getId()).orElseThrow(()->new ResourceNotFoundException("Country not found with given id: " + districtDto.getId()));
        district.setDistrictName(districtDto.getDistrictName());

        return appUtils.districtToDto(districtRepo.save(district));
    }

    @Override
    public String deleteDistrictById(int id) {
        districtRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("District with id '"+id+"' not found!"));
        districtRepo.deleteById(id);
        return "District with id '"+id+"' deleted!";
    }

    @Override
    public String uploadDistrictCSV(MultipartFile file) {

        if(!Objects.equals(file.getContentType(), "text/csv")){
            throw new IllegalArgumentException("Invalid file type. CSV file.");
        }

        try {
            List<District> districts = CSVParser.parse(file.getInputStream(), Charset.defaultCharset(), CSVFormat.DEFAULT)
                    .stream().skip(1)
                    .map(record -> {
                        District district = new District();
                        district.setDistrictName(record.get(2).trim());
                        district.setStatus(Boolean.parseBoolean(record.get(3)));


                        String stateName = record.get(1).trim();

                        State state = stateRepo.findByStateName(stateName);

                        if(state == null){
                            state = new State();
                            state.setStateName(stateName.trim());
                            state.setStatus(true);

                            String countryName= record.get(0).trim();

                            Country country = countryRepo.findByCountry(countryName);

                            if (country == null) {
                                country = new Country();
                                country.setCountry(countryName.trim());
                                country.setStatus(true);
                                countryRepo.save(country);
                            }

                            state.setCountry(country);
                            stateRepo.save(state);

                        }


                        district.setState(state);
                        district.setCountry(state.getCountry());
                        return district;
                    })
                    .toList();
            districtRepo.saveAll(districts);

            return "CSV file uploaded successfully. records uploaded.";


        }catch (Exception e){
            return e.getMessage();
        }

    }
    @Override
    public HttpResponse searchDistrict(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<District> pageDistrict = districtRepo.findByDistrictNameContainingIgnoreCase(keyword, pageable);
        List<DistrictDto> dtos = appUtils.districtsToDtos(pageDistrict.getContent());
        return HttpResponse.builder()
                .pageNumber(pageDistrict.getNumber())
                .pageSize(pageDistrict.getSize())
                .totalElements(pageDistrict.getTotalElements())
                .totalPages(pageDistrict.getTotalPages())
                .isLastPage(pageDistrict.isLast())
                .data(dtos)
                .build();
    }

}
