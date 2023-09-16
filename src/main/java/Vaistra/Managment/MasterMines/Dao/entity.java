package Vaistra.Managment.MasterMines.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Entity")
public class entity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "No")
    private Integer Id;
    @Column(name="Entity Type")
    private String entityType;
    @Column(name="Short Name")
    private String shortName;
}
