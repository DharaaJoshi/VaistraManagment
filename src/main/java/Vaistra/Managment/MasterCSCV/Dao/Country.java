package Vaistra.Managment.MasterCSCV.Dao;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country")

public class Country
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")

    private Integer id;
    @Column(name = "country_name")
    private String country;


    @Column(name = "status")
    private boolean status;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "country")
    private List<State> states = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private List<District> districts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private List<SubDistrict> subDistricts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private List<Village> villages = new ArrayList<>();


}







//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Table(name="country")
//public class Country {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column (name="No")
//    private int id;
//    @Column(name="COUNTRY")
//    private String country;
//    @Column(name="STATUS")
//    private boolean status=true;
//    @Column(name="DELETED")
//    private boolean deleted=false;
//
//     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//             @JoinTable(
//                     name="state_country",
//                     joinColumns = @JoinColumn(name = "no"),
//                     inverseJoinColumns = @JoinColumn(name="id")
//
//
//             )
//    List<State>states=new ArrayList<>();
//
//    }
//


