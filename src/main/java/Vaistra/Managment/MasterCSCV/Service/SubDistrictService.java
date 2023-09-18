package Vaistra.Managment.MasterCSCV.Service;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;

import java.util.List;

public interface SubDistrictService {




        public SubDistrictDto addSubDistrict(SubDistrictDto subDistrictDto);
        public SubDistrictDto getSubDistrictById(int id);


        List<SubDistrictDto> getAllSubDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection);

        List<SubDistrictDto> getAllSubDistrictsByActiveState(int pageNumber, int pageSize, String sortBy, String sortDirection);

        public SubDistrictDto updateSubDistrict(SubDistrictDto subDistrict, int id);
        public String deleteSubDistrictById(int id);
//    public String softDeleteCountryById(int id);
//    public String restoreCountryById(int id);



        List<DistrictDto> getDistrictsByStateId(int stateId);

    HttpResponse getSubDistrict(int pageNo, int pageSize, String sortBy, String sortDirection);

    HttpResponse getSubDistrictByKeyword(int pageNo, int pageSize, String sortBy, String sortDirection, String keyword);

    //List<DistrictDto> getDistrictsByCountryId(int countryId);
    }


