package com.dummydata.model;

public class MeterReading {

    private String meterID;
    private String profile;
    private String month;
    private String meter;

    public MeterReading(Builder builder) {
        this.meterID = builder.meterID;
        this.profile = builder.profile;
        this.month = builder.month;
        this.meter = builder.meter;
    }

    public String getMeterID() {
        return meterID;
    }

    public String getProfile() {
        return profile;
    }

    public String getMonth() {
        return month;
    }

    public String getMeter() {
        return meter;
    }

    public static class Builder {

        private String meterID;
        private String profile;
        private String month;
        private String meter;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {}

        public Builder setMeterID(String meterID) {
            this.meterID = meterID;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder setMonth(String month) {
            this.month = month;
            return this;
        }

        public Builder setMeter(String meter) {
            this.meter = meter;
            return this;
        }

        public MeterReading build() {
            return new MeterReading(this);
        }

    }

}
