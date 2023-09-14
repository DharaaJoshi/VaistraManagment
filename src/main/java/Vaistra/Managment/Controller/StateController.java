package Vaistra.Managment.Controller;

import Vaistra.Managment.Dto.CountryDto;
import Vaistra.Managment.Dto.HttpResponse;
import Vaistra.Managment.Dto.StateDto;
import Vaistra.Managment.Service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("state")
public class StateController {


        private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @PostMapping
    public ResponseEntity<StateDto> addState( @RequestBody StateDto stateDto)
    {
        return new ResponseEntity<>(stateService.addState(stateDto), HttpStatus.CREATED);
    }
    @GetMapping("all")
    public ResponseEntity <HttpResponse>getAllState(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "state_id", required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(stateService.getAllState(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
//    @GetMapping("{state_id}")
//    public ResponseEntity <List<StateDto>>getStateById(@PathVariable int id){
//
//        return new ResponseEntity<>(stateService.getStateById(id), HttpStatus.OK);
//    }
    @PutMapping("{state_id}")
    public ResponseEntity<StateDto> updateState(@RequestBody StateDto state, @PathVariable int state_id) {
        return new ResponseEntity<>(stateService.updateState(state, state_id), HttpStatus.ACCEPTED);
    }
}
