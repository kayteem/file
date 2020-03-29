/**
 * Created by:  Tobias Mielke
 * Created on:  17.11.2015
 * Modified on: 17.11.2015
 */

package de.kayteem.lib.file.fileWriter;

import java.io.*;
import java.util.List;


public class FileWriter implements IFileWriter {

    // FIELDS
    private String filePath;


    // CONSTRUCTION
    public FileWriter(String filePath) {
        this.filePath = filePath;
    }


    // IMPLEMENTATION
    public void writeToFile(List<String> lineStrings) throws IOException {

        // [1] - Create a file.
        File file = new File(filePath);

        // [2] - Create an output stream.
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // [3] - Create the buffered writer.
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {

            // [4] - Write line by line.
            for (String line : lineStrings) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

}
