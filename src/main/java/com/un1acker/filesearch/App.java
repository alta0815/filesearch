package com.un1acker.filesearch;

import java.io.File;

/**
 * un1acker
 * 29.03.2015
 */
public class App {
    private static final File ROOT_DIRECTORY = new File(System.getProperty("user.dir"));

    public static void main(String[] args) {
        FileSearch.init(args, ROOT_DIRECTORY);
    }
}
