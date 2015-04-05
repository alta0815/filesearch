import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.un1acker.filesearch.util.FileFilterByName;
import com.un1acker.filesearch.util.FileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import java.io.File;
import java.util.List;


/**
 * un1acker
 * 30.03.2015
 */
public class FileUtilTest {
    @Rule
    public MethodRule benchmarkRun = new BenchmarkRule();

    //TODO for tested classes you shouldn't use static
    private File searchDirectory;
    private FileUtil fileUtil;

    @Before
    public void setup() {
        searchDirectory = new File(System.getProperty("user.dir") + File.separator + "src");
        fileUtil = new FileUtil();
    }

    @Test
    public void searchOnlyJavaFile() {
        String filter = ".+java";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(6, files.size());
    }

    @Test
    public void searchFileBeginWithF() {
        String filter = "F.*";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(4, files.size());
    }

    @Test
    public void searchFileFilter() {
        String filter = "FileFilter.+";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void searchFilesWithQuestion() {
        String filter = "j.?v.?";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(2, files.size());
    }

    @Test
    public void searchDirectoryResources() {
        String filter = "resour.{3}";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void nothingFound() {
        String filter = ".idea";
        List<File> files = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(filter));
        Assert.assertEquals(0, files.size());
    }

}
