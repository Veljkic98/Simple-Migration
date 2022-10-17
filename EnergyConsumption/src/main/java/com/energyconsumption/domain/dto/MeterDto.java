package com.energyconsumption.domain.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class MeterDto {

    private Long id;

    @NotBlank
    private String meterIdentifier;

    private List<ReadingDto> readingDtos;

    public MeterDto() {
        // empty constructor
    }

    public MeterDto(Builder builder) {
        this.id = builder.id;
        this.meterIdentifier = builder.meterIdentifier;
        this.readingDtos = builder.readingDtos;
    }

    // get

    public Long getId() {
        return id;
    }

    public String getMeterIdentifier() {
        return meterIdentifier;
    }

    public List<ReadingDto> getReadingDtos() {
        return readingDtos;
    }

    // set

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeterIdentifier(String meterIdentifier) {
        this.meterIdentifier = meterIdentifier;
    }

    public void setReadingDtos(List<ReadingDto> readingDtos) {
        this.readingDtos = readingDtos;
    }

    public static class Builder {

        private Long id;

        private String meterIdentifier;

        private List<ReadingDto> readingDtos;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder meterIdentifier(String meterIdentifier) {
            this.meterIdentifier = meterIdentifier;
            return this;
        }

        public Builder readingDtos(List<ReadingDto> readingDtos) {
            this.readingDtos = readingDtos;
            return this;
        }

        public MeterDto build() {
            return new MeterDto(this);
        }

    }

}
