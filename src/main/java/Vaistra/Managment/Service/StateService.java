package Vaistra.Managment.Service;

import Vaistra.Managment.Dto.StateDto;
import Vaistra.Managment.Dto.UserDto;

import java.util.List;

public interface StateService {

   StateDto addState(StateDto stateDto);
    StateDto getStateById(int id);
    List<StateDto> getAllState(int pageNumber, int pageSize, String sortBy, String sortDirection);
    StateDto updateState(StateDto stateDto, int id);
}
