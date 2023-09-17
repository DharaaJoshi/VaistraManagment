package Vaistra.Managment.MasterMines.Controller;

import Vaistra.Managment.MasterMines.Dto.VehicleDto;
import Vaistra.Managment.MasterMines.Service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
    
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping
    public ResponseEntity<VehicleDto> addVehicle(@Valid @RequestBody VehicleDto Vehicle)
    {
        return new ResponseEntity<>(vehicleService.addVehicle(Vehicle), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto entity, @PathVariable int id) {
        return new ResponseEntity<>(vehicleService.updateVehicle(entity, id), HttpStatus.ACCEPTED);
    }
    @GetMapping("{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable int id) {
        return new ResponseEntity<>(vehicleService.getVehicleById(id), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<VehicleDto>> getAllVehicle(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                                  @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                                  @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(vehicleService.getAllVehicle(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
    @DeleteMapping("{d}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable int id) {
        return new ResponseEntity<>(vehicleService.deleteVehicleById(id), HttpStatus.OK);
    }
}
