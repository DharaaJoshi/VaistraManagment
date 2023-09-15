package Vaistra.Managment.MasterMines.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class MineralDto {


    private Integer id;
    private String category;
    private String mineralName;
    private String atrName;
    private String hsnCode;
    private String grade;
}
