package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DistrictService {

    public DistrictDto addDistrict(DistrictDto DistrictDto);


    List<DistrictDto> getAllDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    List<DistrictDto> getAllDistrictsByActiveState(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public DistrictDto updateDistrict(DistrictDto country, int id);
    public String deleteDistrictById(int id);



    List<DistrictDto> getDistrictsByStateId(int stateId);


    HttpResponse getDistrict(int pageNo, int pageSize, String sortBy, String sortDirection);

    HttpResponse getDistrictByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);

    String uploadDistrictCSV(MultipartFile file);
}
