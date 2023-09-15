package Vaistra.Managment.MasterCSCV.Dao;

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
public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_id")
    private Integer villageId;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "status")
    private boolean status ;

    @ManyToOne
    @JoinColumn(name = "subDistrict_id")
    private SubDistrict sdistrict;

    @JoinColumn(name = "subdstrict_name")
    private String subdistrictname;

}
