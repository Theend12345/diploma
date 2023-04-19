package com.example.diploma.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "act2")
@NoArgsConstructor
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String limit;
    @Column
    private String commit;
    @Column
    private String date;
    @Column
    private String defect;
    @Column
    private String station;
    @Column
    private String element;
    @Column
    private Integer kmo;
    @Column
    private String object;
    @Column
    private String resolve_date;
    @Column
    private int status;
    @Column
    private String value;
}
