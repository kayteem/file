import de.kayteem.lib.fileUtils.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Tobias Mielke
 * Created on 31.12.2020
 * Changed on 31.12.2020
 */
public class AFileUtils {

    // paths
    @Test
    public void canRetrieveTheCodeBaseOfAClass() throws Exception {

        // SETUP
        Class<?> classFileUtils = FileUtils.class;

        // EXECUTION
        Path pathCodeBase = FileUtils.retrieveCodeBasePath(classFileUtils);

        // POST CONDITION
        Path expectedCodeBasePath = Paths.get("P:/Java/de/kayteem/lib/fileUtils/out/production/fileUtils");
        Assert.assertEquals(expectedCodeBasePath, pathCodeBase);
    }

    @Test
    public void canRetrieveTheExecutionPathOfAClass() throws Exception {

        // SETUP
        Class<?> classFileUtils = FileUtils.class;

        // EXECUTION
        Path pathExecutionPath = FileUtils.retrieveExecutionPath(classFileUtils);

        // POST CONDITION
        Path expectedExecutionPath = Paths.get("P:/Java/de/kayteem/lib/fileUtils/out/production/fileUtils");
        Assert.assertEquals(expectedExecutionPath, pathExecutionPath);
    }

    @Test
    public void canRetrieveTheUserDirectoryPath() throws Exception {

        // EXECUTION
        Path pathUserDir = FileUtils.retrieveHomeDirectoryPath();

        // POST CONDITION
        Path expectedUserDirectoryPath = Paths.get("C:/Users/Tobias");
        Assert.assertEquals(expectedUserDirectoryPath, pathUserDir);
    }


    // directories


    @Test
    public void canCreateANewDirectoryInAPath() throws Exception {

        // SETUP
        Path path = Paths.get("src/test");

        // PRE CONDITION
        Assert.assertTrue(Files.exists(path));

        // EXECUTION
        FileUtils.createDirInPath(path, "newDir");

        // POST CONDITION
        Path createdDirPath = path.resolve("newDir");
        Assert.assertTrue(Files.exists(createdDirPath));
    }

}
