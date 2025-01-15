package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Serverscene2Base extends AnchorPane {

    Button stop_server_btn = new Button("Stop");

    public Serverscene2Base(Stage stage) {

        setStyle("-fx-background-color: #Bb8141;"); // Background color

        // Create PieChart
        PieChart pieChart = new PieChart();
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Online people", 40),
                new PieChart.Data("Offline people", 35),
                new PieChart.Data("Available for play", 25)
        );

        pieChart.setData(pieData);
        pieChart.setTitle("Friends Status");
        pieChart.setLabelsVisible(true);
        pieChart.setLegendVisible(true);

        // Add PieChart to the AnchorPane
        getChildren().add(pieChart);

        // Add a Back Button at the top
        //stop_server_btn = new Button("Stop");
        stop_server_btn.setLayoutX(50);
        stop_server_btn.setLayoutY(50);
        stop_server_btn.setStyle(
                "-fx-background-color: #C08A5B;"
                + "-fx-background-radius: 15;"
                + "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: white;"
        );

        // Set the action to switch back to Scene 1 (Main Menu)
        getChildren().add(stop_server_btn);

        // Center the PieChart on the screen (stage)
        this.widthProperty().addListener((obs, oldVal, newVal) -> centerPieChart(pieChart));
        this.heightProperty().addListener((obs, oldVal, newVal) -> centerPieChart(pieChart));

        /* stop_server_btn.addEventHandler(ActionEvent.ACTION,(event) -> {
            System.out.println("stop button clicked");
        });*/
    }

    // Method to center the PieChart in the middle of the screen
    private void centerPieChart(PieChart pieChart) {
        // Ensure that the stage is fully loaded before centering
        double stageWidth = getScene().getWindow().getWidth();
        double stageHeight = getScene().getWindow().getHeight();

        double pieChartWidth = pieChart.getWidth();
        double pieChartHeight = pieChart.getHeight();

        double centerX = (stageWidth - pieChartWidth) / 2;
        double centerY = (stageHeight - pieChartHeight) / 2;

        pieChart.setLayoutX(centerX);
        pieChart.setLayoutY(centerY);
    }

}
