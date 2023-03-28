package de.app.fivegla.integration.micasense;

import de.app.fivegla.integration.fiware.FiwareIntegrationServiceWrapper;
import de.app.fivegla.model.MicaSenseChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * Service for Mica Sense integration.
 */
@Slf4j
@Service
public class MicaSenseIntegrationService {

    private final ExifDataIntegrationService exifDataIntegrationService;
    private final FiwareIntegrationServiceWrapper fiwareIntegrationServiceWrapper;

    public MicaSenseIntegrationService(ExifDataIntegrationService exifDataIntegrationService,
                                       FiwareIntegrationServiceWrapper fiwareIntegrationServiceWrapper) {
        this.exifDataIntegrationService = exifDataIntegrationService;
        this.fiwareIntegrationServiceWrapper = fiwareIntegrationServiceWrapper;
    }

    /**
     * Processes an image from the mica sense camera.
     *
     * @param micaSenseChannel      The channel the image was taken with.
     * @param base64EncodedTifImage The base64 encoded tiff image.
     */
    public void processImage(MicaSenseChannel micaSenseChannel, String base64EncodedTifImage) {
        var image = Base64.getDecoder().decode(base64EncodedTifImage);
        var location = exifDataIntegrationService.readLocation(image);
        log.debug("Channel for the image: {}.", micaSenseChannel);
        log.debug("Location for the image: {}.", location.getCoordinates());
    }

    /**
     * Registers a new drone in the system.
     *
     * @param droneId The id of the drone.
     */
    public void registerDrone(String droneId) {
        fiwareIntegrationServiceWrapper.createDevice(droneId);
    }
}
