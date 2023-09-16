package Vaistra.Managment.MasterMines.Controller;

import Vaistra.Managment.MasterMines.Dto.DesignationDto;
import Vaistra.Managment.MasterMines.Dto.DesignationDto;
import Vaistra.Managment.MasterMines.Service.DesignationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("designation")
public class DesignationController {
    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }
    @PostMapping
    public ResponseEntity<DesignationDto>addDesignation(@Valid@RequestBody DesignationDto designation)
    {
        return new ResponseEntity<>(designationService.addDesignation(designation), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<DesignationDto> updateDesignation(@RequestBody DesignationDto entity, @PathVariable int id) {
        return new ResponseEntity<>(designationService.updateDesignation(entity, id), HttpStatus.ACCEPTED);
    }
    @GetMapping("{id}")
    public ResponseEntity<DesignationDto> getDesignationById(@PathVariable int id) {
        return new ResponseEntity<>(designationService.getDesignationById(id), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<DesignationDto>> getAllDesignation(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                        @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(designationService.getAllDesignation(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
    @DeleteMapping("{d}")
    public ResponseEntity<String> deleteDesignationById(@PathVariable int id) {
        return new ResponseEntity<>(designationService.deleteDesignationById(id), HttpStatus.OK);
    }
}
