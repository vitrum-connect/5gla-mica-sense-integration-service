package de.app.fivegla.controller.dto.request;

import de.app.fivegla.model.MicaSenseChannel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request for image processing.
 */
@Getter
@Setter
@Schema(description = "Request for image processing.")
public class ImageProcessingRequest {

    /**
     * The id of the drone.
     */
    @NotBlank
    @Schema(description = "The id of the drone.")
    private String droneId;

    /**
     * The channel of the image since the value can not be read from the EXIF.
     */
    @NotNull
    @Schema(description = "The channel of the image.")
    private MicaSenseChannel micaSenseChannel;

    /**
     * The base64 encoded tiff image.
     */
    @NotBlank
    @Schema(description = "The base64 encoded tiff image.")
    private String base64Image;
}
