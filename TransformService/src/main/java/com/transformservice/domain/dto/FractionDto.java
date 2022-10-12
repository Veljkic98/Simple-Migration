package com.transformservice.domain.dto;

import javax.validation.constraints.NotBlank;

public class FractionDto {

    private Long id;

    @NotBlank
    private String month;

    @NotBlank
    private Double value;

    public FractionDto() {
        // empty constructor
    }

    public FractionDto(Builder builder) {
        this.id = builder.id;
        this.month = builder.month;
        this.value = builder.value;
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

    public static class Builder {

        private Long id;

        private String month;

        private Double value;

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

        public FractionDto build() {
            return new FractionDto(this);
        }

    }

}
