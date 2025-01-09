package server;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class serverscene extends AnchorPane {

    protected final Label Scene_title;
    protected final AmbientLight ambientLight;
    protected final Button Start;



    public serverscene(Stage stage) {

        Scene_title = new Label();
        ambientLight = new AmbientLight();
        Start = new Button();

      

        setId("AnchorPane");
        setPrefHeight(377.0);
        setPrefWidth(484.0);
        setStyle("-fx-background-color: Bb8141;");

        AnchorPane.setLeftAnchor(Scene_title, 66.0);
        AnchorPane.setRightAnchor(Scene_title, 80.0);
        AnchorPane.setTopAnchor(Scene_title, 21.0);
        Scene_title.setAlignment(javafx.geometry.Pos.CENTER);
        Scene_title.setPrefHeight(123.0);
        Scene_title.setPrefWidth(338.0);
        Scene_title.setStyle("-fx-font-size: 29;");
        Scene_title.setText("Shick-Shack-Shock");
        Scene_title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Scene_title.setTextFill(javafx.scene.paint.Color.valueOf("#fffdd0"));
        Scene_title.setFont(new Font("Copperplate Gothic Light", 15.0));

        ambientLight.setColor(javafx.scene.paint.Color.CHARTREUSE);
        ambientLight.setLightOn(true);
        Scene_title.setGraphic(ambientLight);

        Start.setPrefHeight(49.0);
        Start.setPrefWidth(201.0);
        Start.setStyle(
            "-fx-background-color: #fffdd0;" +
            "-fx-background-radius: 30;" +
            "-fx-background-insets: 0;" +
            "-fx-text-fill: #C08A5B;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4, 0, 1, 1);" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );
        Start.setText("Start");

        setButtonPosition(getWidth());

        widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            setButtonPosition(newValue.doubleValue());
        });

        Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Serverscene2Base server = new Serverscene2Base(stage);
                Scene scene = new Scene(server);
                stage.setScene(scene);
            }   
        });

        getChildren().add(Scene_title);
        getChildren().add(Start);
    }

    private void setButtonPosition(double width) {
        double buttonWidth = Start.getPrefWidth();
        double xPosition = (width - buttonWidth) / 2;
        AnchorPane.setLeftAnchor(Start, xPosition);
        AnchorPane.setTopAnchor(Start, 207.0);
    }
}
