package com.energyconsumption.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "reading", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"month", "meter_id"})
})
public class Reading {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`month`", nullable = false)
    private String month;

    @Column(name = "`value`", nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "meter_id", nullable = false)
    private Meter meter;

    public Reading() {
        // empty constructor
    }

    public Reading(Builder builder) {
        this.id = builder.id;
        this.month = builder.month;
        this.value = builder.value;
        this.meter = builder.meter;
    }

    // get

    public Long getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public Integer getValue() {
        return value;
    }

    public Meter getMeter() {
        return meter;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public static class Builder {

        private Long id;

        private String month;

        private Integer value;

        private Meter meter;

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

        public Builder value(Integer value) {
            this.value = value;
            return this;
        }

        public Builder meter(Meter meter) {
            this.meter = meter;
            return this;
        }

        public Reading build() {
            return new Reading(this);
        }

    }

}
