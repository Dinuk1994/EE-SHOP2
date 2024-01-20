package controller;

import bo.BoFactory;
import bo.custom.impl.UserUpdateBoImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.util.BoType;
import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAcountUpdateFormController {

    public AnchorPane pane6;
    public JFXTextField txtSearchEmail;
    public JFXPasswordField txtSearchPassword;
    public JFXTextField txtPassword;
    @FXML
    private Label lblId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    

    UserUpdateBoImpl userUpdateBo= BoFactory.getInstance().getBo(BoType.UPDATE);
    public void setUserData(String email,String password) throws SQLException, ClassNotFoundException {
        txtEmail.setText(email);
        txtPassword.setText(password);


        String userEmail=txtEmail.getText();
        String currentPassword=txtPassword.getText();


        UserDto userDto=new UserDto();
        userDto.setEmail(userEmail);
        userDto.setPrimaryPassword(currentPassword);


        if (userUpdateBo.isFound(userDto)){
            List<UserDto> list=userUpdateBo.allUsers();

            for (UserDto dto:list) {
               if (dto.getEmail().equals(userEmail)){
                   lblId.setText(dto.getUserId());
                   txtName.setText(dto.getName());
                   txtAddress.setText(dto.getAddress());
                   txtContactNumber.setText(String.valueOf(dto.getContactNumber()));
               }

            }

        }

    }


    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane6.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserDashboardForm.fxml"))));
        stage.setResizable(false);
        stage.setTitle("User Dashboard");
        stage.show();

    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane6.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserDashboardForm.fxml"))));
        stage.setResizable(false);
        stage.setTitle("User Dashboard");
        stage.show();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        UserDto userDto=new UserDto(lblId.getText(),txtName.getText(),txtEmail.getText(),txtAddress.getText(),Integer.parseInt(txtContactNumber.getText()),txtPassword.getText());
        if (userUpdateBo.isValidPassword(txtPassword.getText())){
           boolean isUpdate= userUpdateBo.isUpdate(userDto);
           if (isUpdate){
               new Alert(Alert.AlertType.INFORMATION,"User Update Successfull").show();
           }else {
               new Alert(Alert.AlertType.ERROR,"Something Went Wrong").show();
           }
        }
    }


    public void searchBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String searchEmail=txtSearchEmail.getText();
        String searchPassword=txtSearchPassword.getText();

        UserDto userDto=new UserDto();
        userDto.setEmail(searchEmail);
        userDto.setPrimaryPassword(searchPassword);

        if (userUpdateBo.isFound(userDto)){
            List<UserDto> list=userUpdateBo.allUsers();

            for (UserDto dto:list) {
                if (dto.getEmail().equals(searchEmail)){
                    lblId.setText(dto.getUserId());
                    txtName.setText(dto.getName());
                    txtEmail.setText(dto.getEmail());
                    txtAddress.setText(dto.getAddress());
                    txtContactNumber.setText(String.valueOf(dto.getContactNumber()));
                    txtPassword.setText(dto.getPrimaryPassword());
                }
                }

            }else{
            new Alert(Alert.AlertType.ERROR,"Wrong Information!").show();

        }


    }
}
