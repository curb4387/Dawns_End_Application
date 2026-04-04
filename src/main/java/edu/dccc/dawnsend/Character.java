package edu.dccc.dawnsend;

import java.util.Set;

public class Character {
    // each Character has info, stats, skills, actions
    private CharacterInfo info;
    private Stats stats;
    private Skills skills;
    private Set<Actions> actions;

    public Character(CharacterInfo info, Stats stats, Skills skills, Set<Actions> actions) {
        this.info = info;
        this.stats = stats;
        this.skills = skills;
        this.actions = actions;
    }

    public String formatActions() {
        String actionsString = "ACTIONS\n----------\n";
        for (Actions action : actions) {
            actionsString += action.toString();
        }
        return actionsString;
    }

    @Override
    public String toString() {
        return info.toString() + "\n" +
                stats.toString() + "\n" +
                skills.toString() + "\n" +
                "Actions\n" + formatActions();
    }

    public CharacterInfo getInfo() {
        return info;
    }

    public Stats getStats() {
        return stats;
    }

    public Skills getSkills() {
        return skills;
    }

    public Set<Actions> getActions() {
        return actions;
    }
}