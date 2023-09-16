package Vaistra.Managment.MasterMines.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MineralDto {


    private Integer id;
    private String category;

    private String mineralName;
    private String atrName;
    private String hsnCode;
    private String grade;
}
