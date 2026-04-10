package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DawnsEndFX extends Application {
    private final CharacterList characters = new CharacterList();
    private CircularLinkedList<Character> characterList;
    private ListIterator<Character> iterator;
    private boolean moveForward = true;
    private AbilityList abilityList;
    private AbilityList.AbilitySection currentSection;
    private Character currentCharacter;

    // UI components
    GridPane details = new GridPane();
    private final Label nameLabel = new Label();
    private final ImageView characterImage = new ImageView();
    private final Label sectionLabel = new Label();

    @Override
    public void start(Stage stage) {
        characters.addCharacters();
        characterList = characters.getCharacterList();
        iterator = characterList.iterator();

        /** set up view */
        // Top: header
        nameLabel.setFont(Font.font("Serif", FontWeight.BOLD, 22));
        HBox topBar = new HBox(nameLabel);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(15));

        // Middle: details
        details.setAlignment(Pos.CENTER_LEFT);
        details.setHgap(20);
        details.setVgap(5);
        VBox centerBox = new VBox(10, sectionLabel, details);
        centerBox.setPadding(new Insets(10));

        // Left/Right: left and right section buttons
        Button prevSectionBtn = new Button("⮜"); // unicode: U+2B9C
        Button nextSectionBtn = new Button("⮞"); // unicode: U+2B9E
        prevSectionBtn.setPrefWidth(50);
        nextSectionBtn.setPrefWidth(50);

        // Left: image
        characterImage.setFitWidth(200);
        characterImage.setPreserveRatio(true);
        characterImage.setSmooth(true);
        VBox imageBox = new VBox(characterImage);
        imageBox.setPadding(new Insets(10));
        imageBox.setAlignment(Pos.TOP_CENTER);

        // Bottom: buttons
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");
        prevButton.setPrefWidth(100);
        nextButton.setPrefWidth(100);
        HBox buttonBar = new HBox(20, prevButton, nextButton);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(15));

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(imageBox);
        root.setCenter(centerBox);
        root.setBottom(buttonBar);
        root.setPadding(new Insets(10));

        /** Controller logic */
        prevButton.setOnAction(e -> {
            if (moveForward) {
                iterator.previous();
            }
            moveForward = false;

            if (iterator.hasPrevious()) {
                updateDisplay(iterator.previous());
            }
        });

        nextButton.setOnAction(e -> {
            if (!moveForward) {
                iterator.next();
            }
            moveForward = true;

            if (iterator.hasNext()) {
                updateDisplay(iterator.next());
            }
        });

        // initialize first character
        if (iterator.hasNext()) {
            updateDisplay(iterator.next());
        }

        // character info buttons
        prevSectionBtn.setOnAction(e -> {
            if (abilityList != null) {
                currentSection = abilityList.showPreviousSection();
                updateSectionDisplay(getCurrentCharacter(), currentSection);
            }
        });

        nextSectionBtn.setOnAction(e -> {
            if (abilityList != null) {
                currentSection = abilityList.showNextSection();
                updateSectionDisplay(getCurrentCharacter(), currentSection);
            }
        });

        /** Scene and Stage */
        Scene scene = new Scene(root, 800, 480);
        stage.setTitle("Dawn's End - Dungeons & Dragons Campaign");
        stage.setScene(scene);
        stage.show();
    }

    private Character getCurrentCharacter() {
        return currentCharacter;
    }

    // method to populate grid for each page to dynamically add content
    // instead of hardcoding and clearing each label each time
    private void populateGrid(GridPane grid, List<String[]> data) {
        grid.getChildren().clear(); // clear grid of previous content

        int row = 0;
        for (String[] pair : data) {
            if (pair.length == 1) {
                Label value = new Label(pair[0]);
                grid.add(value, 1, row, 2, 1); // 2, 1 makes it span 2 columns
            } else {
                Label key = new Label(pair[0] + ":");
                Label value = new Label(pair[1]);

                grid.add(key, 1, row);
                grid.add(value, 2, row);
            }
            row++;
        }
    }

    // add data to grid
    private List<String[]> buildSection(Character character, AbilityList.AbilitySection section) {
        List<String[]> data = new ArrayList<>();

        switch (section) {
            case INFO:
                data.add(new String[]{"CHARACTER INFO"});
                data.add(new String[]{"Name", character.getInfo().getName()});
                data.add(new String[]{"Race", character.getInfo().getRace()});
                data.add(new String[]{"Class", character.getInfo().getCharClass()});
                data.add(new String[]{"Level", String.valueOf(character.getInfo().getLevel())});
                break;

            case STATS:
                data.add(new String[]{"STATS"});
                data.add(new String[]{"Strength", String.valueOf(character.getStats().getMainStats().getStr())});
                data.add(new String[]{"Dexterity", String.valueOf(character.getStats().getMainStats().getDex())});
                data.add(new String[]{"Constitution", String.valueOf(character.getStats().getMainStats().getCon())});
                data.add(new String[]{"Intelligence", String.valueOf(character.getStats().getMainStats().getIntel())});
                data.add(new String[]{"Wisdom", String.valueOf(character.getStats().getMainStats().getWis())});
                data.add(new String[]{"Charisma", String.valueOf(character.getStats().getMainStats().getCha())});

                data.add(new String[]{"----------"});

                data.add(new String[]{"Proficiency Bonus", "+" + String.valueOf(character.getStats().getOtherStats().getProficiency())});
                data.add(new String[]{"Walking Speed", String.valueOf(character.getStats().getOtherStats().getWalking()) + " ft."});
                data.add(new String[]{"Initiative", "+" + String.valueOf(character.getStats().getOtherStats().getInitiative())});
                data.add(new String[]{"Armor Class", String.valueOf(character.getStats().getOtherStats().getArmorClass())});
                break;

            case SKILLS:
                data.add(new String[]{"SKILLS"});
                data.add(new String[]{character.getSkills().getMainSkills().toString()});

                data.add(new String[]{"----------"});

                data.add(new String[]{"SENSES"});
                data.add(new String[]{character.getSkills().getSenses().toString()});
                break;

            case ACTIONS:
                data.add(new String[]{"ACTIONS"});
                data.add(new String[]{character.formatActions()});
                break;
        }

        return data;
    }

    // method to display characters and info
    private void updateDisplay(Character character) {
        abilityList = new AbilityList(character);
        currentSection = abilityList.showNextSection();
        updateSectionDisplay(character, currentSection);
        nameLabel.setText(character.getInfo().getName());
        currentCharacter = character;

        // try to load image
        try {
            Image img = new Image(getClass().getResourceAsStream(character.getInfo().getImgSrc()));
            characterImage.setImage(img);
        } catch (Exception e) {
            System.err.println("Error loading image: " + character.getInfo().getImgSrc());
        }
    }

    private void updateSectionDisplay(Character character, AbilityList.AbilitySection section) {
        sectionLabel.setText(section.toString());

        List<String[]> data = buildSection(character, section);
        populateGrid(details, data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}