package de.app.fivegla.integration.fiware;


import de.app.fivegla.api.Constants;
import de.app.fivegla.fiware.DeviceIntegrationService;
import de.app.fivegla.fiware.DeviceMeasurementIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    private static String getFiwareId(int sensorId) {
        return Constants.FIWARE_FARM21_SENSOR_ID_PREFIX + sensorId;
    }

    private static String getFiwareId() {
        return Constants.FIWARE_FARM21_SENSOR_ID_PREFIX + UUID.randomUUID();
    }

}
