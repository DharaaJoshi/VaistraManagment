package Vaistra.Managment.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @Size(min = 3, message = "Country name should be at least 3 characters!")
    private String country;
    private boolean status=true;
    private boolean deleted=false;

}
