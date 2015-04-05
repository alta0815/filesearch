package com.un1acker.filesearch;

import com.un1acker.filesearch.util.FileFilterByName;
import com.un1acker.filesearch.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * un1acker
 * 29.03.2015
 */
public class FileSearch {
    private List<File> findFiles;
    //statics methods should be short and quick
    // here you put your main logic in a static context. This won't be work in more then one thread
    FileUtil fileUtil;

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
            }
        } else {
            info.append("Nothing found\n");
        }
        return info.toString();
    }


}
