package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;

import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Set;

public class CharacterList {
    CircularLinkedList<Character> characterList = new CircularLinkedList<>();
    Iterator<Character> iterator = characterList.iterator();

    public void addCharacters() {
        characterList.add(createBarbonk());
        characterList.add(createEB3());
        characterList.add(createGirr());
        characterList.add(createShadark());
        characterList.add(createSoop());
        // initialize iterator after adding characters to avoid modification errors
        iterator = characterList.iterator();
    }

    private Character createBarbonk() {
        // build character info
        CharacterInfo info = new CharacterInfo("The Dred Pirate Barbonk", "Goliath", "Barbarian", 5, "/edu/dccc/dawnsend/images/barbonk.png");

        // build stats
        Stats stats = new Stats(
                new Stats.MainStats(17, 11, 17, 12, 11, 10),
                new Stats.OtherStats(3, 40, 0, 13)
        );

        // build skills
        Set<String> barbonkSkills = new LinkedHashSet<>();
        barbonkSkills.add("Animal Handling");
        barbonkSkills.add("Athletics");
        barbonkSkills.add("Intimidation");
        barbonkSkills.add("Perception");
        barbonkSkills.add("Survival");

        Skills skills = new Skills(
                new Skills.MainSkills(barbonkSkills),
                new Skills.Senses(13, 11, 10)
        );

        // build actions
        Set<Actions> barbonkActions = new LinkedHashSet<>();
        barbonkActions.add(new Actions("Handaxe", 4, 9));
        barbonkActions.add(new Actions("Maul", 5, 15));
        barbonkActions.add(new Actions("Unarmed Strike", 4, 7));
        barbonkActions.add(new Actions("Tavern Brawler Strike", 4, 7));
        barbonkActions.add(new Actions("Stone's Endurance", 1, 12));

        return new Character(info, stats, skills, barbonkActions);
    }

    private Character createEB3() {
        // build character info
        CharacterInfo info = new CharacterInfo("Edmund Boxley the Third (among other aliases)", "Changeling", "Bard", 5, "/edu/dccc/dawnsend/images/eb3.png");

        // build stats
        Stats stats = new Stats(
                new Stats.MainStats(8, 14, 13, 10, 12, 19),
                new Stats.OtherStats(3, 30, 2, 14)
        );

        // build skills
        Set<String> eb3Skills = new LinkedHashSet<>();
        eb3Skills.add("Arcana");
        eb3Skills.add("Athletics");
        eb3Skills.add("Deception");
        eb3Skills.add("History");
        eb3Skills.add("Insight");
        eb3Skills.add("Investigation");
        eb3Skills.add("Perception");
        eb3Skills.add("Performance");
        eb3Skills.add("Persuasion");
        eb3Skills.add("Stealth");

        Skills skills = new Skills(
                new Skills.MainSkills(eb3Skills),
                new Skills.Senses(14, 13, 14)
        );

        // build actions
        Set<Actions> eb3Actions = new LinkedHashSet<>();
        eb3Actions.add(new Actions("Dagger", 3, 6));
        eb3Actions.add(new Actions("Shortsword", 3, 8));
        eb3Actions.add(new Actions("Starry Wisp", 2, 16));
        eb3Actions.add(new Actions("Unarmed Strike", 0, 0));
        eb3Actions.add(new Actions("Bardic Inspiration", 1, 8));
        eb3Actions.add(new Actions("Cutting Words", 1, 8));

        return new Character(info, stats, skills, eb3Actions);
    }

    private Character createGirr() {
        // build character info
        CharacterInfo info = new CharacterInfo("Girr Bunnykix", "Harengon", "Druid", 5, "/edu/dccc/dawnsend/images/girr.png");

        // build stats
        Stats stats = new Stats(
                new Stats.MainStats(10, 14, 14, 10, 19, 8),
                new Stats.OtherStats(3, 30, 5, 18)
        );

        // build skills
        Set<String> girrSkills = new LinkedHashSet<>();
        girrSkills.add("Arcana");
        girrSkills.add("History");
        girrSkills.add("Insight");
        girrSkills.add("Nature");
        girrSkills.add("Perception");

        Skills skills = new Skills(
                new Skills.MainSkills(girrSkills),
                new Skills.Senses(17, 10, 17)
        );

        // build actions
        Set<Actions> girrActions = new LinkedHashSet<>();
        girrActions.add(new Actions("Dagger", 3, 6));
        girrActions.add(new Actions("Quarterstaff", 1, 8));
        girrActions.add(new Actions("Thorn Whip", 2, 12));
        girrActions.add(new Actions("Starry Wisp", 2, 16));
        girrActions.add(new Actions("Unarmed Strike", 1, 1));
        girrActions.add(new Actions("Lucky Footwork", 1, 4));

        return new Character(info, stats, skills, girrActions);
    }

    private Character createShadark() {
        // build character info
        CharacterInfo info = new CharacterInfo("Shadark Ember", "Wood Elf", "Ranger", 5, "/edu/dccc/dawnsend/images/shad.png");

        // build stats
        Stats stats = new Stats(
                new Stats.MainStats(13, 17, 12, 10, 14, 10),
                new Stats.OtherStats(3, 35, 3, 14)
        );

        // build skills
        Set<String> shadSkills = new LinkedHashSet<>();
        shadSkills.add("Arcana");
        shadSkills.add("Athletics");
        shadSkills.add("Investigation");
        shadSkills.add("Nature");
        shadSkills.add("Perception");
        shadSkills.add("Survival");

        Skills skills = new Skills(
                new Skills.MainSkills(shadSkills),
                new Skills.Senses(15, 13, 12)
        );

        // build actions
        Set<Actions> shadActions = new LinkedHashSet<>();
        shadActions.add(new Actions("Longbow", 4, 11));
        shadActions.add(new Actions("Shortsword", 4, 9));
        shadActions.add(new Actions("Shortsword of Life Stealing", 4, 10));
        shadActions.add(new Actions("Unarmed Strike", 2, 2));

        return new Character(info, stats, skills, shadActions);
    }

    private Character createSoop() {
        // build character info
        CharacterInfo info = new CharacterInfo("Soop the Slinky", "Bugbear", "Rogue", 5, "/edu/dccc/dawnsend/images/soop.png");

        // build stats
        Stats stats = new Stats(
                new Stats.MainStats(8, 20, 14, 13, 12, 10),
                new Stats.OtherStats(3, 30, 8, 17)
        );

        // build skills
        Set<String> soopSkills = new LinkedHashSet<>();
        soopSkills.add("Acrobatics");
        soopSkills.add("Deception");
        soopSkills.add("Insight");
        soopSkills.add("Investigation");
        soopSkills.add("Perception");
        soopSkills.add("Sleight of Hand");
        soopSkills.add("Stealth");

        Skills skills = new Skills(
                new Skills.MainSkills(soopSkills),
                new Skills.Senses(14, 14, 14)
        );

        // build actions
        Set<Actions> soopActions = new LinkedHashSet<>();
        soopActions.add(new Actions("Dagger", 6, 9));
        soopActions.add(new Actions("Shortbow", 6, 11));
        soopActions.add(new Actions("Shortsword", 6, 11));
        soopActions.add(new Actions("Unarmed Strike", 0, 0));
        soopActions.add(new Actions("Surprise Attack", 2, 12));
        soopActions.add(new Actions("Sneak Attack", 3, 18));

        return new Character(info, stats, skills, soopActions);
    }

    public CircularLinkedList<Character> getCharacterList() {
        return characterList;
    }

    // add iterator methods and testing
}