package com.starknetworks.boundarycontext.profilemanagement.control.util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility to manipulate an image
 */
@Component
public class ImageUtil {

    /**
     * scale image
     *
     * @param img     image to scale
     * @param dWidth  width of destination image
     * @param dHeight height of destination image
     * @param fWidth  x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
    public byte[] scaleImage(byte[] img, int dWidth, int dHeight,
                             double fWidth, double fHeight) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(img);
        BufferedImage sbi = ImageIO.read(bais);
        BufferedImage dbi = null;
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return toByteArray(dbi);
    }

    private byte[] toByteArray(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }
}
