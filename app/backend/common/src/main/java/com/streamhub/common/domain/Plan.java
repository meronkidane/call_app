package com.streamhub.common.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "plans")
public class Plan extends BaseDocument {

    @Indexed(unique = true)
    private String code;

    private String name;

    private BigDecimal price;

    private String currency;

    private int maxDevices;

    private String maxResolution;

    private boolean drmRequired;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMaxDevices() {
        return maxDevices;
    }

    public void setMaxDevices(int maxDevices) {
        this.maxDevices = maxDevices;
    }

    public String getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(String maxResolution) {
        this.maxResolution = maxResolution;
    }

    public boolean isDrmRequired() {
        return drmRequired;
    }

    public void setDrmRequired(boolean drmRequired) {
        this.drmRequired = drmRequired;
    }
}
