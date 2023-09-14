package Vaistra.Managment.Service;

import Vaistra.Managment.Dto.DistrictDto;

import java.util.List;

public interface DistrictService {

    public DistrictDto addDistrict(DistrictDto DistrictDto);
    public DistrictDto getDistrictById(int id);


    List<DistrictDto> getAllDistricts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    List<DistrictDto> getAllDistrictsByActiveState(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public DistrictDto updateDistrict(DistrictDto country, int id);
    public String deleteDistrictById(int id);
//    public String softDeleteCountryById(int id);
//    public String restoreCountryById(int id);



    List<DistrictDto> getDistrictsByStateId(int stateId);

    //List<DistrictDto> getDistrictsByCountryId(int countryId);
}
