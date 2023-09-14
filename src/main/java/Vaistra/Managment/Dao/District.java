package Vaistra.Managment.Dao;

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
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "status")
    private boolean status ;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @JoinColumn(name="state")
    private String StateName;

//    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SubDistrict> subDistricts = new ArrayList<>();

}