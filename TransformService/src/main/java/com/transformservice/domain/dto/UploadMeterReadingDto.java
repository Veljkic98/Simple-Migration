package com.transformservice.domain.dto;

import com.opencsv.bean.CsvBindByName;
import com.transformservice.domain.entity.Profile;

public class UploadMeterReadingDto {

    @CsvBindByName(column = "meterID")
    private String meterIdentifier;

    @CsvBindByName
    private String profile;

    @CsvBindByName
    private String month;

    @CsvBindByName(column = "meter")
    private Integer meterReading;

    public UploadMeterReadingDto() {
        // empty constructor
    }

    public UploadMeterReadingDto(Builder builder) {
        this.meterIdentifier = builder.meterIdentifier;
        this.profile = builder.profile;
        this.month = builder.month;
        this.meterReading = builder.meterReading;
    }

    // get

    public String getMeterIdentifier() {
        return meterIdentifier;
    }

    public String getProfile() {
        return profile;
    }

    public String getMonth() {
        return month;
    }

    public Integer getMeterReading() {
        return meterReading;
    }

    // set

    public void setMeterIdentifier(String meterIdentifier) {
        this.meterIdentifier = meterIdentifier;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMeterReading(Integer meterReading) {
        this.meterReading = meterReading;
    }

    public static class Builder {

        private String meterIdentifier;

        private String profile;

        private String month;

        private Integer meterReading;

        public static Profile.Builder newInstance() {
            return new Profile.Builder();
        }

        public Builder setMeterIdentifier(String meterIdentifier) {
            this.meterIdentifier = meterIdentifier;
            return this;
        }

        public Builder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder month(String month) {
            this.month = month;
            return this;
        }

        public Builder meterReading(Integer meterReading) {
            this.meterReading = meterReading;
            return this;
        }

        public UploadMeterReadingDto build() {
            return new UploadMeterReadingDto(this);
        }

    }

    //todo delete
    @Override
    public String toString() {
        return "UploadMeterReadingDto{" +
                "meterIdentifier='" + meterIdentifier + '\'' +
                ", profile='" + profile + '\'' +
                ", month='" + month + '\'' +
                ", meterReading=" + meterReading +
                '}';
    }
}
