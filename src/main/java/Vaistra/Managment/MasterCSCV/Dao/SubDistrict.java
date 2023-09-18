package Vaistra.Managment.MasterCSCV.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "stateId")
    private State state;
    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    @ManyToOne
    @JoinColumn(name = "id")
    private Country country;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "subDistrict")
    private List<Village> villages = new ArrayList<>();
}