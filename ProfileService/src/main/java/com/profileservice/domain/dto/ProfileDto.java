package com.profileservice.domain.dto;

import com.profileservice.domain.entity.Profile;

public class ProfileDto {

    private Long id;

    private String name;

    private String month;

    private Double fraction;

    private Integer meterReading;

    public ProfileDto() {
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

    public ProfileDto(Builder builder) {
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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder month(String month) {
            this.month = month;
            return this;
        }

        public Builder fraction(Double fraction) {
            this.fraction = fraction;
            return this;
        }

        public Builder meterReading(Integer meterReading) {
            this.meterReading = meterReading;
            return this;
        }

        public ProfileDto build() {
            return new ProfileDto(this);
        }
    }

    public static ProfileDto fromEntity(Profile profile) {
        return Builder.newInstance()
                .id(profile.getId())
                .fraction(profile.getFraction())
                .meterReading(profile.getMeterReading())
                .name(profile.getName())
                .month(profile.getMonth())
                .build();
    }

}
