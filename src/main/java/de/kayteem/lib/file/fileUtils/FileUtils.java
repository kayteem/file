/**
 * Created by:  Tobias Mielke
 * Created on:  30.10.2015
 * Changed on:  10.11.2015
 */

package de.kayteem.lib.file.fileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.security.CodeSource;


public class FileUtils {

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

}
