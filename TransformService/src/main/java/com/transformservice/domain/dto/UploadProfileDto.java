package com.transformservice.domain.dto;

import com.transformservice.domain.entity.Profile;

public class UploadProfileDto {

    private String profile;

    private String month;

    private Double fraction;

    public UploadProfileDto() {
        // empty constructor
    }

    public UploadProfileDto(Builder builder) {
        this.profile = builder.profile;
        this.month = builder.month;
        this.fraction = builder.fraction;
    }

    // get

    public String getProfile() {
        return profile;
    }

    public String getMonth() {
        return month;
    }

    public Double getFraction() {
        return fraction;
    }

    // set


    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }

    public static class Builder {

        private String profile;

        private String month;

        private Double fraction;

        public static Profile.Builder newInstance() {
            return new Profile.Builder();
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
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

        public UploadProfileDto build() {
            return new UploadProfileDto(this);
        }

    }

}
