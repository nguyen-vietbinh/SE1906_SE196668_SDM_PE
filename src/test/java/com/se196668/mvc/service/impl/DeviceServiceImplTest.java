package com.se196668.mvc.service.impl;

import com.se196668.mvc.entity.Device;
import com.se196668.mvc.repository.CategoryRepository;
import com.se196668.mvc.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void statusList_returnsExpectedStatuses() {
        HashMap<String, String> result =
                deviceService.statusList();

        assertEquals(3, result.size());
        assertEquals("Active", result.get("AC"));
        assertEquals("Inactive", result.get("IN"));
        assertEquals("Draft", result.get("DR"));
    }

    @Test
    void findById_existingId_returnsDevice() {
        // Arrange
        Device device = mock(Device.class);

        when(deviceRepository.findById(1))
                .thenReturn(Optional.of(device));

        // Act
        Device result = deviceService.findById(1);

        // Assert
        assertSame(device, result);
        verify(deviceRepository).findById(1);
    }

    @Test
    void findById_missingId_throwsException() {
        // Arrange
        when(deviceRepository.findById(999))
                .thenReturn(Optional.empty());

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> deviceService.findById(999)
        );

        assertEquals(
                "Device not found with ID",
                exception.getMessage()
        );
    }

}
