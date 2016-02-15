/**
 * Created by:  Tobias Mielke
 * Created on:  30.10.2015
 * Changed on:  15.02.2016
 */

package de.kayteem.lib.file.fileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    public static BufferedImage loadImageResource(String filename) throws IOException {
        return ImageIO.read(ClassLoader.getSystemResource(filename));
    }

    public static List<BufferedImage> loadImageResources(String... filenames) throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        for (String filename : filenames) {
            images.add(ImageIO.read(ClassLoader.getSystemResource(filename)));
        }

        return images;
    }


    public static String retrieveExecutionPath(Class clazz) throws FileNotFoundException {
        File file = retrieveJarFile(clazz);
        return file.getParentFile().getPath() + "\\";
    }

    public static File retrieveJarFile(Class clazz) throws FileNotFoundException {
        File file;

        try {
            CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
            file = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return file;
    }


    public static String retrieveDownloadsPath() {
        return "C:/Users/" + System.getProperty("user.name") + "/Downloads/";
    }

    public static File retrieveDownloadsDirectory() throws FileNotFoundException {
        File downloadsDir = new File(retrieveDownloadsPath());

        if (!downloadsDir.exists()) {
            throw new FileNotFoundException("\"Downloads\" could not be found!");
        }

        return downloadsDir;
    }


    public static void openFile(String path) throws IOException {
        openFile(new File(path));
    }

    public static void openFile(File file) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(file);
    }

}
