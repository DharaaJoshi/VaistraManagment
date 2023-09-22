package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.StateDto;
import org.springframework.web.multipart.MultipartFile;

public interface StateService {

   StateDto addState(StateDto stateDto);
    StateDto getStateById(int id);
    HttpResponse getAllState(int pageNumber, int pageSize, String sortBy, String sortDirection);
    StateDto updateState(StateDto stateDto, int id);
    String deleteState(Integer stateId);
    String uploadStateCSV(MultipartFile file);
    HttpResponse searchStateByKeyword(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection);
}
