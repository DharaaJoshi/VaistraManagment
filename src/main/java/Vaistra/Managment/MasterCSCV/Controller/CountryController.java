package Vaistra.Managment.MasterCSCV.Controller;

import Vaistra.Managment.MasterCSCV.Dto.CountryDto;
import Vaistra.Managment.MasterCSCV.Service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @PutMapping("softDelete/{id}")
//    public ResponseEntity<String> softDeleteById(@PathVariable int id) {
//        return new ResponseEntity<>(countryService.softDeleteCountryById(id), HttpStatus.OK);
//    }
//    @PutMapping("restore/{id}")
//    public ResponseEntity<String> restoreCountryById(@PathVariable int id) {
//        return new ResponseEntity<>(countryService.restoreCountryById(id), HttpStatus.OK);
//    }

}
