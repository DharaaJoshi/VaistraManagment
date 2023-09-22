package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DistrictService {

    public DistrictDto addDistrict(DistrictDto DistrictDto);
    List<DistrictDto> getAllDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public DistrictDto updateDistrict(DistrictDto country, int id);
    public String deleteDistrictById(int id);

    String uploadDistrictCSV(MultipartFile file);
    HttpResponse searchDistrict(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection);
}
