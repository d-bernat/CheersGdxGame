package com.gamefactoryx.cheers.model.kongosdrink;

/**
 * Created by Bernat on 20.07.2017.
 */
public enum AvatarType {
    ARGENTINA("argentina", 0),
    AUSTRALIA("australia", 1),
    AUSTRIA("austria", 2),
    BRASIL("brasil", 3),
    BULGARIA("bulgaria", 4),
    CANADA("canada", 5),
    CHILE("chile", 6),
    CHINA("china", 7),
    CROATIA("croatia", 8),
    CZECH("czech", 9),
    DENMARK("denmark", 10),
    EGYPT("egypt", 11),
    FINLAND("finland", 12),
    FRANCE("france", 13),
    GB("gb", 14),
    GERMANY("germany", 15),
    GREECE("greece", 16),
    INDIA("india", 17),
    ITALY("italy", 18),
    JAMAIKA("jamaika", 19),
    KOLUMBIA("kolumbia", 20),
    MEXICO("mexico", 21),
    NETHERLANDS("netherlands", 22),
    POLAND("poland", 23),
    PORTUGAL("portugal", 24),
    RUSSIA("russia", 25),
    SERBIA("serbia", 26),
    SLOVAKIA("slovakia", 27),
    SPAIN("spain", 28),
    SWEDEN("sweden", 29),
    SWITZERLAND("switzerland", 30),
    TUNIS("tunis", 31),
    TURKEY("turkey", 32),
    UKRAINE("ukraine", 33),
    USA("usa", 34),
    COLOUR1("colour1", 35),
    COLOUR2("colour2", 36),
    COLOUR3("colour3", 37),
    COLOUR4("colour4", 38),
    COLOUR5("colour5", 39),
    COLOUR6("colour6", 40),
    COLOUR7("colour7", 41),
    COLOUR8("colour8", 42),
    COLOUR9("colour9", 43),
    COLOUR10("colour10", 44);


    private String name;
    private int value;

    AvatarType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String toString(){
        return name;
    }
    public int value() {
        return this.value;
    }
}

