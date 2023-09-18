package Vaistra.Managment.ManageBank.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NO")
    private Integer id;
    @Column(name="Bank Long Name")
    private String bankName;
    @Column(name="Bank short Name")
    private String bankShortName;

    @Column(name="Bank Logo")
    private byte[] logo;
    @Column(name = "is active")
    private Boolean isActive;
}
