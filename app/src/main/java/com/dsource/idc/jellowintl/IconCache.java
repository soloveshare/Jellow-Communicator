package com.dsource.idc.jellowintl;

import java.util.HashMap;

public class IconCache {
    public static String[] ExpressiveIcons;
    public static String[] L1Icons;
    public static HashMap<String, String[]> AllL2Icons;
    public static HashMap<String, String[]> L3Icons;
    public static HashMap<String, String[]> L3SeqIcons;
    public static String[] MiscellaneousIcons;

    //public static HashMap<String, String[]> L2Icons;
    //public static HashMap<String, String[]> L2SeqIcons;

    public static void clearIconCache() {
        L1Icons = null;
        AllL2Icons = null;
        L3Icons = null;
        L3SeqIcons = null;
        ExpressiveIcons = null;
        MiscellaneousIcons = null;

        //L2Icons = null;
        //L2SeqIcons = null;
    }
}
