package com.profileservice.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "profile", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "\"month\"" }) })
public class Profile {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "\"month\"", nullable = false)
    private String month;

    @Column(nullable = false)
    private Double fraction;

    @Column(nullable = false)
    private Integer meterReading;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public Double getFraction() {
        return fraction;
    }

    public Integer getMeterReading() {
        return meterReading;
    }

}
