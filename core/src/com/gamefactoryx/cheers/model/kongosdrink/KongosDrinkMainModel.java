package com.gamefactoryx.cheers.model.kongosdrink;

/**
 * Created by Bernat on 10.07.2017.
 */
public class KongosDrinkMainModel {
    private static KongosDrinkMainModel instance;
    private int index;
    private int xxcoor;
    private int xcoor;
   // private OrderedMap<Integer, String> getTopScorers = new OrderedMap<>();

    public static KongosDrinkMainModel getInstance() {
        if (instance == null) {
            instance = new KongosDrinkMainModel();
        }

        return instance;
    }

    public static KongosDrinkMainModel getNewInstance() {
        instance = new KongosDrinkMainModel();
        return instance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getXcoor() {
        return xcoor;
    }

    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    public int getXxcoor() {
        return xxcoor;
    }

    public void setXxcoor(int xxcoor) {
        this.xxcoor = xxcoor;
    }
    /* public void put(int score, String name){
        FileHandle fHandle = Gdx.files.local("high_score.txt");
        String old_content = "";
        if(fHandle.exists()){
            old_content = fHandle.readString();
        }

        fHandle.writeString(createContent(old_content, score, name), false);
    }

    public List<String> get(){
        List<String> ret = new ArrayList<>();
        FileHandle fHandle = Gdx.files.local("high_score.txt");
        if(fHandle.exists()){
            String content = fHandle.readString();
            for(String s: content.split(";"))
                ret.add(s);
        }

        return ret;
    }

    private String createContent(String old_content, int score, String name){
        List<String> scores = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for(String s: old_content.split(";")) {
            scores.add(s);
        }

        scores.add(Integer.toString(score) + ":" + name);
        Collections.sort(scores, new Comparator<String>(){

            @Override
            public int compare(String s1, String s2) {
                int score1, score2;
                try {
                    score1 = Integer.parseInt(s1.split(":")[0]);
                }catch(Exception e){
                    score1 = 0;
                }
                try {
                    score2 = Integer.parseInt(s2.split(":")[0]);
                }catch(Exception e){
                    score2 = 0;
                }

                return score2 - score1;
            }
        });

        int counter = 1;
        for(String s: scores){
            result.append(";" + s);
            if(counter == Configuration.getMaxTopScorers())
                break;
            else
                ++counter;
        }

        result.delete(0, 1);
        return result.toString();
    }*/
}
