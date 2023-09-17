package Vaistra.Managment.MasterMines.Controller;

import Vaistra.Managment.MasterMines.Dto.EquipmentDto;
import Vaistra.Managment.MasterMines.Service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipment")
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        
    }
    @PostMapping
    public ResponseEntity<EquipmentDto> addEquipment(@Valid @RequestBody EquipmentDto equipment)
    {
        return new ResponseEntity<>(equipmentService.addEquipment(equipment), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<EquipmentDto> updateEquipment(@RequestBody EquipmentDto entity, @PathVariable int id) {
        return new ResponseEntity<>(equipmentService.updateEquipment(entity, id), HttpStatus.ACCEPTED);
    }
    @GetMapping("{id}")
    public ResponseEntity<EquipmentDto> getEquipmentById(@PathVariable int id) {
        return new ResponseEntity<>(equipmentService.getEquipmentById(id), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<EquipmentDto>> getAllEquipment(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                          @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                          @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(equipmentService.getAllEquipment(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
    @DeleteMapping("{d}")
    public ResponseEntity<String> deleteEquipmentById(@PathVariable int id) {
        return new ResponseEntity<>(equipmentService.deleteEquipmentById(id), HttpStatus.OK);
    }
}

