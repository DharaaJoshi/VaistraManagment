package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Service.CountryService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;

    }

   @PostMapping
        public ResponseEntity<CountryDto> addCountry(@Valid @RequestBody CountryDto country) {
            return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
        }
    @GetMapping("all")
    public ResponseEntity <List<CountryDto>>getAllCountries(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                                            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

        return new ResponseEntity<>(countryService.getAllCountries(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }






    @PutMapping("{id}")
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto country, @PathVariable int id) {
        return new ResponseEntity<>(countryService.updateCountry(country, id), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable int id) {
        return new ResponseEntity<>(countryService.deleteCountryById(id), HttpStatus.OK);
    }
    @PostMapping("/csv")
    public ResponseEntity<String> uploadCountryCSV(@RequestParam MultipartFile file) throws IOException, java.io.IOException {
        return new ResponseEntity<>(countryService.uploadCountryCSV(file),HttpStatus.OK);
    }

}
