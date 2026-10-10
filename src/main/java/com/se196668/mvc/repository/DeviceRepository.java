package com.se196668.mvc.repository;

import com.se196668.mvc.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findByDeviceNameContainingIgnoreCaseOrderByInstallationDateDesc(String deviceName);

    boolean existsDeviceByDeviceCodeIgnoreCase(String trim);

    List<Device> findAllByOrderByInstallationDateDesc();
}