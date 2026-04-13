package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class DawnsEndFX extends Application {
    private final CharacterList characters = new CharacterList();
    private CircularLinkedList<Character> characterList;
    private ListIterator<Character> iterator;
    private boolean moveForward = true;
    private boolean nextCharacter = false;
    private AbilityList abilityList;
    private AbilityList.AbilitySection currentSection;
    private Character currentCharacter;

    // UI components
    GridPane details = new GridPane();
    private final Label nameLabel = new Label();
    private final ImageView characterImage = new ImageView();
    private final ImageView borderImage = new ImageView();
    private final Label sectionLabel = new Label();

    @Override
    public void start(Stage stage) {
        characters.addCharacters();
        characterList = characters.getCharacterList();
        iterator = characterList.iterator();

        /** set up view */
        // Top: header
        // nameLabel styles
        nameLabel.setFont(Font.font("Serif", FontWeight.BOLD, 25));
        nameLabel.setTextFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setColor(Color.BLACK);
        nameLabel.setEffect(ds);

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

        // add checkbox for moving to next page with timer
        CheckBox autoBtn = new CheckBox("Auto");
        // use to align button and checkbox
        Region emptyBox1 = new Region();
        Region emptyBox2 = new Region();
        VBox buttonsWrapper = new VBox(emptyBox1, nextSectionBtn, emptyBox2, autoBtn);

        // align nextSectionBtn in center vertically and align horizontally with prevSectionBtn
        buttonsWrapper.setAlignment(Pos.CENTER);
        VBox.setVgrow(emptyBox1, Priority.ALWAYS);
        VBox.setVgrow(emptyBox2, Priority.ALWAYS);
        VBox.setMargin(nextSectionBtn, new Insets(18, 0, 0, 0));

        // add wrapper box around details content to include section buttons
        HBox detailsWrapper = new HBox(10, prevSectionBtn, centerBox, buttonsWrapper);
        HBox.setHgrow(centerBox, Priority.ALWAYS); // grow horizontally so buttons stay in place
        centerBox.setMaxWidth(Double.MAX_VALUE);
        // align section buttons in center
        detailsWrapper.setAlignment(Pos.CENTER);
        detailsWrapper.setPadding(new Insets(5));
        detailsWrapper.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8);"
        );

        // Left: character image and border on top of image
        characterImage.setFitWidth(200);
        characterImage.setFitHeight(220);
