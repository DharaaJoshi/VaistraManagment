package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubDistrictService {




        public SubDistrictDto addSubDistrict(SubDistrictDto subDistrictDto);
        public SubDistrictDto getSubDistrictById(int id);


        List<SubDistrictDto> getAllSubDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    HttpResponse searchSubDistrict(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection);


        public SubDistrictDto updateSubDistrict(SubDistrictDto subDistrict, int id);
        public String deleteSubDistrictById(int id);







    }


