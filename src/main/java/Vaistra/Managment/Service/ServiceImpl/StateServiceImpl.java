package Vaistra.Managment.Service.ServiceImpl;

import Vaistra.Managment.Dao.Country;
import Vaistra.Managment.Dao.State;
import Vaistra.Managment.Dto.StateDto;
import Vaistra.Managment.Dto.UserDto;
import Vaistra.Managment.Exception.ResourceNotFoundException;
import Vaistra.Managment.Repository.CountryRepo;
import Vaistra.Managment.Repository.StateRepo;
import Vaistra.Managment.Service.StateService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
StateServiceImpl implements StateService {




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
       Country country= countryRepo.findById(stateDto.getCountry().getId())
                .orElseThrow(()->new ResourceNotFoundException("Country with id "+stateDto.getCountry()+" not found!" ));


        State state = new State();
        state.setState(stateDto.getState());

        state.setCountry(country);
        state.setStatus(true);

        return appUtils.StateToDto(stateRepo.save(state));


    }

    @Override
    public StateDto getStateById(int id) {
        return null;
    }

    @Override
    public List<StateDto> getAllState(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return null;
    }

    @Override
    public StateDto updateState(StateDto stateDto, int id) {
        return null;
    }
}
