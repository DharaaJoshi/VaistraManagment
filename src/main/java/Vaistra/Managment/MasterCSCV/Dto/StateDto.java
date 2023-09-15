package Vaistra.Managment.MasterCSCV.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer state_id;
    private String stateName;
    private boolean status;
    private Integer id;

}
