package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("test blue",800,500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                // 800/16 = 50
                if (i % 50 == 0 || j % 50 == 0)  {
                    imageWriter.writePixel(j, i, Color.BLACK);
                }

                 else {
                    imageWriter.writePixel(j, i, Color.BLUE);
                }
            }
        }
        imageWriter.writeToImage();
    }

}