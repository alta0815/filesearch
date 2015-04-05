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
        System.out.println(getInfoFindFiles());
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

    private String getInfoFindFiles() {
        StringBuilder info = new StringBuilder();
        String format = "%s : %s \n";
        if (findFiles.size() != 0) {
            for (File file : this.findFiles) {
                info.append("==============================").append("\n");
                info.append(String.format(format, "File name", file.getName()));
                info.append(String.format(format, "isDirectory", file.isDirectory()));
                info.append(String.format(format, "File name parent path", file.getParent()));
                if (Settings.isReturnFileSize() && file.isFile()) {
                    info.append(String.format(format, "File size in byte", file.length()));
                }
                if(Settings.isReturnLastModifiedDate()){
                    info.append(String.format(format, "Last modified", new Date(file.lastModified())));
                }
            }
        } else {
            info.append("Nothing found\n");
        }
        return info.toString();
    }


}
