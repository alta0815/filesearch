package com.un1acker.filesearch;

import com.un1acker.filesearch.util.FileFilterByName;
import com.un1acker.filesearch.util.FileUtil;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * un1acker
 * 29.03.2015
 */
public class FileSearch {
    private List<File> findFiles;
    private FileUtil fileUtil;

    private static Settings settings = new Settings();
    private static CmdLineParser parser = new CmdLineParser(settings);

    //statics methods should be short and quick
    // here you put your main logic in a static context. This won't be work in more then one thread
    public static void init(String[] args, File rootDirectory) {
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
        }
        FileSearch fileSearch = new FileSearch();
        fileSearch.searchResult(rootDirectory);
    }

    public FileSearch() {
        fileUtil = new FileUtil();
    }

    public void searchResult(File searchDirectory) {
        searchFiles(searchDirectory);
        System.out.println(getInfo());
    }

    private void searchFiles(File searchDirectory) {
        if (Settings.getName() != null) {
            this.findFiles = fileUtil.findFilesInDirectoryForFilter(searchDirectory,
                    new FileFilterByName(Settings.getName()));
        } else {
            System.out.println("No properties was provided. Searching in the root directory");
            this.findFiles = fileUtil.findFilesInDirectoryForFilter(searchDirectory, new FileFilterByName(".+"));
        }
    }

    private String getInfo() {
        if (this.findFiles.size() != 0) {
            if (Settings.isReturnLastModifiedDate() && Settings.isReturnFileSize()) {
                String format = "%1$-50s %2$-15s %3$-50s %4$-10s %5$-20s\n";
                return getInfoFindFiles(format);
            } else if (Settings.isReturnFileSize()) {
                String format = "%1$-50s %2$-15s %3$-50s %4$-10s\n";
                return getInfoFindFiles(format);
            } else if (Settings.isReturnLastModifiedDate()) {
                String format = "%1$-50s %2$-15s %3$-50s %5$-20s\n";
                return getInfoFindFiles(format);
            } else {
                String format = "%1$-50s %2$-15s %3$-50s\n";
                return getInfoFindFiles(format);
            }
        }
        return "Nothing found\n";

    }

    private String getInfoFindFiles(String format) {
        StringBuilder info = new StringBuilder();
        info.append(String.format(format, "FileName", "isDirectory", "FilePath", "Size", "Date"));
        for (File file : this.findFiles) {
            info.append(String.format(format, file.getName(), file.isDirectory(), getFilePath(file.getAbsolutePath()), file.length(), new Date(file.lastModified())));
        }
        return info.toString();
    }

    private String getFilePath(String absolutePath) {
        return ".." + File.separator + absolutePath.
                substring(System.getProperty("user.dir").length(), absolutePath.lastIndexOf(File.separator));
    }
}
