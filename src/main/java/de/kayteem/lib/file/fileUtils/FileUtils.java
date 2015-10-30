/**
 * Created by:  Tobias Mielke
 * Created on:  30.10.2015
 * Changed on:  30.10.2015
 */

package de.kayteem.lib.file.fileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.security.CodeSource;


public class FileUtils {

    public static String retrieveExecutionPath(Class mainClass) throws FileNotFoundException {
        String executionPath;

        try {
            CodeSource codeSource = mainClass.getProtectionDomain().getCodeSource();
            File file = new File(codeSource.getLocation().toURI().getPath());
            executionPath = file.getParentFile().getPath() + "\\";
        } catch (URISyntaxException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return executionPath;
    }

}
