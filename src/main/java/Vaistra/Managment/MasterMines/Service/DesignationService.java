package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.DesignationDto;
import Vaistra.Managment.MasterMines.Dto.EntityDto;

import java.util.List;

public interface DesignationService {

    DesignationDto addDesignation(DesignationDto designationDto);
    DesignationDto getDesignationById(int id);


    public DesignationDto updateDesignation(DesignationDto designationDto, int id);

    public String deleteDesignationById(int id);


    List<DesignationDto> getAllDesignation(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
