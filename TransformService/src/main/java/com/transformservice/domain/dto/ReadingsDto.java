package com.transformservice.domain.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.stream.Stream;

public class ReadingsDto {

    @NotNull
    private Integer janReading;

    @NotNull
    private Integer febReading;

    @NotNull
    private Integer marReading;

    @NotNull
    private Integer aprReading;

    @NotNull
    private Integer mayReading;

    @NotNull
    private Integer junReading;

    @NotNull
    private Integer julReading;

    @NotNull
    private Integer avgReading;

    @NotNull
    private Integer sepReading;

    @NotNull
    private Integer octReading;

    @NotNull
    private Integer novReading;

    @NotNull
    private Integer decReading;

    public ReadingsDto() {
        // empty constructor
    }

    // get

    public Integer getJanReading() {
        return janReading;
    }

    public Integer getFebReading() {
        return febReading;
    }

    public Integer getMarReading() {
        return marReading;
    }

    public Integer getAprReading() {
        return aprReading;
    }

    public Integer getMayReading() {
        return mayReading;
    }

    public Integer getJunReading() {
        return junReading;
    }

    public Integer getJulReading() {
        return julReading;
    }

    public Integer getAvgReading() {
        return avgReading;
    }

    public Integer getSepReading() {
        return sepReading;
    }

    public Integer getOctReading() {
        return octReading;
    }

    public Integer getNovReading() {
        return novReading;
    }

    public Integer getDecReading() {
        return decReading;
    }

    // set

    public void setJanReading(Integer janReading) {
        this.janReading = janReading;
    }

    public void setFebReading(Integer febReading) {
        this.febReading = febReading;
    }

    public void setMarReading(Integer marReading) {
        this.marReading = marReading;
    }

    public void setAprReading(Integer aprReading) {
        this.aprReading = aprReading;
    }

    public void setMayReading(Integer mayReading) {
        this.mayReading = mayReading;
    }

    public void setJunReading(Integer junReading) {
        this.junReading = junReading;
    }

    public void setJulReading(Integer julReading) {
        this.julReading = julReading;
    }

    public void setAvgReading(Integer avgReading) {
        this.avgReading = avgReading;
    }

    public void setSepReading(Integer sepReading) {
        this.sepReading = sepReading;
    }

    public void setOctReading(Integer octReading) {
        this.octReading = octReading;
    }

    public void setNovReading(Integer novReading) {
        this.novReading = novReading;
    }

    public void setDecReading(Integer decReading) {
        this.decReading = decReading;
    }

    public boolean isAnyFieldNull() {
        return Stream.of(decReading, decReading, decReading, decReading, decReading, decReading,
                        decReading, decReading, decReading, decReading, decReading, decReading)
                .anyMatch(Objects::isNull);
    }

}
