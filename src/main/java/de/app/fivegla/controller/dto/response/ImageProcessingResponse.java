package de.app.fivegla.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Response for image processing.
 */
@Getter
@Setter
@Builder
@Schema(description = "Response for image processing.")
public class ImageProcessingResponse extends Response {

    /**
     * The oid of the image.
     */
    @Schema(description = "The oid of the image.")
    private String oid;

}
