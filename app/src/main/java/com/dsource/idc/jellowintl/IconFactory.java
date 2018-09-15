package com.dsource.idc.jellowintl;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class IconFactory {

    private static final String EXTENSION = ".png";
    private static final char EXTENSION_SEPARATOR = '.';

    // L3 : "[0-9]{6}([1-9][0-9]{3}|[0-9][1-9][0-9]{2}|[0-9]{2}[1-9][0-9]|[0-9]{3}[1-9])GG"
    //  or "\d{6}([1-9]\d{3}|\d{3}[1-9]|\d{2}[1-9]\d|\d[1-9]\d{2})GG"
    //  Java version : "\\d{6}([1-9]\\d{3}|\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2})GG"
    // L2 : "[0-9]{4}([1-9][1-9]|[0-9][1-9]|[1-9][0-9])0{4}GG"
    // or "[0-9]{4}([1-9][1-9]|[0][1-9]|[1-9][0])0{4}GG"
    // L1 : "[0-9]{4}0{6}GG"

    /**
     * @param dir
     * @param langCode
     */

    public static String[] getL1Icons(@NonNull File dir, String langCode) {

        String regex = langCode + "[0-9]{2}" + "0{6}GG" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] level1Icons = new String[iconNames.size()];

        iconNames.toArray(level1Icons);

        return level1Icons;
    }


    public static String[] getL2Icons(@NonNull File dir, String langCode, String level1IconCode) {

        String regex = langCode + level1IconCode +
                "([1-9][1-9]|[0-9][1-9]|[1-9][0-9])" + "0{4}GG" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] level2Icons = new String[iconNames.size()];

        iconNames.toArray(level2Icons);

        return level2Icons;
    }


    public static String[] getL3Icons(@NonNull File dir, String langCode, String level1IconCode, String level2IconCode) {

        String regex = langCode + level1IconCode + level2IconCode +
                "([1-9][0-9]{3}|[0-9][1-9][0-9]{2}|[0-9]{2}[1-9][0-9]|[0-9]{3}[1-9])GG" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] level3Icons = new String[iconNames.size()];

        iconNames.toArray(level3Icons);

        return level3Icons;
    }


    public static String[] getL2SeqIcons(@NonNull File dir, String langCode, String level1IconCode) {
        String regex = langCode + level1IconCode + "([1-9][1-9]|[0-9][1-9]|[1-9][0-9])" + "0{4}SS" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] level2SeqIcons = new String[iconNames.size()];

        iconNames.toArray(level2SeqIcons);

        return level2SeqIcons;
    }

    public static String[] getL3SeqIcons(@NonNull File dir, String langCode, String level1IconCode, String level2IconCode) {
        String regex = langCode + level1IconCode + level2IconCode +
                "([1-9][0-9]{3}|[0-9][1-9][0-9]{2}|[0-9]{2}[1-9][0-9]|[0-9]{3}[1-9])SS" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] level3SeqIcons = new String[iconNames.size()];

        iconNames.toArray(level3SeqIcons);

        return level3SeqIcons;
    }

    public static String[] getExpressiveIcons(@NonNull File dir, String langCode) {

        String regex = langCode + "[0-9]{2}" + "EE" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] expressiveIcons = new String[iconNames.size()];

        iconNames.toArray(expressiveIcons);

        return expressiveIcons;
    }


    public static String[] getMiscellaneousIcons(@NonNull File dir, String langCode) {

        String regex = langCode + "[0-9]{2}" + "MS" + EXTENSION;
        ArrayList<String> iconNames = new ArrayList<>();
        for (String fileName : dir.list()) {
            if (fileName.matches(regex)) {
                iconNames.add(fileName);
            }
        }

        Collections.sort(iconNames);

        String[] miscellaneousIcons = new String[iconNames.size()];

        iconNames.toArray(miscellaneousIcons);

        return miscellaneousIcons;
    }


    public static String[] removeFileExtension(String[] iconNamesWithExtension) {
        String[] iconNames = iconNamesWithExtension.clone();
        for (int i = 0; i < iconNames.length; i++) {
            iconNames[i] = iconNames[i].
                    substring(0, iconNames[i].lastIndexOf(EXTENSION_SEPARATOR));
        }
        return iconNames;
    }

}
