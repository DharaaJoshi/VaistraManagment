package Vaistra.Managment.MasterMines.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class Mineral {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="No")
    private Integer id;
    @Column(name="Category")
    private String category;
    @Column(name="Mineral Name")
    private String mineralName;
    @Column(name="ATR Name")
    private String atrName;
    @Column(name="HSN Code")
    private String hsnCode;
    @Column(name="Grade")
    private String grade;
}
