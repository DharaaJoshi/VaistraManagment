package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.DesignationDto;

import java.util.List;

public interface VehicleService {


    DesignationDto addDesignation(DesignationDto designationDto);
    DesignationDto getDesignationById(int id);


    public DesignationDto updateDesignation(DesignationDto designationDto, int id);

    public String deleteDesignationById(int id);


    List<DesignationDto> getAllDesignation(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
