/**
 * Created by:  Tobias Mielke
 * Created on:  30.10.2015
 * Changed on:  15.02.2016
 */

package de.kayteem.lib.file.fileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    // FILE DIALOGS
    public static String showFileDialog(Component parent, String title, String approveButtonText, String initialDir, FileNameExtensionFilter extension) {

        // [1] - Create file chooser.
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);
        fc.setApproveButtonText(approveButtonText);
        fc.setMultiSelectionEnabled(false);

        // [2] - Set initial directory.
        if (initialDir != null) {
            File initialDirectory = new File(initialDir);
            if (initialDirectory.exists()) {
                fc.setCurrentDirectory(initialDirectory);
            }
        }

        // [3] - Set filter.
        fc.addChoosableFileFilter(extension);

        // [4] - Open.
        int result = fc.showOpenDialog(parent);

        // [5] - Return file or null (if cancelled).
        return (result == JFileChooser.APPROVE_OPTION) ? fc.getSelectedFile().getAbsolutePath() : null;
    }


    // RESOURCES
    public static void openResource(String filename) throws IOException {
        if (Desktop.isDesktopSupported()) {

            // [1] - Load resource.
            File file = loadResource(filename);

            // [2] - Open resource.
            Desktop.getDesktop().open(file);
        }
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


    // FILES IN DIRECTORY
    public static void openFile(String path) throws IOException {
        openFile(new File(path));
    }

    public static void openFile(File file) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(file);
    }


    public static String retrieveExecutionPath(Class clazz) throws FileNotFoundException {
        String absolutePath = retrieveJarFile(clazz).getAbsolutePath();

        return absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
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


    public static String retrieveParentDirectoryPath(File file) {
        String path = file.getAbsolutePath();

        return path.substring(0, path.lastIndexOf(File.separator));
    }


    // HELPERS
    private static File loadResource(String filename) throws IOException {

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

}
