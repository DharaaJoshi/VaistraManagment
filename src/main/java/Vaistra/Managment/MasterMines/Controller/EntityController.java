package Vaistra.Managment.MasterMines.Controller;

import Vaistra.Managment.MasterMines.Dto.EntityDto;
import Vaistra.Managment.MasterMines.Dto.MineralDto;
import Vaistra.Managment.MasterMines.Service.EntityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entity")
public class EntityController {

    private final EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }
    @PostMapping
    public ResponseEntity<EntityDto>addEntity(@Valid @RequestBody EntityDto entity){
        return new ResponseEntity<>(entityService.addEntity(entity), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<EntityDto> updateEntity(@RequestBody EntityDto entity, @PathVariable int id) {
        return new ResponseEntity<>(entityService.updateEntity(entity, id), HttpStatus.ACCEPTED);
    }
    @GetMapping("{id}")
    public ResponseEntity<EntityDto> getEntityById(@PathVariable int id) {
        return new ResponseEntity<>(entityService.getEntityById(id), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<EntityDto>> getAllEntity(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                           @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                           @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(entityService.getAllEntity(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
    @DeleteMapping("{d}")
    public ResponseEntity<String> deleteEntityById(@PathVariable int id) {
        return new ResponseEntity<>(entityService.deleteEntityById(id), HttpStatus.OK);
    }
}
