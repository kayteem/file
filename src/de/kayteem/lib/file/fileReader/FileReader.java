/**
 * Created by:  Tobias Mielke
 * Created on:  24.06.2015
 * Modified on: 09.09.2015
 */

package de.kayteem.lib.file.fileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileReader implements IFileReader {

    // FIELDS
    private String filePath;


    // CONSTRUCTION
    public FileReader(String filePath) {
        this.filePath = filePath;
    }


    // IMPLEMENTATION
    public List<String> readFromFile() throws IOException {

        // [1] - Create string list.
        final List<String> lineStrings = new ArrayList<>();

        // [2] - Create buffered reader.
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {

            // [3] - Read line by line.
            String line;
            while ((line = br.readLine()) != null) {
                lineStrings.add(line);
            }
        }

//        try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
//            linesStream.forEach(lineStrings::add);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return lineStrings;
    }

}
