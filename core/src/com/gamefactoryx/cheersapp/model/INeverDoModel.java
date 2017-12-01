package com.gamefactoryx.cheersapp.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoModel extends Model {

    private static Configuration.LanguageEnum lastLanguage;

    private String[] tasks_mixed;
    private String[] tasks_18plus;
    private String[] tasks_standard;
    private String rulesText;


    public String getLine() {

        int mixed_max = Configuration.isPremium() ? instance.tasks_mixed.length : Math.min(100, instance.tasks_mixed.length);
        int plus_max = Configuration.isPremium() ? instance.tasks_18plus.length : Math.min(100, instance.tasks_18plus.length);
        int standard_max = Configuration.isPremium() ? instance.tasks_standard.length : Math.min(100, instance.tasks_standard.length);

        if (listTasksMixed.size() == 0) {
            for (int i = 0; i < mixed_max; i++) {
                listTasksMixed.add(i);
            }
            Collections.shuffle(listTasksMixed);
        }
        if (listTasks18plus.size() == 0) {
            for (int i = 0; i < plus_max; i++) {
                listTasks18plus.add(i);
            }
            Collections.shuffle(listTasks18plus);
        }
        if (listTasksStandard.size() == 0) {
            for (int i = 0; i < standard_max; i++) {
                listTasksStandard.add(i);
            }
            Collections.shuffle(listTasksStandard);
        }

        switch (Configuration.getINeverDoGameType()) {
            case GAME_MIXED:
                return tasks_mixed[listTasksMixed.remove(listTasksMixed.size() - 1)];
            case GAME_STANDARD:
                return tasks_standard[listTasksStandard.remove(listTasksStandard.size() - 1)];
            case GAME_18PLUS:
                return tasks_18plus[listTasks18plus.remove(listTasks18plus.size() - 1)];
            default:
                return tasks_mixed[listTasksMixed.remove(listTasksMixed.size() - 1)];
        }
    }

    private void setTasksMixed(String[] tasks) {
        listTasksMixed.clear();
        this.tasks_mixed = tasks;
    }

    private void setTasks18plus(String[] tasks) {
        listTasks18plus.clear();
        this.tasks_18plus = tasks;
    }

    private void setTasksStandard(String[] tasks) {
        listTasksStandard.clear();
        this.tasks_standard = tasks;
    }

    private static INeverDoModel instance;
    private static List<Integer> listTasksMixed = new ArrayList<>();
    private static List<Integer> listTasks18plus = new ArrayList<>();
    private static List<Integer> listTasksStandard = new ArrayList<>();

    public static INeverDoModel getInstance() {
        if (instance == null) {
            instance = new INeverDoModel();
            Configuration.setINeverDoGameType(Configuration.getINeverDoGameType());
            instance.initTasks();
        }
        return instance;
    }

    public static INeverDoModel getNewInstance() {
        lastLanguage = null;
        instance = new INeverDoModel();
        Configuration.setINeverDoGameType(Configuration.getINeverDoGameType());
        instance.initTasks();
        return instance;
    }

    @Override
    public String getRulesText() {
        return rulesText;
    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/iNeverDoScreen/rules.txt").readString();
    }


    private INeverDoModel() {
    }

    private void initTasks() {

        if (lastLanguage == null || lastLanguage != Configuration.getLanguage()) {
            FileHandle taskFile = Gdx.files.internal(Configuration.getLanguage() + "/iNeverDoScreen/tasks_mixed.txt");
            setTasksMixed(taskFile.readString().split("\\n"));
            taskFile = Gdx.files.internal(Configuration.getLanguage() + "/iNeverDoScreen/tasks_18+.txt");
            setTasks18plus(taskFile.readString().split("\\n"));
            taskFile = Gdx.files.internal(Configuration.getLanguage() + "/iNeverDoScreen/tasks_standart.txt");
            setTasksStandard(taskFile.readString().split("\\n"));
        }
        lastLanguage = Configuration.getLanguage();
    }

}
