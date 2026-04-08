package edu.dccc.dawnsend;

import edu.dccc.store.CircularLinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DawnsEndFX extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Dawn's End - Dungeons & Dragons Campaign");
        Scene scene = new Scene(label, 400, 200);
        stage.setTitle("Character Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}