import com.un1acker.filesearch.util.FileFilterByName;
import com.un1acker.filesearch.util.FileUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.List;


/**
 * un1acker
 * 30.03.2015
 */
public class FileUtilTest {
    private static File searchDirectory;

    @BeforeClass
    public static void setup() {
        searchDirectory = new File(System.getProperty("user.dir") + "\\src");
    }

    @Test
    public void searchOnlyJavaFile() {
        String filter = ".+java";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(6, files.size());
    }

    @Test
    public void searchFileBeginWithF() {
        String filter = "F.*";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(4, files.size());
    }

    @Test
    public void searchFileFilter() {
        String filter = "FileFilter.+";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void searchFilesWithQuestion() {
        String filter = "j.?v.?";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(2, files.size());
    }

    @Test
    public void searchDirectoryResources() {
        String filter = "resour.{3}";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void nothingFound() {
        String filter = ".idea";
        List<File> files = FileUtil.listFiles(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(0, files.size());
    }

}
