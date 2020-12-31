package de.kayteem.lib.fileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;


/**
 * Created by Tobias Mielke
 * Created on 31.12.2020
 * Changed on 31.12.2020
 */
public class FileUtils {

    // IMPLEMENTATION - paths
    public static Path retrieveCodeBasePath(Class<?> clazz) throws URISyntaxException {
        CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
        
        return Paths.get(codeSource.getLocation().toURI());
    }

    public static Path retrieveExecutionPath(Class<?> clazz) throws URISyntaxException {
        Path pathCodeBase = retrieveCodeBasePath(clazz);

        return pathCodeBase.toFile().isDirectory()
                ? pathCodeBase
                : pathCodeBase.getParent();
    }

    public static Path retrieveHomeDirectoryPath() throws FileNotFoundException {
        Path pathHomeDir = Paths.get(System.getProperty("user.home"));

        if (!Files.exists(pathHomeDir)) {
            throw new FileNotFoundException("Cannot find home directory");
        }

        return pathHomeDir;
    }


    // IMPLEMENTATION - directories
    public static Path createDirInPath(Path path, String dirName) throws IOException {
        Path pathNewDir = path.resolve(dirName);

        if (!Files.exists(pathNewDir)) {
            Files.createDirectory(pathNewDir);
        }

        return pathNewDir;
    }

    
    // IMPLEMENTATION - file dialogs
    public static Path showFileDialog(Component parent, String title, String approveButtonText, Path initialDir, FileNameExtensionFilter extension) {

        // [1] - Create file chooser.
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);
        fc.setApproveButtonText(approveButtonText);
        fc.setMultiSelectionEnabled(false);

        // [2] - Set initial directory.
        if (initialDir != null) {
            if (Files.exists(initialDir)) {
                fc.setCurrentDirectory(initialDir.toFile());
            }
            else {
                Path parentDir = initialDir.getParent();
                if (Files.exists(parentDir)) {
                    fc.setCurrentDirectory(parentDir.toFile());
                }
            }
        }

        // [3] - Set filter.
        if (extension != null) {
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(extension);
        }

        // [4] - Open.
        int result = fc.showOpenDialog(parent);

        // [5] - Return file or null (if cancelled).
        return (result == JFileChooser.APPROVE_OPTION) ? fc.getSelectedFile().toPath() : null;
    }

}
