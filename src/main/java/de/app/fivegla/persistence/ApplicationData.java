package de.app.fivegla.persistence;

import de.app.fivegla.model.MicaSenseImage;
import one.microstream.integrations.spring.boot.types.Storage;
import one.microstream.storage.types.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Application data.
 */
@Storage
public class ApplicationData {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    private transient StorageManager storageManager;

    private List<MicaSenseImage> micaSenseImages;

    /**
     * Add image to the list of images.
     *
     * @param micaSenseImage The image to add.
     * @return The added image.
     */
    protected MicaSenseImage addMicaSenseImage(MicaSenseImage micaSenseImage) {
        if (null == micaSenseImages) {
            micaSenseImages = new ArrayList<>();
        }
        micaSenseImages.add(micaSenseImage);
        storageManager.store(this);
        return micaSenseImage;
    }

    /**
     * Access the list of images.
     *
     * @return The list of images.
     */
    protected List<MicaSenseImage> getMicaSenseImages() {
        return micaSenseImages;
    }
}
