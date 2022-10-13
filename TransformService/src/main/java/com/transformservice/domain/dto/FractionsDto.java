package com.transformservice.domain.dto;

import javax.validation.constraints.NotNull;

public class FractionsDto {

    @NotNull
    private Double janFraction;

    @NotNull
    private Double febFraction;

    @NotNull
    private Double marFraction;

    @NotNull
    private Double aprFraction;

    @NotNull
    private Double mayFraction;

    @NotNull
    private Double junFraction;

    @NotNull
    private Double julFraction;

    @NotNull
    private Double avgFraction;

    @NotNull
    private Double sepFraction;

    @NotNull
    private Double octFraction;

    @NotNull
    private Double novFraction;

    @NotNull
    private Double decFraction;

    public FractionsDto() {
        // empty constructor
    }

    // get

    public Double getJanFraction() {
        return janFraction;
    }

    public Double getFebFraction() {
        return febFraction;
    }

    public Double getMarFraction() {
        return marFraction;
    }

    public Double getAprFraction() {
        return aprFraction;
    }

    public Double getMayFraction() {
        return mayFraction;
    }

    public Double getJunFraction() {
        return junFraction;
    }

    public Double getJulFraction() {
        return julFraction;
    }

    public Double getAvgFraction() {
        return avgFraction;
    }

    public Double getSepFraction() {
        return sepFraction;
    }

    public Double getOctFraction() {
        return octFraction;
    }

    public Double getNovFraction() {
        return novFraction;
    }

    public Double getDecFraction() {
        return decFraction;
    }

    // set

    public void setJanFraction(Double janFraction) {
        this.janFraction = janFraction;
    }

    public void setFebFraction(Double febFraction) {
        this.febFraction = febFraction;
    }

    public void setMarFraction(Double marFraction) {
        this.marFraction = marFraction;
    }

    public void setAprFraction(Double aprFraction) {
        this.aprFraction = aprFraction;
    }

    public void setMayFraction(Double mayFraction) {
        this.mayFraction = mayFraction;
    }

    public void setJunFraction(Double junFraction) {
        this.junFraction = junFraction;
    }

    public void setJulFraction(Double julFraction) {
        this.julFraction = julFraction;
    }

    public void setAvgFraction(Double avgFraction) {
        this.avgFraction = avgFraction;
    }

    public void setSepFraction(Double sepFraction) {
        this.sepFraction = sepFraction;
    }

    public void setOctFraction(Double octFraction) {
        this.octFraction = octFraction;
    }

    public void setNovFraction(Double novFraction) {
        this.novFraction = novFraction;
    }

    public void setDecFraction(Double decFraction) {
        this.decFraction = decFraction;
    }

}
