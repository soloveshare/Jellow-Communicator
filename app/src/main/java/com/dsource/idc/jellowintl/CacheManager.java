package com.dsource.idc.jellowintl;

import static com.dsource.idc.jellowintl.IconCache.clearIconCache;
import static com.dsource.idc.jellowintl.MemoryCache.clearMemoryCache;
import static com.dsource.idc.jellowintl.PathFactory.clearPathCache;

public class CacheManager {
    public static void clearCache(){
        clearIconCache();
        clearPathCache();
        clearMemoryCache();
    }
}
