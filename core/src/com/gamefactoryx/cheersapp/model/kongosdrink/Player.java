package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.gamefactoryx.cheersapp.model.Subject;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bernat on 18.07.2017.
 */
public class Player {
    private int position;
    private boolean enable;
    private boolean active;
    private float rotate;
    private boolean finished;
    private List<Subject> subjects = new ArrayList<>();

    public Player( Subject subject) {

        this.subjects.add(subject);
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public List<Subject> getSubjects(){
        return subjects;
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

    public com.gamefactoryx.cheersapp.model.kongosdrink.AvatarType getAvatar() {
        return subjects.get(0).getAvatar();
    }

    public void setAvatar(com.gamefactoryx.cheersapp.model.kongosdrink.AvatarType avatar) {
        subjects.get(0).setAvatar( avatar );
    }

    public String getName() {
        return subjects.get(0).getName();
    }
    public void setName(String name){
        subjects.get(0).setName(name);
    }
    public Subject.Sex getSex() {
        return subjects.get(0).getSex();
    }
    public void setSex(Subject.Sex sex){
        subjects.get(0).setSex(sex);
    }

    public Subject.Type getSubjectType(){
        return subjects.get(0).getType();
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
