package de.app.fivegla.controller;

import de.app.fivegla.controller.dto.response.VersionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for information purpose.
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @Value("${app.version:unknown}")
    private String applicationVersion;

    /**
     * Returns the version of the application.
     *
     * @return the version of the application
     */
    @Operation(
            operationId = "info.version",
            description = "Fetch the version of the application."
    )
    @ApiResponse(
            responseCode = "200",
            description = "The version of the application.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = VersionResponse.class)
            )
    )
    @GetMapping(value = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VersionResponse> getVersion() {
        return ResponseEntity.ok(VersionResponse.builder().version(applicationVersion).build());
    }

}
