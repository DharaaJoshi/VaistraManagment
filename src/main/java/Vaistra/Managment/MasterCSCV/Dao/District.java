package Vaistra.Managment.MasterCSCV.Dao;

import Vaistra.Managment.ManageBank.Dao.BankBranch;
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
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "status")
    private boolean status ;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "district")
    private List<SubDistrict> subDistricts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "district")
    private List<Village> villages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "district")
    private List<BankBranch> branches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "stateId")
    private State state;

    @ManyToOne
    @JoinColumn(name = "id")
    private Country country;


}