package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UserDashboardFormController {

    @FXML
    private AnchorPane pane5;
    private String userEmail;
    private String password;

    public void setUserData(String userEmail,String password){
        this.userEmail=userEmail;
        this.password=password;
    }

    @FXML
    void inventoryBtnOnAction(ActionEvent event) {

    }

    @FXML
    void orderDetailsBtnOnAction(ActionEvent event) {

    }

    @FXML
    void placeOrderBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane5.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/placeOrderForm.fxml"))));
        stage.setTitle("Place Order Form");
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void userAcountBtnOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/UserAcountUpdateForm.fxml"));
        Parent root=loader.load();

        UserAcountUpdateFormController userAcountUpdateFormController=loader.getController();
        userAcountUpdateFormController.setUserData(userEmail,password);

        Stage stage = (Stage) pane5.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("User Account Update");
        stage.setResizable(false);
        stage.show();

    }

    public void addNewCustomerBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane5.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/NewCustomer.fxml"))));
        stage.setTitle("Add New Customer Form");
        stage.setResizable(false);
        stage.show();

    }
}
