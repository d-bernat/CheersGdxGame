package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bernat on 02.06.2017.
 */
public class FunnyNameGenerator {

    private static List<String> nameList = new ArrayList<>();

    private static void createFunnyNames() {
        if(nameList.size() == 0) {
            FileHandle nameFile = Gdx.files.internal(Configuration.getLanguage() + "/Busdrivingscreen/names.txt");
            String fileContent = nameFile.readString();
            String[] names = fileContent.split("\n");
            nameList = Arrays.asList(names);
            Collections.shuffle(nameList);
        }
    }

    public static String getFunnyName(int index){
        if(nameList.size() == 0)
            createFunnyNames();
        if(index >= nameList.size())
            return null;
        return nameList.get(index);
    }
}
