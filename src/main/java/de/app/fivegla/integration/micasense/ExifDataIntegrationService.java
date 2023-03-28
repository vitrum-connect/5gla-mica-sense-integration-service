package de.app.fivegla.integration.micasense;

import de.app.fivegla.api.Error;
import de.app.fivegla.api.ErrorMessage;
import de.app.fivegla.api.exceptions.BusinessException;
import de.app.fivegla.fiware.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service for reading exif data from images.
 */
@Slf4j
@Service
public class ExifDataIntegrationService {

    /**
     * Reads exif data from image.
     *
     * @param image The image to read exif data from.
     * @return exif data from image.
     */
    public Location readLocation(byte[] image) {
        try {
            var metadata = (TiffImageMetadata) Imaging.getMetadata(image);
            var gps = metadata.getGPS();
            return Location.builder().coordinates(Arrays.asList(gps.getLatitudeAsDegreesNorth(), gps.getLongitudeAsDegreesEast())).build();
        } catch (Exception e) {
            var errorMessage = ErrorMessage.builder().error(Error.COULD_NOT_READ_IMAGE_METADATA).message("Could not read image metadata, please see log for more details.").build();
            throw new BusinessException(errorMessage);
        }
    }
}
