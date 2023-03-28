package de.app.fivegla.integration.micasense;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExifDataIntegrationServiceTest {

    @Test
    void givenValidImageWhenReadingExifDataThenReturnExifData() throws Throwable {
        var image = readImageFromResources();
        Assertions.assertNotNull(image);

        var imageMetadata = new ExifDataIntegrationService().readLocation(image);
        Assertions.assertNotNull(imageMetadata);
        Assertions.assertEquals(52.8874122, imageMetadata.getCoordinates().get(0));
        Assertions.assertEquals(10.4364606, imageMetadata.getCoordinates().get(1));
    }

    private byte[] readImageFromResources() throws Throwable {
        try (var stream = getClass().getResourceAsStream("/micasense/example.tif")) {
            Assertions.assertNotNull(stream);
            return stream.readAllBytes();
        }
    }


}