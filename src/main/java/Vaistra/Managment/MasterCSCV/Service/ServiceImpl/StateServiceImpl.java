package Vaistra.Managment.MasterCSCV.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterCSCV.Dao.Country;
import Vaistra.Managment.MasterCSCV.Dao.State;
import Vaistra.Managment.MasterCSCV.Dto.StateDto;
import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.repo.CountryRepo;
import Vaistra.Managment.MasterCSCV.repo.StateRepo;
import Vaistra.Managment.MasterCSCV.Service.StateService;
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
public class StateServiceImpl implements StateService {




    private final AppUtils appUtils;
    private final StateRepo stateRepo;
    private final CountryRepo countryRepo;
    public StateServiceImpl(AppUtils appUtils, StateRepo stateRepo, CountryRepo countryRepo) {
        this.appUtils = appUtils;
        this.stateRepo = stateRepo;
        this.countryRepo = countryRepo;

    }






    @Override
    public StateDto addState(StateDto stateDto) {
       Country country= countryRepo.findById(stateDto.getId())
                .orElseThrow(()->new ResourceNotFoundException("Country with id "+stateDto.getId()+" not found!" ));


        State state = new State();
        state.setStateName(stateDto.getStateName());
        state.setCountry(country);
        state.setStatus(true);
        return appUtils.StateToDto(stateRepo.save(state));


    }



    public StateDto getStateById (int id){
        return appUtils.StateToDto(stateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State with id '" + id + "' Not Found!")));
    }




    public HttpResponse getAllState(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<State> pageState = stateRepo.findAll(pageable);
        List<StateDto> states = appUtils.StatesToDtos(pageState.getContent());

        return HttpResponse.builder()
                .pageNumber(pageState.getNumber())
                .pageSize(pageState.getSize())
                .totalElements(pageState.getTotalElements())
                .totalPages(pageState.getTotalPages())
                .isLastPage(pageState.isLast())
                .data(states)
                .build();
    }

    @Override
    public StateDto updateState(StateDto stateDto, int id) {
        stateDto.setStateName(stateDto.getStateName().trim().toUpperCase());

        State state = stateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State with id '" + id + "' not found!"));

        if(stateRepo.existsByStateName(stateDto.getStateName()))
        {
            throw new DuplicateEntryException("State with name '"+stateDto.getStateName()+"' already exist!");
        }

        state.setStateName(stateDto.getStateName());
        return appUtils.StateToDto(stateRepo.save(state));
    }
    @Override
    public String deleteState(Integer stateId) {
        State state = stateRepo.findById(stateId).orElseThrow(()-> new ResourceNotFoundException("State not found with given id: " + stateId));
        stateRepo.deleteById(stateId);
        return "Record deleted successfully.";    }


    @Override
    public String uploadStateCSV(MultipartFile file) {

        if(!Objects.equals(file.getContentType(), "text/csv")){
            throw new IllegalArgumentException("Invalid file type. CSV file.");
        }

        try {
            List<State> states = CSVParser.parse(file.getInputStream(), Charset.defaultCharset(), CSVFormat.DEFAULT)
                    .stream().skip(1)
                    .map(record -> {
                        State state = new State();
                        state.setStateName(record.get(1).trim());
                        state.setStatus(Boolean.parseBoolean(record.get(2)));

                        String countryName = record.get(0).trim();

                        Country country = countryRepo.findByCountry(countryName);

                        if (country == null) {
                            country = new Country();
                            country.setCountry(countryName.trim());
                            country.setStatus(true);
                            countryRepo.save(country);
                        }

                        state.setCountry(country);
                        return state;
                    })
                    .toList();

            stateRepo.saveAll(states);

            return "CSV file uploaded successfully. records uploaded.";


        }catch (Exception e){
            return e.getMessage();
        }

    }
    @Override
    public HttpResponse searchStateByKeyword(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<State> pageState = stateRepo.findByStateNameContainingIgnoreCase(keyword,  pageable);

        List<StateDto> states = appUtils.StatesToDtos(pageState.getContent());

        return HttpResponse.builder()
                .pageNumber(pageState.getNumber())
                .pageSize(pageState.getSize())
                .totalElements(pageState.getTotalElements())
                .totalPages(pageState.getTotalPages())
                .isLastPage(pageState.isLast())
                .data(states)
                .build();
    }

}

