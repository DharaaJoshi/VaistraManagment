package Vaistra.Managment.Controller;

import Vaistra.Managment.Dto.StateDto;
import Vaistra.Managment.Service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
