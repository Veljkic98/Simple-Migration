package com.profileservice.domain.dto;

public class ConsumptionDto {

    private String monthFrom;

    private String monthTo;

    private Integer consumption;

    public ConsumptionDto() {
    }

    public String getMonthFrom() {
        return monthFrom;
    }

    public String getMonthTo() {
        return monthTo;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public ConsumptionDto(Builder builder) {
        this.monthFrom = builder.monthFrom;
        this.monthTo = builder.monthTo;
        this.consumption = builder.consumption;
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
