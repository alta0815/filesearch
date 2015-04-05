package com.un1acker.filesearch.util;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * un1acker
 * 29.03.2015
 */
public class FileUtil {
    public List<File> findFilesInDirectoryForFilter(File searchDirectory, FilenameFilter nameFilter) {
        List<File> resultFilesList = Lists.newArrayList();
        searchFilesInFolderRecursively(resultFilesList, searchDirectory, nameFilter);
        return resultFilesList;
    }

    //TODO Improve the performance and readability
    private void searchFilesInFolderRecursively(List<File> files, File directory, FilenameFilter nameFilter) {
        checkNotNull(directory, "Input directory is null");

        File[] findFiles = directory.listFiles(nameFilter);

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                searchFilesInFolderRecursively(files, file, nameFilter);
            }
        }
        if (findFiles.length > 0) {
            Collections.addAll(files, findFiles);
        }
    }
}
