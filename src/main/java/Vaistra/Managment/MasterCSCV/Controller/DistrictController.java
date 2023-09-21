package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.DistrictDto;
import Vaistra.Managment.MasterCSCV.Service.DistrictService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }


    @PostMapping
    public ResponseEntity<DistrictDto> addDistrict(@Valid @RequestBody DistrictDto districtDto) {
        return new ResponseEntity<>(districtService.addDistrict(districtDto), HttpStatus.CREATED);
    }


    @GetMapping("all")
    public ResponseEntity<List<DistrictDto>> getAllDistricts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = "districtId", required = false) String sortBy,
                                                             @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
        return new ResponseEntity<>(districtService.getAllDistricts(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.FOUND);
    }


    @GetMapping
    public ResponseEntity<List<DistrictDto>> getAllDistrictsByActive(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                                     @RequestParam(value = "sortBy", defaultValue = "districtId", required = false) String sortBy,
                                                                     @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
        return new ResponseEntity<>(districtService.getAllDistrictsByActiveState(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.FOUND);
    }


    @GetMapping("stateId/{stateId}")
    public ResponseEntity<List<DistrictDto>> getDistrictByStateId(@PathVariable int stateId) {
        return new ResponseEntity<>(districtService.getDistrictsByStateId(stateId), HttpStatus.FOUND);
    }


//    @GetMapping("countryId/{countryId}")
//    public ResponseEntity<List<DistrictDto>> getDistrictByCountryId(@PathVariable int countryId)
//    {
//        return new ResponseEntity<>(districtService.getDistrictsByCountryId(countryId), HttpStatus.FOUND);
//    }


    @PutMapping("{districtId}")
    public ResponseEntity<DistrictDto> updateDistrict(@Valid @RequestBody DistrictDto districtDto, @PathVariable int districtId) {
        return new ResponseEntity<>(districtService.updateDistrict(districtDto, districtId), HttpStatus.OK);
    }


    @DeleteMapping("{districtId}")
    public ResponseEntity<String> hardDeleteDistrictById(@PathVariable int districtId) {
        return new ResponseEntity<>(districtService.deleteDistrictById(districtId), HttpStatus.OK);
    }


//
//    @GetMapping("search")
//    public ResponseEntity<HttpResponse> searchByKeyword(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
//                                                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
//                                                        @RequestParam(value = "sortBy", defaultValue = "districtId", required = false) String sortBy,
//                                                        @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection)
//    {
//        return new ResponseEntity<>(districtService.searchDistrict(keyword, pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
//    }

    @PostMapping("/csv")
    public ResponseEntity<String> uploadDistrictCSV(@RequestParam MultipartFile file) throws IOException, java.io.IOException {
        return new ResponseEntity<>(districtService.uploadDistrictCSV(file), HttpStatus.OK);
    }
}

