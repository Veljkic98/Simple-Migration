package com.transformservice.domain.entity;

import javax.persistence.*;

@Entity
// todo TEST THIS Constraints (profile or profile_id)
@Table(name = "fraction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"month", "profile_id"})
})
public class Fraction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`month`", nullable = false)
    private String month;

    @Column(name = "`value`", nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Fraction() {
        // empty constructor
    }

    public Fraction(Builder builder) {
        this.id = builder.id;
        this.month = builder.month;
        this.value = builder.value;
        this.profile = builder.profile;
    }

    // get

    public Long getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public Double getValue() {
        return value;
    }

    public Profile getProfile() {
        return profile;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static class Builder {

        private Long id;

        private String month;

        private Double value;

        private Profile profile;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder month(String month) {
            this.month = month;
            return this;
        }

        public Builder value(Double value) {
            this.value = value;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Fraction build() {
            return new Fraction(this);
        }

    }

    @Override
    public String toString() {
        return "Fraction{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", value=" + value +
                ", profile=" + profile +
                '}';
    }
}
