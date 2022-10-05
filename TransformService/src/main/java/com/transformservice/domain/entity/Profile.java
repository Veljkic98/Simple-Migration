package com.transformservice.domain.entity;

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
        // empty constructor
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }

    public void setMeterReading(Integer meterReading) {
        this.meterReading = meterReading;
    }

    public Profile(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.month = builder.month;
        this.fraction = builder.fraction;
        this.meterReading = builder.meterReading;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String month;
        private Double fraction;
        private Integer meterReading;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMonth(String month) {
            this.month = month;
            return this;
        }

        public Builder setFraction(Double fraction) {
            this.fraction = fraction;
            return this;
        }

        public Builder setMeterReading(Integer meterReading) {
            this.meterReading = meterReading;
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
                ", month='" + month + '\'' +
                ", fraction=" + fraction +
                ", meterReading=" + meterReading +
                '}';
    }
}
