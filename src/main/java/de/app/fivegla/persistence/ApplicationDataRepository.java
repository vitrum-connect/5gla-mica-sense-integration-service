package de.app.fivegla.persistence;

import de.app.fivegla.model.MicaSenseImage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationDataRepository {

    private final ApplicationData applicationData;

    public ApplicationDataRepository(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    /**
     * Returns the image with the given oid.
     *
     * @param oid The oid of the image.
     * @return The image with the given oid.
     */
    public Optional<MicaSenseImage> getImage(String oid) {
        return applicationData.getMicaSenseImages().stream()
                .filter(image -> image.getOid().equals(oid))
                .findFirst();
    }

    /**
     * Add image to the list of images.
     *
     * @param micaSenseImage The image to add.
     * @return The added image.
     */
    public MicaSenseImage addMicaSenseImage(MicaSenseImage micaSenseImage) {
        return applicationData.addMicaSenseImage(micaSenseImage);
    }
}
