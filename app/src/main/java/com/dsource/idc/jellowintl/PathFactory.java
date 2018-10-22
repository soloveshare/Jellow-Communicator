package com.dsource.idc.jellowintl;

import android.content.Context;

import com.dsource.idc.jellowintl.utility.SessionManager;

import java.io.File;

public class PathFactory {

    public static final String JSON_FILE = "map.json";
    public static String basePath;
    public static File iconDirectory;
    public static File jsonMap;

    public static String getIconPath(Context context, String iconName){
        return getIconPath(context) + iconName;
    }

    public static String getIconPath(Context context){
        if(basePath == null){
            String dirName = new SessionManager(context).getLanguage();
            String path = context.getDir(dirName, Context.MODE_PRIVATE).getAbsolutePath();
            basePath = path + "/";
        }
        return basePath;
    }

    public static File getIconDirectory(Context context){
        if(iconDirectory == null){
            String dirName = new SessionManager(context).getLanguage();
            iconDirectory = context.getDir(dirName, Context.MODE_PRIVATE);
        }
        return  iconDirectory;
    }

    public static File getJSONFile(Context context){
        if(jsonMap == null){
            String dirName = new SessionManager(context).getLanguage();
            String path = context.getDir(dirName, Context.MODE_PRIVATE).getAbsolutePath();
            jsonMap = new File(path + "/" + JSON_FILE);
        }
        return jsonMap;
    }

    public static void clearPathCache(){
        basePath = null;
        iconDirectory = null;
        jsonMap = null;
    }
}
