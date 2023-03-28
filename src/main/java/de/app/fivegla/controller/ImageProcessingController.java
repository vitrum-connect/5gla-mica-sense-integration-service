package de.app.fivegla.controller;

import de.app.fivegla.controller.dto.request.ImageProcessingRequest;
import de.app.fivegla.integration.micasense.MicaSenseIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for image processing.
 */
@Slf4j
@RestController
@RequestMapping("/image-processing")
public class ImageProcessingController {

    private final MicaSenseIntegrationService micaSenseIntegrationService;

    public ImageProcessingController(MicaSenseIntegrationService micaSenseIntegrationService) {
        this.micaSenseIntegrationService = micaSenseIntegrationService;
    }

    /**
     * Processes an image from the mica sense camera.
     *
     * @return 200 if image was processed successfully.
     */
    @Operation(
            operationId = "image-processing.process-image",
            description = "Processes an image from the mica sense camera."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Image was processed successfully."
    )
    @ApiResponse(
            responseCode = "400",
            description = "The request is invalid."
    )
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Void> processImage(@Valid @RequestBody ImageProcessingRequest request) {
        log.debug("Processing image for channel: {}.", request.getMicaSenseChannel());
        micaSenseIntegrationService.processImage(request.getMicaSenseChannel(), request.getBase64EncodedTiffImage());
        return ResponseEntity.ok().build();
    }

}
