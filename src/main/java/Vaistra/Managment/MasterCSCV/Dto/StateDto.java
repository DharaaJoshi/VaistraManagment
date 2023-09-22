package Vaistra.Managment.MasterCSCV.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {

    @NotEmpty(message = "State name shouldn't be empty.")
    @NotNull(message = "State name shouldn't be null.")
    @NotBlank(message = "State name shouldn't be blank.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State name should only contain alphabets and spaces.")
    @Size(min = 3, max = 250, message = "State name should have a length between 3 and 30 characters.")
    private String stateName;

    private Boolean status;

    @NotNull(message = "Country Id shouldn't be null.")
    private Integer Id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name should only contain alphabets and spaces.")
    private String country;

}
