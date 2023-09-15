package Vaistra.Managment.MasterCSCV.Controller;


import Vaistra.Managment.MasterCSCV.Dto.VillageDto;
import Vaistra.Managment.MasterCSCV.Service.VillageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("village")
public class VillageController {
    private final VillageService villageService;
    @Autowired

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }




    @PostMapping
    public ResponseEntity<VillageDto> addVillage(@Valid @RequestBody VillageDto villageDto)
    {
        return new ResponseEntity<>(villageService.addVillage(villageDto), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity <List<VillageDto>>getAllVillage(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy", defaultValue = "villageId", required = false) String sortBy,
                                                            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(villageService.getAllVillage(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

}
