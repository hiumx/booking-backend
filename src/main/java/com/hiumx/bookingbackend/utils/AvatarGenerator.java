package com.hiumx.bookingbackend.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AvatarGenerator {
    public static BufferedImage generateAvatar(String name) {
        int size = 100;
        BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Background color
        g2d.setColor(new Color(52, 152, 219));
        g2d.fillRect(0, 0, size, size);

        // Text properties
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics();
        int x = (size - fm.stringWidth(name.substring(0, 1).toUpperCase())) / 2;
        int y = ((size - fm.getHeight()) / 2) + fm.getAscent();

        // Draw the first character of the name
        g2d.drawString(name.substring(0, 1).toUpperCase(), x, y);
        g2d.dispose();

        return bufferedImage;
    }
}
