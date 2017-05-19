package com.gamefactoryx.cheers.model.bus_driving;

/**
 * Created by bernat on 17.05.2017.
 */
public class Card {
    private String name;
    private int value;
    private CardType type;
    private Integer cardIndex;
    private String file;
    private CardOrientation orientation;
   // private final CardSize cardSize;

    public enum CardType{
        CLUB("clubs"), DIAMOND("diamond"), HEART("heart"), SPADE("spades");
        private String value;
        CardType(String value){
            this.value = value;

        }
        public String value(){
            return this.value;
        }

    }

    public enum CardSize{
        SMALL("_pyramid_side"), BIG("");
        private String value;
        CardSize(String value){
            this.value = value;

        }
        public String value(){
            return this.value;
        }

    }
    public enum CardOrientation{
        FACE("face"), BACK("back");
        private String value;
        CardOrientation(String value){
            this.value = value;

        }
        public String value(){
            return this.value;
        }

    }

    public Card(int index, CardSize cardSize){
        this.cardIndex = index;

       // this.cardSize = cardSize;
        this.orientation = CardOrientation.BACK;

        String prefix;
        if(index >= 2 && index <=14) {
            type = CardType.HEART;
            prefix = "heart";
        }
        else if(index >=15 && index <= 27) {
            type = CardType.SPADE;
            prefix = "spades";
        }
        else if(index >=28 && index <= 40) {
            type = CardType.DIAMOND;
            prefix = "diamond";
        }
        else{
            type = CardType.CLUB;
            prefix = "clubs";
        }


        switch(index){
            case 2:
            case 15:
            case 28:
            case 41:
                value = 2;
                name = "2";
                break;
            case 3:
            case 16:
            case 29:
            case 42:
                value = 3;
                name = "3";
                break;
            case 4:
            case 17:
            case 30:
            case 43:
                value = 4;
                name = "4";
                break;
            case 5:
            case 18:
            case 31:
            case 44:
                value = 5;
                name = "5";
                break;
            case 6:
            case 19:
            case 32:
            case 45:
                value = 6;
                name = "6";
                break;
            case 7:
            case 20:
            case 33:
            case 46:
                value = 7;
                name = "7";
                break;
            case 8:
            case 21:
            case 34:
            case 47:
                value = 8;
                name = "8";
                break;
            case 9:
            case 22:
            case 35:
            case 48:
                value = 9;
                name = "9";
                break;
            case 10:
            case 23:
            case 36:
            case 49:
                value = 10;
                name = "10";
                break;
            case 11:
            case 24:
            case 37:
            case 50:
                value = 11;
                name = "b";
                break;
            case 12:
            case 25:
            case 38:
            case 51:
                value = 12;
                name = "d";
                break;
            case 13:
            case 26:
            case 39:
            case 52:
                value = 13;
                name = "k";
                break;
            case 14:
            case 27:
            case 40:
            case 53:
                value = 14;
                name = "a";
                break;
        }


//        file = "common/busdriving_cards/" + prefix + cardSize.value() + "/" + type.value() + "-" + name + ".png";

    }
    public String getName() {
        return name;
    }

    public CardType getType(){
        return this.type;
    }

    public int getValue() {
        return value;
    }


    public String getFileName(CardSize cardSize) {
        String prefix;

        if(cardIndex >= 2 && cardIndex <=14) {
            type = CardType.HEART;
            prefix = "heart";
        }
        else if(cardIndex >=15 && cardIndex <= 27) {
            type = CardType.SPADE;
            prefix = "spades";
        }
        else if(cardIndex >=28 && cardIndex <= 40) {
            type = CardType.DIAMOND;
            prefix = "diamond";
        }
        else{
            type = CardType.CLUB;
            prefix = "clubs";
        }
        return  "common/busdriving_cards/" + prefix + cardSize.value() + "/" + type.value() + "_" + name + ".png";
    }

    public CardOrientation getOrientation() {
        return orientation;
    }
}
