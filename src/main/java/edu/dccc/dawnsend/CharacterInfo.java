package edu.dccc.dawnsend;

public class CharacterInfo {
    String name;
    String race;
    String charClass;
    int level;
    String imgSrc;

    public CharacterInfo(String name, String race, String charClass, int level, String imgSrc) {
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.level = level;
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return "Character\n" +
                "Name: " + name + "\n" +
                "Race: " + race + "\n" +
                "Class: " + charClass + "\n" +
                "Level: " + level + "\n";
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getCharClass() {
        return charClass;
    }

    public int getLevel() {
        return level;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}