package Vaistra.Managment.MasterMines.Controller;


import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterMines.Dto.MineralDto;
import Vaistra.Managment.MasterMines.Service.MineralService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mineral")
public class MineralController {

    private final MineralService mineralService;

    public MineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    @PostMapping
    public ResponseEntity<MineralDto> addMineral(@Valid @RequestBody MineralDto mineral) {
        return new ResponseEntity<>(mineralService.addMineral(mineral), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<MineralDto> getMineralById(@PathVariable int id) {
        return new ResponseEntity<>(mineralService.getMineralById(id), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<MineralDto>> getAllMinerals(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(mineralService.getAllMineral(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<MineralDto> updateMineral(@RequestBody MineralDto mineral, @PathVariable int id) {
        return new ResponseEntity<>(mineralService.updateMineral(mineral, id), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMineralById(@PathVariable int id) {
        return new ResponseEntity<>(mineralService.deleteMineralById(id), HttpStatus.OK);
    }


}
