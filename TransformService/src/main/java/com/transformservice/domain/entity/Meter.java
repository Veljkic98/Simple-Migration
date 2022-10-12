package com.transformservice.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meter")
public class Meter {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meter_identifier", nullable = false, unique = true)
    private String meterIdentifier;

    @OneToMany(mappedBy = "meter", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reading> readings;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Meter() {
        // empty constructor
    }

    public Meter(Builder builder) {
        this.id = builder.id;
        this.profile = builder.profile;
        this.readings = builder.readings;
        this.meterIdentifier = builder.meterIdentifier;
    }

    // get

    public Long getId() {
        return id;
    }

    public String getMeterIdentifier() {
        return meterIdentifier;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public Profile getProfile() {
        return profile;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeterIdentifier(String meterIdentifier) {
        this.meterIdentifier = meterIdentifier;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static class Builder {

        private Long id;

        private String meterIdentifier;

        private List<Reading> readings;

        private Profile profile;

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

        public Builder readings(List<Reading> readings) {
            this.readings = readings;
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
//                ", readings=" + readings +
//                ", profile=" + profile +
                '}';
    }

}
