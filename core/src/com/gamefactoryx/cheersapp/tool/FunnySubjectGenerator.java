package com.gamefactoryx.cheersapp.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheersapp.model.Subject;
import com.gamefactoryx.cheersapp.model.kongosdrink.AvatarType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bernat on 02.06.2017.
 */
public class FunnySubjectGenerator {

    private static List<Subject> subjects = new ArrayList<>();

    private static void createFunnySubjects() {
        if (subjects.size() == 0) {
            FileHandle nameFile = Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/names.txt");
            String fileContent = nameFile.readString();
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                String[] subject = line.split(";");
                if (subject.length == 4) {
                    Subject.Sex sex;
                    switch (subject[0].trim()) {
                        case "M":
                            sex = Subject.Sex.MALE;
                            break;
                        case "F":
                            sex = Subject.Sex.FEMALE;
                            break;
                        default:
                            sex = Subject.Sex.DONT_CARE;
                    }

                    Subject.Type type;
                    switch (subject[3].trim()) {
                        case "P":
                            type = Subject.Type.PERSON;
                            break;
                        case "T":
                            type = Subject.Type.TEAM;
                            break;
                        default:
                            type = Subject.Type.PERSON;
                            break;
                    }

                    AvatarType avatar;
                    switch (subject[2].trim()) {
                        case "ARGENTINA":
                            avatar = AvatarType.ARGENTINA;
                            break;
                        case "AUSTRALIA":
                            avatar = AvatarType.AUSTRALIA;
                            break;
                        case "AUSTRIA":
                            avatar = AvatarType.AUSTRIA;
                            break;
                        case "BRASIL":
                            avatar = AvatarType.BRASIL;
                            break;
                        case "BULGARIA":
                            avatar = AvatarType.BULGARIA;
                            break;
                        case "CANADA":
                            avatar = AvatarType.CANADA;
                            break;
                        case "CHILE":
                            avatar = AvatarType.CHILE;
                            break;
                        case "CHINA":
                            avatar = AvatarType.CHINA;
                            break;
                        case "COLOUR1":
                            avatar = AvatarType.COLOUR1;
                            break;
                        case "COLOUR2":
                            avatar = AvatarType.COLOUR2;
                            break;
                        case "COLOUR3":
                            avatar = AvatarType.COLOUR3;
                            break;
                        case "COLOUR4":
                            avatar = AvatarType.COLOUR4;
                            break;
                        case "CROATIA":
                            avatar = AvatarType.CROATIA;
                            break;
                        case "CZECH":
                            avatar = AvatarType.CZECH;
                            break;
                        case "DENMARK":
                            avatar = AvatarType.DENMARK;
                            break;
                        case "EGYPT":
                            avatar = AvatarType.EGYPT;
                            break;
                        case "FINLAND":
                            avatar = AvatarType.FINLAND;
                            break;
                        case "FRANCE":
                            avatar = AvatarType.FRANCE;
                            break;
                        case "GB":
                            avatar = AvatarType.GB;
                            break;
                        case "GERMANY":
                            avatar = AvatarType.GERMANY;
                            break;
                        case "GREECE":
                            avatar = AvatarType.GREECE;
                            break;
                        case "INDIA":
                            avatar = AvatarType.INDIA;
                            break;
                        case "ITALY":
                            avatar = AvatarType.ITALY;
                            break;
                        case "JAMAIKA":
                            avatar = AvatarType.JAMAIKA;
                            break;
                        case "KOLUMBIA":
                            avatar = AvatarType.KOLUMBIA;
                            break;
                        case "MEXICO":
                            avatar = AvatarType.MEXICO;
                            break;
                        case "NETHERLANDS":
                            avatar = AvatarType.NETHERLANDS;
                            break;
                        case "POLAND":
                            avatar = AvatarType.POLAND;
                            break;
                        case "PORTUGAL":
                            avatar = AvatarType.PORTUGAL;
                            break;
                        case "RUSSIA":
                            avatar = AvatarType.RUSSIA;
                            break;
                        case "SERBIA":
                            avatar = AvatarType.SERBIA;
                            break;
                        case "SLOVAKIA":
                            avatar = AvatarType.SLOVAKIA;
                            break;
                        case "SPAIN":
                            avatar = AvatarType.SPAIN;
                            break;
                        case "SWEDEN":
                            avatar = AvatarType.SWEDEN;
                            break;
                        case "SWITZERLAND":
                            avatar = AvatarType.SWITZERLAND;
                            break;
                        case "TUNIS":
                            avatar = AvatarType.TUNIS;
                            break;
                        case "TURKEY":
                            avatar = AvatarType.TURKEY;
                            break;
                        case "UKRAINE":
                            avatar = AvatarType.UKRAINE;
                            break;
                        case "USA":
                            avatar = AvatarType.USA;
                            break;
                        default:
                            avatar = AvatarType.COLOUR2;
                            break;

                    }
                    subjects.add(new Subject(subject[1].trim(), sex, type, avatar));

                }
            }
        }
        Collections.shuffle(subjects);
    }

    public static Subject getFunnySubject(int index) {
        if (subjects.size() == 0)
            createFunnySubjects();
        if (index >= subjects.size())
            return null;
        return subjects.get(index);
    }
}
