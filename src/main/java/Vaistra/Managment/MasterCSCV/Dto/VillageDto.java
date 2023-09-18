package Vaistra.Managment.MasterCSCV.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VillageDto {
    private Integer villageId;

    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "District name must contain only alphabets with at least 3 characters!")
    private String villageName;

    private boolean status;

    @NotNull(message = "Sub-district id shouldn't be null.")
    @Min(value=0,message = "Sub-district Id should be positive digits only.")
    private Integer subDistrictId;

    @Min(value=0,message = "District Id should be positive digits only.")
    private Integer districtId;

    @Min(value=0,message = "State Id should be positive digits only.")
    private Integer stateId;

    @Min(value=0,message = "Country Id should be positive digits only.")
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name should only contain alphabets and spaces.")
    private String country;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State name should only contain alphabets and spaces.")
    private String stateName;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "District name should only contain alphabets and spaces.")
    private String districtName;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "District name should only contain alphabets and spaces.")
    private String subDistrictName;



}
