package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * image writer test
 */
class ImageWriterTest {

    /**
     * Test method for {@link ImageWriter#writeToImage()}
     */
    @Test
    void writeToImage() {
        int nX = 800;
        int nY = 500;
        int gapX = nX / 16;
        int gapY = nY / 10;
        ImageWriter imageWriter = new ImageWriter("blue screen", nX, nY);
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % gapY == 0 || j % gapX == 0) {
                    imageWriter.writePixel(j, i, Color.BLACK);
                } else if (i > nY - gapY && j < gapX) {
                    imageWriter.writePixel(j, i, new Color(255, 0, 0));
                } else if ((i - 250) * (i - 250) + (j - 400) * (j - 400) <= 2250) {
                    imageWriter.writePixel(j, i, new Color(200, 10, 0));
                } else {
                    imageWriter.writePixel(j, i, new Color(0, 0, 1000));
                }
            }
        }
        imageWriter.writeToImage();
    }
}