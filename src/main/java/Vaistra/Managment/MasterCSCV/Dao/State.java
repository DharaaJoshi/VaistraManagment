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
@Table(name="state")

public class State {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "state_id")
        private Integer stateId;

        @Column(name = "state_name")
        private String stateName;

        @Column(name = "status")
        private boolean status = true;


        @ManyToOne
        @JoinColumn(name = "id")
        private Country country;

        @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "state")
        private List<District> districts = new ArrayList<>();

        @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "state")
        private List<SubDistrict> subDistricts = new ArrayList<>();

        @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "state")
        private List<Village> villages = new ArrayList<>();

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "state")
        private List<BankBranch> branches = new ArrayList<>();




}
