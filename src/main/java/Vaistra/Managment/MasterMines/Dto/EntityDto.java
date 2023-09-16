package Vaistra.Managment.MasterMines.Dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityDto {

    private Integer id;
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "entity name must contain only alphabets with at least 3 characters")
    private String entityType;
    private String  shortName;
}
