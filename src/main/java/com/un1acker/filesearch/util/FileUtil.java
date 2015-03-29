package com.un1acker.filesearch.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * un1acker
 * 29.03.2015
 */
public class FileUtil {
    public static List<File> listFiles(File searchDirectory, FilenameFilter nameFilter) {
        List<File> files = new ArrayList<>();
        innerListFiles(files, searchDirectory, nameFilter);
        return files;
    }

    private static void innerListFiles(List<File> files, File directory, FilenameFilter nameFilter) {
        File[] findFiles = directory.listFiles(nameFilter);
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                innerListFiles(files, file, nameFilter);
            }
        }
        if (findFiles.length > 0) {
            for (File file : findFiles) {
                files.add(file);
            }
        }
    }
}
