package com.gamefactoryx.cheersapp.model;

import com.gamefactoryx.cheersapp.model.kongosdrink.AvatarType;

public class Subject {
    public enum Sex {MALE, FEMALE, DONT_CARE}
    public enum Type {PERSON, TEAM}
    private Sex sex;
    private String name;
    private AvatarType avatar;
    private Type type;

    public Subject(String name, Sex sex, Type type, AvatarType avatar) {
        this.sex = sex;
        this.name = name;
        this.type = type;
        this.avatar = avatar;
    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public AvatarType getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(AvatarType avatar) {
        this.avatar = avatar;
    }
}
