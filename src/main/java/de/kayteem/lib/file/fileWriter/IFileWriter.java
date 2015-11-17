/**
 * Created by:  Tobias Mielke
 * Created on:  17.11.2015
 * Modified on: 17.11.2015
 */

package de.kayteem.lib.file.fileWriter;

import java.io.IOException;
import java.util.List;


public interface IFileWriter {

    void writeToFile(List<String> lineStrings) throws IOException;

}