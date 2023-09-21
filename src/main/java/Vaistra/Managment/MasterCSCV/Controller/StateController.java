package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.StateDto;
import Vaistra.Managment.MasterCSCV.Service.StateService;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("state")
public class StateController {


        private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @PostMapping
    public ResponseEntity<StateDto> addState(@RequestBody StateDto stateDto)
    {
        return new ResponseEntity<>(stateService.addState(stateDto), HttpStatus.CREATED);
    }
    @GetMapping("all")
    public ResponseEntity <HttpResponse>getAllState(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "stateId", required = false) String sortBy,
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
    @PostMapping("/csv")
    public ResponseEntity<String> uploadStateCSV(@RequestParam MultipartFile file) throws IOException, java.io.IOException {
        return new ResponseEntity<>(stateService.uploadStateCSV(file),HttpStatus.OK);
    }
}
