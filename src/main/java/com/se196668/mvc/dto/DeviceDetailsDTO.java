package com.se196668.mvc.dto;

import com.se196668.mvc.entity.Device;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceDetailsDTO {

    private String deviceCode;

    private String deviceName;

    private int categoryId;

    private BigDecimal price;

    private LocalDate installationDate;

    private Integer warrantyMonths;

    private String status;

    public DeviceDetailsDTO(String deviceCode, String deviceName, int categoryId, BigDecimal price, LocalDate installationDate, Integer warrantyMonths, String status) {
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.categoryId = categoryId;
        this.price = price;
        this.installationDate = installationDate;
        this.warrantyMonths = warrantyMonths;
        this.status = status;
    }

    public DeviceDetailsDTO(Device device){
        if(device!=null){
            this.deviceCode = device.getDeviceCode();
            this.deviceName = device.getDeviceName();
            this.categoryId = device.getCategory().getId();
            this.price = device.getPrice();
            this.installationDate = device.getInstallationDate();
            this.warrantyMonths = device.getWarrantyMonths();
            this.status = device.getStatus();
        }
    }

    public DeviceDetailsDTO() {
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }

    public Integer getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(Integer warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
