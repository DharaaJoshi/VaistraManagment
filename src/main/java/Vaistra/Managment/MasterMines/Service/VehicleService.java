package Vaistra.Managment.MasterMines.Service;

import Vaistra.Managment.MasterMines.Dto.VehicleDto;
import Vaistra.Managment.MasterMines.Dto.VehicleDto;

import java.util.List;

public interface VehicleService {


    VehicleDto addVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicleById(int id);


    public VehicleDto updateVehicle(VehicleDto VehicleDto, int id);

    public String deleteVehicleById(int id);


    List<VehicleDto> getAllVehicle(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
