package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;

import java.util.ListIterator;
import java.util.Scanner;

// class to iterate through each section within a single character
public class AbilityList {
    public enum AbilitySection {
        INFO,
        STATS,
        SKILLS,
        ACTIONS
    }

    private Character character;
    private ListIterator<AbilitySection> sectionIterator;
    CircularLinkedList<AbilitySection> abilitySections = new CircularLinkedList<>();

    public AbilityList(Character character) {
        this.character = character;
        abilitySections.add(AbilitySection.INFO);
        abilitySections.add(AbilitySection.STATS);
        abilitySections.add(AbilitySection.SKILLS);
        abilitySections.add(AbilitySection.ACTIONS);
        sectionIterator = abilitySections.iterator();
    }

    public void showPage(AbilitySection section) {
        switch (section) {
            case INFO:
                System.out.println(character.getInfo());
                break;
            case STATS:
                System.out.println(character.getStats());
                break;
            case SKILLS:
                System.out.println(character.getSkills());
                break;
            case ACTIONS:
                System.out.println(character.formatActions());
                break;
        }
    }

    private boolean moveForward = true;

    public AbilitySection showNextSection() {
        // if !moveForward, go next one to avoid duplicate
        if (!moveForward) {
            sectionIterator.next();
        }
        moveForward = true;
        return sectionIterator.next();
    }

    public AbilitySection showPreviousSection() {
        // if moveForward, go back one to avoid duplicate
        if (moveForward) {
            sectionIterator.previous();
        }
        moveForward = false;
        return sectionIterator.previous();
    }

    // include index of character so finding via index works
    public void showAllSections() {
        for (int i = 0; i < abilitySections.size(); i++) {
            System.out.println("Section Index: " + i + "\n" + abilitySections.get(i));
            System.out.println(character);
        }
    }

    // show character at given index
    public void showSingleSection(Scanner in) {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(in.nextLine());
        System.out.println("Section at index: " + index + "\n" + abilitySections.get(index));
        resetIteratorToIndex(index + 1);
    }

    public void resetIteratorToIndex(int index) {
        int size = abilitySections.size();
        if (size == 0) { return; }
        int normalizedIndex = ((index % size) + size) % size; // works safely with negative indexes
        sectionIterator = abilitySections.iterator();

        for(int i = 0; i < normalizedIndex; i++) {
            sectionIterator.next();
        }
    }

    public void testAbilityList() {
        Scanner in = new Scanner(System.in);
//        sectionIterator = abilitySections.iterator();
        String menuItem = "";

        while (!menuItem.toLowerCase().equals("r")) {
            System.out.println("Next Page <n>, Previous Page <p>, All Pages <a>, Page by Index <i>, Return <r>: ");
            menuItem = in.nextLine();
            AbilitySection section;

            switch (menuItem.toLowerCase()) {
                case "n":
                    section = showNextSection();
                    showPage(section);
                    break;
                case "p":
                    section = showPreviousSection();
                    showPage(section);
                    break;
                case "a":
                    showAllSections();
                    break;
                case "i":
                    showSingleSection(in);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        CharacterList chList = new CharacterList();
        chList.addCharacters();
        Character first = chList.getCharacterList().get(0);
        AbilityList abilityList = new AbilityList(first);
        abilityList.testAbilityList();
    }
}