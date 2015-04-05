package com.un1acker.filesearch;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;

/**
 * un1acker
 * 29.03.2015
 */
public class App {
    private static final File ROOT_DIRECTORY = new File(System.getProperty("user.dir"));
    private static Settings settings = new Settings();
    private static CmdLineParser parser = new CmdLineParser(settings);
    private static FileSearch fileSearch = new FileSearch();

    public static void main(String[] args) {
        try {
            parser.parseArgument(args);
            fileSearch.searchResult(ROOT_DIRECTORY);
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
        }
    }
}
