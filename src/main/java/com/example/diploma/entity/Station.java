package com.example.diploma.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "station")
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String esr;
    @Column
    private String name;
    @Column
    private String st_id;
    @Column
    private String nod_id;
}
