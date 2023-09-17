package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {

    EquipmentDto addEquipment(EquipmentDto equipmentDto);
    EquipmentDto getEquipmentById(int id);


    public EquipmentDto updateEquipment(EquipmentDto equipmentDto, int id);

    public String deleteEquipmentById(int id);


    List<EquipmentDto> getAllEquipment(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    
}
