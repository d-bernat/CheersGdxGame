package com.gamefactoryx.cheersapp.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class CocktailsModel {


    public static final String[] cocktailNames= {
            "10pm",
            "21stcentury",
            "43peaches",
            "agulone",
            "bloodycarry",
            "bluelagoon",
            "cosmopolitan",
            "darksidemartini",
            "exoticrain",
            "freshup",
            "ginbasilsmash",
            "gintonic",
            "ginwildberry",
            "gorbatschowsummer",
            "kaspar",
            "ladyrose",
            "liftboy",
            "lilacaipirinha",
            "longislandicetea",
            "maitai",
            "manhattan",
            "margarita",
            "millionairesmule",
            "mojito",
            "raspberry",
            "redcarpet",
            "sexonthebeach",
            "sunnymario",
            "vespermartini",
            "whiterussian"
    };
    private static CocktailsModel instance;
    private int page = 1;
    private int maxPages = 8;
    private boolean last, first;
    private List<List<String>> cocktailText = new ArrayList<>();
    private int cocktailToDisplay = -1;

    public static CocktailsModel getInstance() {
        if (instance == null) {
            instance = new CocktailsModel();
        }

        return instance;
    }

    public static CocktailsModel getNewInstance() {
        instance = new CocktailsModel();
        return instance;
    }

    private CocktailsModel() {
        for(String name: cocktailNames) {
            FileHandle cocktailFile = Gdx.files.internal(Configuration.getLanguage() + "/cocktails/" + name + ".txt");
            String text = cocktailFile.readString("UTF-8");
            String[] lines = text.split("\\n");
            if(lines.length > 0){
                List<String> llines = new ArrayList<>();
                for(String line: lines) llines.add(line);
                cocktailText.add(llines);
            }

        }
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        first = page == 1;
        last = page == maxPages;
    }


    public int getMaxPages() {
        return maxPages;
    }

    public boolean isFirstPage() {
        return first;
    }

    public boolean isLastPage() {
        return last;
    }

    public List<List<String>> getCocktailText() {
        return cocktailText;
    }

    public int getCocktailToDisplay() {
        return cocktailToDisplay;
    }

    public void setCocktailToDisplay(int cocktailToDisplay) {
        this.cocktailToDisplay = cocktailToDisplay;
    }
}
