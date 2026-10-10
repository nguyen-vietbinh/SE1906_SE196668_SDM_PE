package com.se196668.mvc.dto;

import com.se196668.mvc.entity.Category;
import com.se196668.mvc.entity.Device;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceListDTO {

    private Integer id;

    private String deviceCode;

    private String deviceName;

    private int categoryId;

    private BigDecimal price;

    private LocalDate installationDate;

    private String status;

    public DeviceListDTO(Device device){
        if(device != null){
            this.id = device.getId();
            this.deviceCode = device.getDeviceCode();
            this.deviceName = device.getDeviceName();
            this.categoryId = device.getCategory().getId();
            this.price = device.getPrice();
            this.installationDate = device.getInstallationDate();
            this.status = device.getStatus();
        }
    }

    public DeviceListDTO(Integer id, String deviceCode, String deviceName, int categoryId, BigDecimal price, LocalDate installationDate, String status) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.categoryId = categoryId;
        this.price = price;
        this.installationDate = installationDate;
        this.status = status;
    }

    public DeviceListDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
