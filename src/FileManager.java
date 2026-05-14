/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This class saves calculation results and chart images.
*/

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FileManager {

    private File file;

    public FileManager(String fileName) throws IOException {

        file = new File(fileName);

        if (file.exists() == false) {
            file.createNewFile();
        }
    }

    public void saveText(String text) throws IOException {

        FileWriter writer = new FileWriter(file, true);

        writer.write(text);
        writer.write("\n-------------------------\n");

        writer.close();
    }


    public static void saveComponentImage(Component component, String fileName) throws IOException {

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();

        component.paint(g2);

        g2.dispose();

        ImageIO.write(image, "png", new File(fileName));
    }
}
