package com.transformservice.domain.dto;

import javax.validation.constraints.NotBlank;

public class ConsumptionDto {

    @NotBlank
    private String monthFrom;

    @NotBlank
    private String monthTo;

    private Integer consumption;

    public ConsumptionDto() {
        // empty constructor
    }

    public ConsumptionDto(Builder builder) {
        this.monthFrom = builder.monthFrom;
        this.monthTo = builder.monthTo;
        this.consumption = builder.consumption;
    }

    // get

    public String getMonthFrom() {
        return monthFrom;
    }

    public String getMonthTo() {
        return monthTo;
    }

    public Integer getConsumption() {
        return consumption;
    }

    // set


    public void setMonthFrom(String monthFrom) {
        this.monthFrom = monthFrom;
    }

    public void setMonthTo(String monthTo) {
        this.monthTo = monthTo;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public static class Builder {

        private String monthFrom;

        private String monthTo;

        private Integer consumption;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder monthFrom(String monthFrom) {
            this.monthFrom = monthFrom;
            return this;
        }

        public Builder monthTo(String monthTo) {
            this.monthTo = monthTo;
            return this;
        }

        public Builder consumption(Integer consumption) {
            this.consumption = consumption;
            return this;
        }

        public ConsumptionDto build() {
            return new ConsumptionDto(this);
        }

    }

}
