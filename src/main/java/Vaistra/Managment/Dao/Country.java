package Vaistra.Managment.Dao;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="No")
    private int id;
    @Column(name="COUNTRY")
    private String country;
    @Column(name="STATUS")
    private boolean status=true;
    @Column(name="DELETED")
    private boolean deleted=false;



    }



