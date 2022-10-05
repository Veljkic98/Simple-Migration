package com.dummydata.model;

import com.opencsv.bean.CsvBindByName;

public class Profile {

    @CsvBindByName(column = "Month")
    private String month;

    @CsvBindByName(column = "Profile")
    private String profile;

    @CsvBindByName(column = "Fraction")
    private double fraction;

    public Profile(Builder builder) {
        this.month = builder.month;
        this.profile = builder.profile;
        this.fraction = builder.fraction;
    }

    public String getMonth() {
        return month;
    }

    public String getProfile() {
        return profile;
    }

    public double getFraction() {
        return fraction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String month;
        private String profile;
        private double fraction;

        private Builder() {}

        public Builder setMonth(String month) {
            this.month = month;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder setFraction(double fraction) {
            this.fraction = fraction;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }

    }

    @Override
    public String toString() {
        return "Profile{" +
                "month='" + month + '\'' +
                ", profile='" + profile + '\'' +
                ", fraction=" + fraction +
                '}';
    }

}
