package Vaistra.Managment.MasterMines.Service.ServiceImpl;

import Vaistra.Managment.MasterCSCV.Exception.DuplicateEntryException;
import Vaistra.Managment.MasterCSCV.Exception.ResourceNotFoundException;
import Vaistra.Managment.MasterMines.Dao.Vehicle;
import Vaistra.Managment.MasterMines.Dao.Vehicle;
import Vaistra.Managment.MasterMines.Dao.Vehicle;
import Vaistra.Managment.MasterMines.Dto.VehicleDto;
import Vaistra.Managment.MasterMines.Repository.VehicleRepo;
import Vaistra.Managment.MasterMines.Service.VehicleService;
import Vaistra.Managment.Utils.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    public VehicleServiceImpl(VehicleRepo vehicleRepo, AppUtils appUtils) {
        this.vehicleRepo = vehicleRepo;
        this.appUtils = appUtils;
    }

    private final VehicleRepo vehicleRepo;
    private final AppUtils appUtils;
    @Override
    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        if(vehicleRepo.existsByVehicle(vehicleDto.getVehicle()))
            throw new DuplicateEntryException("Vehicle with type"+vehicleDto.getVehicle()+"already exist.");
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicle(vehicleDto.getVehicle());

        return appUtils.vehicleToDto(vehicleRepo.save(vehicle));
    }

    @Override
    public VehicleDto getVehicleById(int id) {
        return appUtils.vehicleToDto(vehicleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id '" + id + "' Not Found!")));
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto, int id) {
        Vehicle vehicle= vehicleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with Id '" + id + "' not found!"));


        // HANDLE DUPLICATE ENTRY EXCEPTION
        Vehicle existedVehicle = vehicleRepo.findByVehicle(vehicleDto.getVehicle());
        if(existedVehicle != null)
            throw new DuplicateEntryException("Vehicle with name '"+vehicleDto.getVehicle()+"' already exist!");

        vehicle.setVehicle(vehicleDto.getVehicle().toUpperCase());



        return appUtils.vehicleToDto(vehicleRepo.save(vehicle));
    }

    @Override
    public String deleteVehicleById(int id) {
        vehicleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle with Id '" + id + "' not found!"));

        vehicleRepo.deleteById(id);
        return "Vehicle with Id '" + id + "' deleted";
    }

    @Override
    public List<VehicleDto> getAllVehicle(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Vehicle> pageVehicle = vehicleRepo.findAll(pageable);
        return appUtils.vehicleToDtos(pageVehicle.getContent());
    }
}
