package com.gamefactoryx.cheers.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bernat on 18.05.2017.
 */
public class PlayerNameCache {
    private static final Map<Integer, String> nameList = new HashMap<>();

    public static void addName(String name, int position){
        nameList.put(position, name);
    }
    public static String getName(int position){
        if(position < nameList.size())
            return nameList.get(position);
        else
            return "";
    }
}