//        characterImage.setPreserveRatio(true);
        characterImage.setSmooth(true);
        borderImage.setFitWidth(250);
        borderImage.setFitHeight(270);
        borderImage.setSmooth(true);

        // clip for characterImage to round out corners
        Rectangle clip = new Rectangle(characterImage.getFitWidth(), characterImage.getFitHeight());
        clip.setArcWidth(70);
        clip.setArcHeight(70);
        characterImage.setClip(clip);

        StackPane imageBox = new StackPane(characterImage, borderImage);
        imageBox.setAlignment(Pos.CENTER);

        // Bottom: buttons
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");
        prevButton.setPrefWidth(100);
        nextButton.setPrefWidth(100);
        prevButton.setFont(new Font(14));
        nextButton.setFont(new Font(14));

        // logo between buttons
        ImageView logoImage = new ImageView();

        try {
            Image logo = new Image("/edu/dccc/dawnsend/images/logo.png");
            logoImage.setImage(logo);
        } catch (Exception e) {
            System.out.println("Error loading image");
        }

        logoImage.setPreserveRatio(true);
        logoImage.setFitWidth(60);

        HBox buttonBar = new HBox(10, prevButton, logoImage, nextButton);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(15));

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(imageBox);
        root.setCenter(detailsWrapper);
        root.setBottom(buttonBar);
        root.setPadding(new Insets(10));

        // add background image
        try {
            Image background = new Image("/edu/dccc/dawnsend/images/background.jpg");
            BackgroundSize bgSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
            BackgroundImage backgroundImg = new BackgroundImage(background, null, null, BackgroundPosition.CENTER, bgSize);
            root.setBackground(new Background(backgroundImg));
        } catch (Exception e) {
            System.out.println("Error loading image");
        }

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

        // auto checkbox
        Timeline autoIterate;
        autoIterate = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    if (abilityList != null) {
                        if (nextCharacter) {
                            if (iterator.hasNext()) {
                                updateDisplay(iterator.next());
                            }
                            nextCharacter = false;
                            return;
                        }
                        currentSection = abilityList.showNextSection();
                        updateSectionDisplay(getCurrentCharacter(), currentSection);
                        if (currentSection == AbilityList.AbilitySection.ACTIONS) {
                            nextCharacter = true;
                        }
                    }
                })
        );
        autoIterate.setCycleCount(Timeline.INDEFINITE); // auto iterate as long as box is checked

        autoBtn.setOnAction(e -> {
            if (autoBtn.isSelected()) {
                autoIterate.play();
            } else {
                autoIterate.stop();
            }
        });

        /** Scene and Stage */
        Scene scene = new Scene(root, 850, 550);

        // icon for app
        try {
            Image appImg = new Image("/edu/dccc/dawnsend/images/appimg.png");
            ImageView appLogo = new ImageView(appImg);
            appLogo.setPreserveRatio(true);
            appLogo.setFitWidth(5);
            appImg = appLogo.getImage();
            stage.getIcons().add(appImg);
        } catch (Exception e) {
            System.out.println("Error loading image");
        }

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
                value.setFont(Font.font("Serif", FontWeight.BOLD, 18));
            } else {
                Label key = new Label(pair[0] + " ");
                Label value = new Label(pair[1]);
                key.setFont(Font.font(14));
                value.setFont(Font.font(14));

                grid.add(key, 1, row);
                grid.add(value, 2, row);
            }
            row++;
        }
    }

    // method to populate grid for Skills section
    private void populateSkillsGrid(Character character) {
        details.getChildren().clear();

        // 2 headers
        Label skillsHeader = new Label("SKILLS");
        Label sensesHeader = new Label("SENSES");
        skillsHeader.setFont(Font.font("Serif", FontWeight.BOLD, 18));
        sensesHeader.setFont(Font.font("Serif", FontWeight.BOLD, 18));
        // add headers
        details.add(skillsHeader, 1, 0);
        details.add(sensesHeader, 2, 0);

        // left column - Skills
        int rowSkills = 1;
        for (String skill : character.getSkills().getMainSkills().getMainSkills()) {
            Label s = new Label(skill);
            s.setFont(Font.font(14));
            details.add(s, 1, rowSkills++);
        }

        // right column - Senses
        int rowSenses = 1;
        Label percep = new Label("Passive Perception:     " + String.valueOf(character.getSkills().getSenses().getPassPercept()));
        Label invest = new Label("Passive Investigation:  " + String.valueOf(character.getSkills().getSenses().getPassInvest()));
        Label insight = new Label("Passive Insight:            " + String.valueOf(character.getSkills().getSenses().getPassInsight()));

        percep.setFont(Font.font(14));
        invest.setFont(Font.font(14));
        insight.setFont(Font.font(14));

        details.add(percep, 2, rowSenses++);
        details.add(invest, 2, rowSenses++);
        details.add(insight, 2, rowSenses++);
    }


    // add data to grid
    private List<String[]> buildSection(Character character, AbilityList.AbilitySection section) {
        List<String[]> data = new ArrayList<>();

        switch (section) {
            case INFO:
                data.add(new String[]{"CHARACTER INFO"});
                data.add(new String[]{"Name:", character.getInfo().getName()});
                data.add(new String[]{"Race:", character.getInfo().getRace()});
                data.add(new String[]{"Class:", character.getInfo().getCharClass()});
                data.add(new String[]{"Level:", String.valueOf(character.getInfo().getLevel())});
                break;

            case STATS:
                data.add(new String[]{"STATS"});
                data.add(new String[]{"Strength:", String.valueOf(character.getStats().getMainStats().getStr())});
                data.add(new String[]{"Dexterity:", String.valueOf(character.getStats().getMainStats().getDex())});
                data.add(new String[]{"Constitution:", String.valueOf(character.getStats().getMainStats().getCon())});
                data.add(new String[]{"Intelligence:", String.valueOf(character.getStats().getMainStats().getIntel())});
                data.add(new String[]{"Wisdom:", String.valueOf(character.getStats().getMainStats().getWis())});
                data.add(new String[]{"Charisma:", String.valueOf(character.getStats().getMainStats().getCha())});

                data.add(new String[]{"----------"});

                data.add(new String[]{"Proficiency Bonus:", "+" + String.valueOf(character.getStats().getOtherStats().getProficiency())});
                data.add(new String[]{"Walking Speed:", String.valueOf(character.getStats().getOtherStats().getWalking()) + " ft."});
                data.add(new String[]{"Initiative:", "+" + String.valueOf(character.getStats().getOtherStats().getInitiative())});
                data.add(new String[]{"Armor Class:", String.valueOf(character.getStats().getOtherStats().getArmorClass())});
                break;

            case SKILLS:
                // format included in populateSkillsGrid() and updated in updateSectionDisplay()
                break;

            case ACTIONS:
                data.add(new String[]{"ACTIONS"});
                data.add(new String[]{"Attack", "Damage"});

                for (Actions action : character.getActions()) {
                    String attack = action.getAttack();
                    String dmg = "";
                    if (action.getMinDamage() == action.getMaxDamage()) {
                        dmg = String.valueOf(action.getMaxDamage());
                    } else {
                        dmg = String.valueOf(action.getMinDamage()) + " - " + String.valueOf(action.getMaxDamage());
                    }
                    data.add(new String[]{attack, dmg});
                }
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
            Image borderImg = new Image("/edu/dccc/dawnsend/images/imageborder.png");
            borderImage.setImage(borderImg);
        } catch (Exception e) {
            System.err.println("Error loading image: " + character.getInfo().getImgSrc());
        }
    }

    private void updateSectionDisplay(Character character, AbilityList.AbilitySection section) {
        // set column widths according to section
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        if (section == AbilityList.AbilitySection.SKILLS) {
            col1.setPercentWidth(0);
            col2.setPercentWidth(40);
            col3.setPercentWidth(40);
            details.getColumnConstraints().setAll(col1, col2, col3);
            populateSkillsGrid(character);
        } else {
            // reset column constraints so other section content don't move
            col1.setPercentWidth(-1);
            col2.setPercentWidth(-1);
            col3.setPercentWidth(-1);
            details.getColumnConstraints().setAll(col1, col2, col3);
            List<String[]> data = buildSection(character, section);
            populateGrid(details, data);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}