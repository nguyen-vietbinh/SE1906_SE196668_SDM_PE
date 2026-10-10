package com.se196668.mvc.service.impl;


import com.se196668.mvc.dto.DeviceDetailsDTO;
import com.se196668.mvc.dto.DeviceFormDTO;
import com.se196668.mvc.dto.DeviceListDTO;
import com.se196668.mvc.entity.Category;
import com.se196668.mvc.entity.Device;
import com.se196668.mvc.repository.CategoryRepository;
import com.se196668.mvc.repository.DeviceRepository;
import com.se196668.mvc.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<DeviceListDTO> search(String deviceName) {

        List<Device> list = new ArrayList<>();
        if(deviceName == null || deviceName.isBlank()){
            list = deviceRepository.findAllByOrderByInstallationDateDesc();
        }else{
            list = deviceRepository.findByDeviceNameContainingIgnoreCaseOrderByInstallationDateDesc(deviceName.trim());
        }

        return list.stream().map(t -> new DeviceListDTO(t)).toList();
    }

    @Override
    public HashMap<String, String> statusList() {
        HashMap<String, String> result = new HashMap<>();
        result.put("AC", "Active");
        result.put("IN", "Inactive");
        result.put("DR", "Draft");
        return result;
    }

    @Override
    public void delete(Integer deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Override
    public Device findById(int deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found with ID"));
    }

    @Override
    public void create(DeviceFormDTO deviceFormDTO) {

        String deviceCode = deviceFormDTO.getDeviceCode();

        if(deviceCode != null && deviceRepository.existsDeviceByDeviceCodeIgnoreCase(deviceCode.trim())){
            throw new RuntimeException("Device code can not be duplicate");
        }

        Category cate = categoryRepository.findById(deviceFormDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not exist"));
        Device device = deviceFormDTO.toEntity(cate);
        deviceRepository.save(device);
    }

    @Override
    public DeviceDetailsDTO getDetails(int deviceId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if(device.isEmpty()){
            return null;
        }
        return new DeviceDetailsDTO(device.get());
    }
}
