package com.dsource.idc.jellowintl;

import android.content.Context;

import com.dsource.idc.jellowintl.utility.SessionManager;

import java.io.File;

public class PathFactory {

    public static final String JSON_FILE = "map.json";

    public static String getIconPath(Context context, String iconName){
        String dirName = new SessionManager(context).getLanguage();
        String path = context.getDir(dirName, Context.MODE_PRIVATE).getAbsolutePath();
        return path + "/" + iconName;
    }

    public static File getIconDirectory(Context context){
        String dirName = new SessionManager(context).getLanguage();
        return context.getDir(dirName, Context.MODE_PRIVATE);
    }

    public static File getJSONFile(Context context){
        String dirName = new SessionManager(context).getLanguage();
        String path = context.getDir(dirName, Context.MODE_PRIVATE).getAbsolutePath();
        return new File(path + "/" + JSON_FILE);
    }


}
