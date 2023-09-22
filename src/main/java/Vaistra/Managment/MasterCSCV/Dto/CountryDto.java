package Vaistra.Managment.MasterCSCV.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {


    private int id;
    @NotEmpty(message = "Country Should not be Empty!")
    @NotBlank(message = "Country Should not be Blank!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name should only contain alphabets.")
    @Size(min = 3, max = 250, message = "Country name should have a length between 3 and 30 characters.")
    private String country;
    private boolean status=true;


}
