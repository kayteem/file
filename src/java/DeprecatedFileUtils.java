package de.kayteem.lib.fileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by:  Tobias Mielke
 * Created on:  30.10.2015
 * Changed on:  11.08.2019
 */
public class DeprecatedFileUtils {

    // RESOURCES
    public static void openResource(String filename) throws IOException {
        if (Desktop.isDesktopSupported()) {

            // [1] - Load resource.
            File file = loadResource(filename);

            // [2] - Open resource.
            Desktop.getDesktop().open(file);
        }
    }

    public static File loadResource(String filename) throws IOException {

        // [1] - Retrieve file.
        File file = new File(filename);

        // [2] - If file does not exist -> Load as resource stream.
        if (!file.exists()) {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();
        }

        return file;
    }
    
    public static BufferedImage loadImageResource(String filename) throws IOException {

        // [1] - Load resource.
        File imageFile = loadResource(filename);

        // [2] - Return image.
        return ImageIO.read(imageFile);
    }

    public static List<BufferedImage> loadImageResources(String... filenames) throws IOException {

        // [1] - Create image list.
        List<BufferedImage> images = new ArrayList<>();

        // [2] - Add image resources.
        for (String filename : filenames) {
            images.add(loadImageResource(filename));
        }

        return images;
    }

    public static void openFile(String path) throws IOException {
        openFile(new File(path));
    }

    public static void openFile(File file) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(file);
    }

}
