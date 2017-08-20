package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.graphics.Texture;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;


/**
 * Created by Bernat on 18.07.2017.
 */
public class Player {
    private int position;
    private boolean enable;
    private boolean active;
    private float rotate;
    private boolean finished;
    private Subject subject;

    public Player( Subject subject) {
        this.subject = subject;
    }

    public int getPosition() {
        return position;
    }

    public float getNormPosition() {
        return (position - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public AvatarType getAvatar() {
        return subject.getAvatar();
    }

    public String getName() {
        return subject.getName();
    }
    public void setName(String name){
        subject.setName(name);
    }
    public Subject.Sex getSex() {
        return subject.getSex();
    }
    public void setSex(Subject.Sex sex){
        subject.setSex(sex);
    }

    public Subject.Type getSubjectType(){
        return subject.getType();
    }
    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
