package com.un1acker.filesearch.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * un1acker
 * 29.03.2015
 */
public class FIleFilterByName implements FilenameFilter {
    private Pattern pattern;

    public FIleFilterByName(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
