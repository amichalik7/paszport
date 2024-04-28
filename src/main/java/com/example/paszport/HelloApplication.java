package com.example.paszport;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    ImageView obrazek = new ImageView();
    ImageView obrazek2 = new ImageView();
    TextField numer = new TextField();
    TextField imie = new TextField();
    TextField nazwisko = new TextField();
    RadioButton zielone = new RadioButton();
    RadioButton niebieskie = new RadioButton();
    RadioButton piwne = new RadioButton();
    ToggleGroup grupa = new ToggleGroup();
    String kolor = "niebieskie";
    Button ok = new Button();
    Alert wynik = new Alert(Alert.AlertType.NONE, "Wprowadź dane", ButtonType.APPLY);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 751, 309);
        stage.setTitle("Wprowadzenie danych do paszportu. Wykonał:0000000000000");
        stage.setScene(scene);


        numer = (TextField) scene.lookup("#numer");
        imie = (TextField) scene.lookup("#imie");
        nazwisko = (TextField) scene.lookup("#nazwisko");
        zielone = (RadioButton) scene.lookup("#zielone");
        piwne = (RadioButton) scene.lookup("#piwne");
        niebieskie = (RadioButton) scene.lookup("#niebieskie");
        zielone.setToggleGroup(grupa);
        niebieskie.setToggleGroup(grupa);
        piwne.setToggleGroup(grupa);
        ok = (Button) scene.lookup("#ok");

        ok.setOnAction(actionEvent -> {
            String x = nazwisko.getText();
            wynik.setTitle("");
            if (!imie.getText().equals("") && !nazwisko.getText().equals("")) {
                wynik.setContentText(imie.getText() + " " + nazwisko.getText() + " kolor oczu " + kolor);
            }

            wynik.show();
        });

        grupa.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            kolor = ((RadioButton) grupa.getSelectedToggle()).getText();
        });


        obrazek = (ImageView) scene.lookup("#obrazek");
        obrazek2 = (ImageView) scene.lookup(("#obrazek2"));
        numer.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    try {
                        obrazek.setImage(new Image(getClass().getResource(numer.getText() + "-zdjecie.jpg").toString()));
                        obrazek2.setImage(new Image(getClass().getResource(numer.getText() + "-odcisk.jpg").toString()));
                    } catch (Exception e) {
                        obrazek.setImage(null);
                        obrazek2.setImage(null);
                    }
                }
            }
        });





        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}
