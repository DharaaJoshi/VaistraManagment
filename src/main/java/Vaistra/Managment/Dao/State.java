package Vaistra.Managment.Dao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @ManyToOne(cascade = CascadeType.ALL, targetEntity = Country.class)
        @JoinColumn(name="country")

        private Country country;
        private String state;
        private boolean status;

}
