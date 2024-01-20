package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboardFormController {

    public Label lblTime;
    public Label lblDate;
    @FXML
    private AnchorPane pane1;

    public void initialize(){
        calculateTime();
    }


    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        ), new KeyFrame(Duration.seconds(1)));
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();
    }


    @FXML
    void regBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserRegisterForm.fxml"))));
        stage.setResizable(false);
        stage.setTitle("User Registration Form");
        stage.show();
    }

    @FXML
    void reportsBtnOnAction(ActionEvent event) {

    }


}
