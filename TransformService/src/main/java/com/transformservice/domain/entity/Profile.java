package com.transformservice.domain.entity;

import javax.persistence.*;
import java.util.List;

//todo: delete commented lines
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "profile")
    private Meter meter;

    @OneToMany(mappedBy = "profile")
//    @JoinColumn(name = "fraction_id")
    private List<Fraction> fractions;

    public Profile() {
        // empty constructor
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Meter getMeter() {
        return meter;
    }

    public List<Fraction> getFractions() {
        return fractions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public Profile(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.meter = builder.meter;
        this.fractions = builder.fractions;
    }

    public static class Builder {

        private Long id;

        private String name;

        private Meter meter;

        private List<Fraction> fractions;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder meter(Meter meter) {
            this.meter = meter;
            return this;
        }

        public Builder fractions(List<Fraction> fractions) {
            this.fractions = fractions;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }

    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meter=" + meter +
                ", fractions=" + fractions +
                '}';
    }
}
