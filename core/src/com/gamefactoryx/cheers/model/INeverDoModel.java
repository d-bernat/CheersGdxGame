package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoModel extends Model{

    private static com.gamefactoryx.cheers.tool.Configuration.LanguageEnum lastLanguage;

    private String[] tasks_mixed;
    private String[] tasks_18plus;
    private String[] tasks_standard;
    private String rulesText;

    public String getLine() {
        if(listTasksMixed.size() == 0){
            for(int i = 0; i < instance.tasks_mixed.length; i++){
                listTasksMixed.add(i);
            }
            Collections.shuffle(listTasksMixed);
        }
        if(listTasks18plus.size() == 0){
            for(int i = 0; i < instance.tasks_18plus.length; i++){
                listTasks18plus.add(i);
            }
            Collections.shuffle(listTasks18plus);
        }
        if(listTasksStandard.size() == 0){
            for(int i = 0; i < instance.tasks_standard.length; i++){
                listTasksStandard.add(i);
            }
            Collections.shuffle(listTasksStandard);
        }

        switch(Configuration.getINeverDoGameType()){
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

    private void setTasksMixed(String[] tasks){
        this.tasks_mixed = tasks;
    }
    private void setTasks18plus(String[] tasks){
        this.tasks_18plus = tasks;
    }
    private void setTasksStandard(String[] tasks){
        this.tasks_standard = tasks;
    }

    private static INeverDoModel instance;
    private static List<Integer> listTasksMixed = new ArrayList<>();
    private static List<Integer> listTasks18plus = new ArrayList<>();
    private static List<Integer> listTasksStandard= new ArrayList<>();

    public static INeverDoModel getInstance(){
        if(instance == null ) {
            instance = new INeverDoModel();
            Configuration.setINeverDoGameType(Configuration.INeverDoGameTypeEnum.GAME_STANDARD);

        }


        lastLanguage = com.gamefactoryx.cheers.tool.Configuration.getLanguage();
        return instance;
    }

    @Override
    public String getRulesText() {
        return rulesText;
    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/iNeverDoScreen/rules.txt").readString();
    }


    private INeverDoModel(){
        if(lastLanguage == null || lastLanguage != com.gamefactoryx.cheers.tool.Configuration.getLanguage()) {
            FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/iNeverDoScreen/tasks_mixed.txt");
            setTasksMixed(taskFile.readString().split("\\n"));
            taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/iNeverDoScreen/tasks_18+.txt");
            setTasks18plus(taskFile.readString().split("\\n"));
            taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/iNeverDoScreen/tasks_standart.txt");
            setTasksStandard(taskFile.readString().split("\\n"));
        }

    }

}
