package com.github.Laefye.Minecraft2D.types;

import com.github.Laefye.Minecraft2D.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageResource implements Resource {
    private BufferedImage image;

    public ImageResource(BufferedImage image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public static ImageResource load(String filename) throws IOException {
        return ImageResource.load(
                Main.class.getResourceAsStream(filename)
        );
    }

    public static ImageResource load(InputStream stream) throws IOException {
        var image = ImageIO.read(stream);
        stream.close();
        return new ImageResource(image);
    }
}
