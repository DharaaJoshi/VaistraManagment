package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.HttpResponse;
import Vaistra.Managment.MasterCSCV.Dto.SubDistrictDto;
import Vaistra.Managment.MasterCSCV.Service.SubDistrictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("SubDistrict")
public class SubDistrictController {


    private final SubDistrictService subDistrictService;
@Autowired
    public SubDistrictController(SubDistrictService subDistrictService) {
        this.subDistrictService = subDistrictService;
    }


    @PostMapping
    public ResponseEntity<SubDistrictDto> addSubDistrict(@Valid @RequestBody SubDistrictDto subdistrictDto)
    {
        return new ResponseEntity<>(subDistrictService.addSubDistrict(subdistrictDto), HttpStatus.CREATED);
    }




    @GetMapping("all")
    public ResponseEntity<List<SubDistrictDto>> getAllDistricts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = "subDistrictId", required = false) String sortBy,
                                                             @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection)
    {
        return new ResponseEntity<>(subDistrictService.getAllSubDistricts(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.FOUND);
    }


    @PutMapping("{subDistrictId}")
    public ResponseEntity<SubDistrictDto> updateSubDistrict(@Valid @RequestBody SubDistrictDto subdistrictDto, @PathVariable int subDistrictId)
    {
        return new ResponseEntity<>(subDistrictService.updateSubDistrict(subdistrictDto, subDistrictId), HttpStatus.OK);
    }

    @DeleteMapping("{subDistrictId}")
    public ResponseEntity<String> DeleteDistrictById(@PathVariable int subDistrictId)
    {
        return new ResponseEntity<>(subDistrictService.deleteSubDistrictById(subDistrictId), HttpStatus.OK);
    }
    @GetMapping("search")
    public ResponseEntity<HttpResponse> searchByKeyword(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "subDistrictId", required = false) String sortBy,
                                                        @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection)
    {
        return new ResponseEntity<>(subDistrictService.searchSubDistrict(keyword, pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }
}
