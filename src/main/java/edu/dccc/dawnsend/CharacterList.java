package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;

import java.util.*;

public class CharacterList {
    CircularLinkedList<Character> characterList = new CircularLinkedList<>();
    ListIterator<Character> iterator = characterList.iterator();

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

    // create boolean to track next and previous use
    // if true, we just moved forward, if false, we moved back
    // this is so that the same character doesn't get shown again when we switch directions
    private boolean moveForward = true;

    public Character showNextChar() {
        // if !moveForward, go next one to avoid duplicate
        if (!moveForward) {
            iterator.next();
        }
        moveForward = true;
        return iterator.next();
    }

    public Character showPreviousChar() {
        // if moveForward, go back one to avoid duplicate
        if (moveForward) {
            iterator.previous();
        }
        moveForward = false;
        return iterator.previous();
    }

    // include index of character so finding via index works
    public void showAllCharacters() {
        for (int i = 0; i < characterList.size(); i++) {
            System.out.println("Character Index: " + i + "\n" + characterList.get(i));
        }
    }

    // show character at given index
    public void showSingleCharacter(Scanner in) {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(in.nextLine());
        System.out.println("Character at index: " + index + "\n" + characterList.get(index));
        resetIteratorToIndex(index + 1);
    }

    // change iterator to given index so next character is after that index
    public void resetIteratorToIndex(int index) {
        int size = characterList.size();
        if (size == 0) { return; }
        int normalizedIndex = ((index % size) + size) % size; // works safely with negative indexes
        iterator = characterList.iterator();

        for(int i = 0; i < normalizedIndex; i++) {
            iterator.next();
        }
    }

    public void testCharacterList() {
        Scanner in = new Scanner(System.in);
//        iterator = characterList.iterator();
        String menuItem = "";


        while (!menuItem.toLowerCase().equals("q")) {
            System.out.println("Next Character <n>, Previous Character <p>, All Characters <a>, Character by Index <i>, Quit <q>: ");
            menuItem = in.nextLine();

            switch (menuItem.toLowerCase()) {
                case "n":
                    Character nCh = showNextChar();

                    System.out.println(nCh);
                    break;
                case "p":
                    Character pCh = showPreviousChar();
                    System.out.println(pCh);
                    break;
                case "a":
                    showAllCharacters();
                    break;
                case "i":
                    showSingleCharacter(in);
            }
        }
        System.out.println("Thank you for viewing the Dawn's End campaign characters!");
    }

    public static void main(String[] args) {
        CharacterList chList = new CharacterList();
        chList.addCharacters();
        chList.testCharacterList();
    }
}