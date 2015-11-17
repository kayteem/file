/**
 * Created by:  Tobias Mielke
 * Created on:  24.06.2015
 * Modified on: 09.09.2015
 */

package de.kayteem.lib.file.fileReader;

import java.io.IOException;
import java.util.List;


public interface IFileReader {

    List<String> readFromFile() throws IOException;

}