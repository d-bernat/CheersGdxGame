package com.gamefactoryx.cheers.model.kongosdrink;

/**
 * Created by Bernat on 20.07.2017.
 */
public enum AvatarType {
    ARGENTINA("argentina", 0),
    AUSTRALIA("australia"),
    AUSTRIA("austria"),
    BRASIL("brasil"),
    BULGARIA("bulgaria"),
    CANADA("canada"),
    CHILE("chile"),
    CHINA("china"),
    COLOUR1("colour1"),
    COLOUR2("colour2"),
    COLOUR3("colour3"),
    COLOUR4("colour4"),
    CROATIA("croatia"),
    CZECH("czech"),
    DENMARK("denmark"),
    EGYPT("egypt"),
    FINLAND("finland"),
    FRANCE("france"),
    GB("gb"),
    GERMANY("germany"),
    GREECE("greece"),
    INDIA("india"),
    ITALY("italy"),
    JAMAIKA("jamaika"),
    KOLUMBIA("kolumbia"),
    MEXICO("mexico"),
    NETHERLANDS("netherlands"),
    POLAND("poland"),
    PORTUGAL("portugal"),
    RUSSIA("russia"),
    SERBIA("serbia"),
    SLOVAKIA("slovakia"),
    SPAIN("spain"),
    SWEDEN("sweden"),
    SWITZERLAND("switzerland"),
    TUNIS("tunis"),
    TURKEY("turkey"),
    UKRAINE("ukraine"),
    USA("usa");


    private String name;
    private int value;
    private int last_value;

    AvatarType(String name) {
        this.name = name;
        this.value = ++last_value;
    }

    AvatarType(String name, int value) {
        this.name = name;
        this.value = value;
        last_value = value;
    }

    public String toString(){
        return name;
    }
    public int value() {
        return this.value;
    }
}

