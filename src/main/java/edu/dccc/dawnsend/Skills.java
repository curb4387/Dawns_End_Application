package edu.dccc.dawnsend;

import java.util.Set;

public class Skills {
    private MainSkills mainSkills;
    private Senses senses;

    public Skills(MainSkills mainSkills, Senses senses) {
        this.mainSkills = mainSkills;
        this.senses = senses;
    }

    @Override
    public String toString() {
        return "ALL SKILLS\n----------\n" +
                "Main Skills:\n" + mainSkills + "\n" +
                "Senses:\n" + senses + "\n";
    }

    public MainSkills getMainSkills() {
        return mainSkills;
    }

    public Senses getSenses() {
        return senses;
    }

    // inner class to get Skills
    public static class MainSkills {
        Set<String> mainSkills;

        public MainSkills(Set<String> mainSkills) {
            this.mainSkills = mainSkills;
        }

        @Override
        public String toString() {
            String mainString = "";
            for (String skill : mainSkills) {
                mainString += skill + "\n";
            }
            return mainString;
        }

        public Set<String> getMainSkills() {
            return mainSkills;
        }
    }

    // inner class to get Senses
    public static class Senses {
        int passPercept;
        int passInvest;
        int passInsight;

        public Senses(int passPercept, int passInvest, int passInsight) {
            this.passPercept = passPercept;
            this.passInvest = passInvest;
            this.passInsight = passInsight;
        }

        @Override
        public String toString() {
            return "Passive Perception: " + passPercept + "\n" +
                    "Passive Investigation: " + passInvest + "\n" +
                    "Passive Insight: " + passInsight;
        }

        public int getPassPercept() {
            return passPercept;
        }

        public int getPassInvest() {
            return passInvest;
        }

        public int getPassInsight() {
            return passInsight;
        }
    }
}