package com.transformservice.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meter")
//todo: staviti da profile.name i month budu unique
public class Meter {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meter_identifier", nullable = false)
    private String meterIdentifier;

    @OneToOne
    @JoinColumn(name = "profile", nullable = false)
    private Profile profile;

    public Meter() {
        // empty constructor
    }

    public Meter(Builder builder) {
        this.id = builder.id;
        this.profile = builder.profile;
        this.meterIdentifier = builder.meterIdentifier;
    }

    // get

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getMeterIdentifier() {
        return meterIdentifier;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setMeterIdentifier(String meterIdentifier) {
        this.meterIdentifier = meterIdentifier;
    }

    public static class Builder {

        private Long id;

        private Profile profile;

        private String meterIdentifier;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder meterIdentifier(String meterIdentifier) {
            this.meterIdentifier = meterIdentifier;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Meter build() {
            return new Meter(this);
        }

    }

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", meterIdentifier='" + meterIdentifier + '\'' +
                ", profile=" + profile +
                '}';
    }
}
