package com.se196668.mvc.dto;

import com.se196668.mvc.entity.Category;
import com.se196668.mvc.entity.Device;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceFormDTO {

    @NotBlank(message = "Device code is required")
    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "Must start with 3 uppercase letters followed by 3 digits (e.g., DVC101)")
    private String deviceCode;

    @NotBlank(message = "deviceName is required")
    @Size(max = 200, message = "deviceName must not exceed 200 characters")
    @Pattern(regexp = "^\\S(.*\\S)?$", message = "deviceName cannot have leading or trailing spaces")
    private String deviceName;

    @NotNull(message = "Category is required")
    private int categoryId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Installation date is required")
    @Future(message = "Must be a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate installationDate;

    @NotNull(message = "Warranty is required")
    @Min(value = 1, message = "Warranty must be at least 1 month")
    @Max(value = 60, message = "Warranty cannot exceed 60 months")
    private Integer warrantyMonths;

    private String status;

    public DeviceFormDTO() {
    }

    public Device toEntity(Category category){
        Device device = new Device();
        device.setDeviceCode(this.deviceCode);
        device.setDeviceName(this.deviceName);
        device.setCategory(category);
        device.setPrice(this.price);
        device.setInstallationDate(this.installationDate);
        device.setWarrantyMonths(this.warrantyMonths);
        device.setStatus(this.status);
        return device;
    }

    public DeviceFormDTO(String deviceCode, String deviceName, int categoryId, BigDecimal price, LocalDate installationDate, Integer warrantyMonths, String status) {
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.categoryId = categoryId;
        this.price = price;
        this.installationDate = installationDate;
        this.warrantyMonths = warrantyMonths;
        this.status = status;
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
