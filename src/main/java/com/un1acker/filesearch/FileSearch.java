package com.un1acker.filesearch;

import com.un1acker.filesearch.util.FIleFilterByName;
import com.un1acker.filesearch.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * un1acker
 * 29.03.2015
 */
public class FileSearch {
    private List<File> findFiles;
//    private Settings settings;

    public FileSearch() {
    }

    public void searchResult(File searchDirectory) {
        this.searchFiles(searchDirectory);
        System.out.println(getInfoFindFiles());
    }

    private void searchFiles(File searchDirectory) {
        if (Settings.getName() != null) {
            this.findFiles = FileUtil.listFiles(searchDirectory, new FIleFilterByName(Settings.getName()));
        } else {
            this.findFiles = FileUtil.listFiles(searchDirectory, new FIleFilterByName(".+"));
        }
    }

    private String getInfoFindFiles() {
        StringBuilder info = new StringBuilder();
        String format = "%s : %s \n";
        if (findFiles.size() != 0) {
            for (File file : this.findFiles) {
                info.append("++++++++++++++++++++++++++").append("\n");
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
