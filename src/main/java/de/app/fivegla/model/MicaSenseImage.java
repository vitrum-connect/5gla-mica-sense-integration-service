package de.app.fivegla.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * MicaSense image.
 */
@Getter
@Setter
@Builder
public class MicaSenseImage {

    /**
     * The oid of the image.
     */
    private String oid;

    /**
     * The id of the drone.
     */
    private String droneId;

    /**
     * The channel of the image since the value can not be read from the EXIF.
     */
    private MicaSenseChannel channel;

    /**
     * The base64 encoded tiff image.
     */
    private String base64Image;

}
