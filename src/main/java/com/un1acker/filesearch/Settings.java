package com.un1acker.filesearch;

import org.kohsuke.args4j.Option;

/**
 * un1acker
 * 29.03.2015
 */
public class Settings {
    @Option(name = "-n", aliases = "--name", usage = "search files by this name(regex pattern)")
    private static String name;
    @Option(name = "-s", aliases = "--size", usage = "add size in byte for all searched files")
    private static boolean returnFileSize;

    public static String getName() {
        return name;
    }

    public static boolean isReturnFileSize() {
        return returnFileSize;
    }
}
