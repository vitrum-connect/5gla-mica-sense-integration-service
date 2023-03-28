package de.app.fivegla.integration.fiware;


import de.app.fivegla.api.Constants;
import de.app.fivegla.fiware.DeviceIntegrationService;
import de.app.fivegla.fiware.DeviceMeasurementIntegrationService;
import de.app.fivegla.fiware.api.enums.DeviceCategoryValues;
import de.app.fivegla.fiware.model.Device;
import de.app.fivegla.fiware.model.DeviceCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for integration with FIWARE.
 */
@Slf4j
@Service
public class FiwareIntegrationServiceWrapper {
    private final DeviceIntegrationService deviceIntegrationService;
    private final DeviceMeasurementIntegrationService deviceMeasurementIntegrationService;

    public FiwareIntegrationServiceWrapper(DeviceIntegrationService deviceIntegrationService,
                                           DeviceMeasurementIntegrationService deviceMeasurementIntegrationService) {
        this.deviceIntegrationService = deviceIntegrationService;
        this.deviceMeasurementIntegrationService = deviceMeasurementIntegrationService;
    }

    /**
     * Create a new device in FIWARE.
     */
    public void createDevice(String droneId) {
        var fiwareId = getFiwareId(droneId);
        var device = Device.builder()
                .deviceCategory(DeviceCategory.builder()
                        .value(List.of(DeviceCategoryValues.MicaSenseDrone.getKey()))
                        .build())
                .id(fiwareId)
                .build();
        deviceIntegrationService.persist(device);
    }

    private static String getFiwareId(String sensorId) {
        return Constants.MICA_SENSE_DRONE_ID_PREFIX + sensorId;
    }

}
