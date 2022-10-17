package com.energyconsumption.domain.dto;

import java.util.List;

public class ProfileDto {

    private Long id;

    private String name;

    private List<MeterDto> meterDtos;

    private List<FractionDto> fractionDtos;

    public ProfileDto() {
        // empty constructor
    }

    public ProfileDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.meterDtos = builder.meterDtos;
        this.fractionDtos = builder.fractionDtos;
    }

    // get

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MeterDto> getMeterDtos() {
        return meterDtos;
    }

    public List<FractionDto> getFractionDtos() {
        return fractionDtos;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeterDtos(List<MeterDto> meterDtos) {
        this.meterDtos = meterDtos;
    }

    public void setFractionDtos(List<FractionDto> fractionDtos) {
        this.fractionDtos = fractionDtos;
    }

    public static class Builder {

        private Long id;

        private String name;

        private List<MeterDto> meterDtos;

        private List<FractionDto> fractionDtos;

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

        public Builder meterDtos(List<MeterDto> meterDtos) {
            this.meterDtos = meterDtos;
            return this;
        }

        public Builder fractionDtos(List<FractionDto> fractionDtos) {
            this.fractionDtos = fractionDtos;
            return this;
        }

        public ProfileDto build() {
            return new ProfileDto(this);
        }

    }

}
