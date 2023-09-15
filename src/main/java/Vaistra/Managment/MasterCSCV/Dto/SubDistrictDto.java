package Vaistra.Managment.MasterCSCV.Dto;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubDistrictDto {
    private Integer subDistrictId;

    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "District name must contain only alphabets with at least 3 characters!")
    private String SubDistrictName;

    private boolean status;

    @Min(value = 1, message = "State ID must be a positive integer!")
    private Integer districtId;


    @JoinColumn(name = "District_name")
    private String DistrictnName;



}