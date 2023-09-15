package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.StateDto;

public interface StateService {

   StateDto addState(StateDto stateDto);
    StateDto getStateById(int id);
    HttpResponse getAllState(int pageNumber, int pageSize, String sortBy, String sortDirection);
    StateDto updateState(StateDto stateDto, int id);

}
