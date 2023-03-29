package de.app.fivegla.controller;

import de.app.fivegla.controller.dto.request.ImageProcessingRequest;
import de.app.fivegla.controller.dto.response.ImageProcessingResponse;
import de.app.fivegla.integration.micasense.MicaSenseIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Controller for image processing.
 */
@Slf4j
@RestController
@RequestMapping("/images")
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
            operationId = "images.process-image",
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
    public ResponseEntity<ImageProcessingResponse> processImage(@Valid @RequestBody ImageProcessingRequest request) {
        log.debug("Processing image for channel: {}.", request.getMicaSenseChannel());
        var oid = micaSenseIntegrationService.processImage(request.getDroneId(), request.getMicaSenseChannel(), request.getBase64Image());
        return ResponseEntity.ok(ImageProcessingResponse.builder()
                .oid(oid)
                .build());
    }

    /**
     * Return image as stream.
     */
    @Operation(
            operationId = "images.get-image",
            description = "Returns an image from the mica sense camera stored in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The image was found and returned."
    )
    @GetMapping(value = "/{oid}", produces = "image/tiff")
    public ResponseEntity<byte[]> getImage(@PathVariable String oid) {
        var headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        var micaSenseImage = micaSenseIntegrationService.getImage(oid);
        AtomicReference<ResponseEntity<byte[]>> responseEntity = new AtomicReference<>(ResponseEntity.notFound().build());
        micaSenseImage.ifPresent(
                image -> {
                    headers.setContentLength(image.length);
                    responseEntity.set(ResponseEntity.ok().headers(headers).body(image));
                }
        );
        return responseEntity.get();
    }

}
