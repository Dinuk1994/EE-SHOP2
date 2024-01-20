package controller;

import bo.BoFactory;
import bo.custom.LoginBo;
import bo.custom.UserRegBo;
import com.jfoenix.controls.JFXTextField;
import dao.DaoFactory;
import dao.custom.LoginDao;
import dao.custom.UserRegDao;
import dao.util.BoType;
import dao.util.DaoType;
import dto.UserDto;
import entity.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginFormController {
    public AnchorPane pane;
    public JFXTextField txtPassword;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtEmail;

    LoginBo loginBo=BoFactory.getInstance().getBo(BoType.LOGIN);
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

    public void adminLoginBtnOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtPassword.getText().isEmpty() && !txtEmail.getText().isEmpty()) {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashboardForm.fxml"))));
            stage.setTitle("Dashboard - Admin");
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fill the Required Blanks");
            alert.show();
        }
    }

    public void userLoginBtnOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String passWord = txtPassword.getText();

        if (!email.isEmpty() && !passWord.isEmpty()) {
            try {
                UserDto userDto = new UserDto();
                userDto.setEmail(email);
                userDto.setPrimaryPassword(passWord);

                if (loginBo.isFound(userDto)) {

                    FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/UserDashboardForm.fxml"));
                    Parent root=loader.load();

                    UserDashboardFormController userDashboardFormController = loader.getController();
                    userDashboardFormController.setUserData(email,passWord);


                    new Alert(Alert.AlertType.INFORMATION,"User Logged Successfully").show();
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("User Dashboard Form");
                    stage.setResizable(false);
                    stage.show();
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Email Address or Password");
                    alert.show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fill the Required Blanks");
            alert.show();
        }
    }

}

