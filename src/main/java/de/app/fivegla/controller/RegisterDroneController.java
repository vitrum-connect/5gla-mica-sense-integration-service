package de.app.fivegla.controller;

import de.app.fivegla.controller.dto.request.RegisterDroneRequest;
import de.app.fivegla.integration.micasense.MicaSenseIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drone")
public class RegisterDroneController {

    private final MicaSenseIntegrationService micaSenseIntegrationService;

    public RegisterDroneController(MicaSenseIntegrationService micaSenseIntegrationService) {
        this.micaSenseIntegrationService = micaSenseIntegrationService;
    }

    @Operation(
            operationId = "register-drone.register-drone",
            description = "Registers a drone."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Drone was registered successfully."
    )
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Void> registerDrone(@Valid @RequestBody RegisterDroneRequest request) {
        micaSenseIntegrationService.registerDrone(request.getDroneId());
        return ResponseEntity.ok().build();
    }

}
