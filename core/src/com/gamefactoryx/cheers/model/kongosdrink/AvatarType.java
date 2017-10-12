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
    COLOUR1("colour1", 8),
    COLOUR2("colour2", 9),
    COLOUR3("colour3", 10),
    COLOUR4("colour4", 11),
    CROATIA("croatia", 12),
    CZECH("czech", 13),
    DENMARK("denmark", 14),
    EGYPT("egypt", 15),
    FINLAND("finland", 16),
    FRANCE("france", 17),
    GB("gb", 18),
    GERMANY("germany", 19),
    GREECE("greece", 20),
    INDIA("india", 21),
    ITALY("italy", 22),
    JAMAIKA("jamaika", 23),
    KOLUMBIA("kolumbia", 24),
    MEXICO("mexico", 25),
    NETHERLANDS("netherlands", 26),
    POLAND("poland", 27),
    PORTUGAL("portugal", 28),
    RUSSIA("russia", 29),
    SERBIA("serbia", 30),
    SLOVAKIA("slovakia", 31),
    SPAIN("spain", 32),
    SWEDEN("sweden", 33),
    SWITZERLAND("switzerland", 34),
    TUNIS("tunis", 35),
    TURKEY("turkey", 36),
    UKRAINE("ukraine", 37),
    USA("usa", 38);


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

