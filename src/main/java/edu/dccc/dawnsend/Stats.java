package edu.dccc.dawnsend;

import com.sun.tools.javac.Main;

public class Stats {
    private MainStats mainStats;
    private OtherStats otherStats;

    public Stats(MainStats mainStats, OtherStats otherStats) {
        this.mainStats = mainStats;
        this.otherStats = otherStats;
    }

    @Override
    public String toString() {
        return "All Stats\n{" +
                "Main Stats:\n" + mainStats + "\n" +
                "\nOther Stats:\n" + otherStats +
                '}';
    }

    // inner class to get MainStats
    public static class MainStats {
        int str;
        int dex;
        int con;
        int intel;
        int wis;
        int cha;

        public MainStats(int str, int dex, int con, int intel, int wis, int cha) {
            this.str = str;
            this.dex = dex;
            this.con = con;
            this.intel = intel;
            this.wis = wis;
            this.cha = cha;
        }

        @Override
        public String toString() {
            return "Strength: " + str + "\n" +
                    "Dexterity: " + dex + "\n" +
                    "Constitution: " + con + "\n" +
                    "Intelligence: " + intel + "\n" +
                    "Wisdom: " + wis + "\n" +
                    "Charisma: " + cha;
        }

        public int getStr() {
            return str;
        }
        public int getDex() {
            return dex;
        }
        public int getCon() {
            return con;
        }
        public int getIntel() {
            return intel;
        }
        public int getWis() {
            return wis;
        }
        public int getCha() {
            return cha;
        }
    }

    // inner class to get OtherStats
    public static class OtherStats {
        int proficiency;
        int walking;
        int initiative;
        int armorClass;

        public OtherStats(int proficiency, int walking, int initiative, int armorClass) {
            this.proficiency = proficiency;
            this.walking = walking;
            this.initiative = initiative;
            this.armorClass = armorClass;
        }

        @Override
        public String toString() {
            return "Proficiency Bonus: +" + proficiency + "\n" +
                    "Walking Speed: " + walking + " ft.\n" +
                    "Initiative: +" + initiative + "\n" +
                    "Armor Class: " + armorClass;
        }

        public int getProficiency() {
            return proficiency;
        }
        public int getWalking() {
            return walking;
        }
        public int getInitiative() {
            return initiative;
        }
        public int getArmorClass() {
            return armorClass;
        }
    }
}