package com.se196668.mvc.service;

import com.se196668.mvc.dto.DeviceDetailsDTO;
import com.se196668.mvc.dto.DeviceFormDTO;
import com.se196668.mvc.dto.DeviceListDTO;
import com.se196668.mvc.entity.Device;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface DeviceService {

    List<DeviceListDTO> search(String deviceName);
    HashMap<String, String> statusList();
    void delete(Integer deviceId);
    Device findById(int deviceId);
    void create(DeviceFormDTO deviceFormDTO);
    DeviceDetailsDTO getDetails(int deviceId);
}
