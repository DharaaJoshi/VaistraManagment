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
public class SubDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subDistrict_id")
    private Integer subDistrictId;

    @Column(name = "subdstrict_name")
    private String subDistrictName;

    @Column(name = "Status")
    private boolean Status;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}