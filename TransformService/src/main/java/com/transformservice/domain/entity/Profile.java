package com.transformservice.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "profile")
    private List<Meter> meters;

    @OneToMany(mappedBy = "profile")
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

    public List<Meter> getMeters() {
        return meters;
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

    public void setMeters(List<Meter> meters) {
        this.meters = meters;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public Profile(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.meters = builder.meters;
        this.fractions = builder.fractions;
    }

    public static class Builder {

        private Long id;

        private String name;

        private List<Meter> meters;

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

        public Builder meters(List<Meter> meters) {
            this.meters = meters;
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

}
