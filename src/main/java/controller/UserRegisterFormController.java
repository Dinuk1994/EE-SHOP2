package controller;

import bo.BoFactory;
import bo.custom.UserRegBo;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dao.DaoFactory;
import dao.custom.UserRegDao;
import dao.util.BoType;
import dao.util.DaoType;
import dto.UserDto;
import dto.tm.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRegisterFormController {

    public JFXButton newUserBtnOnAction;
    @FXML
    private AnchorPane pane2;

    @FXML
    private Label lblId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTreeTableView<UserTm> tblUser;

    @FXML
    private TreeTableColumn<?, ?> colId;

    @FXML
    private TreeTableColumn<?, ?> colUserName;

    @FXML
    private TreeTableColumn<?, ?> colUserEmail;

    @FXML
    private TreeTableColumn<?, ?> colContactNumber;

    @FXML
    private TreeTableColumn<?, ?> colAddress;

    @FXML
    private TreeTableColumn<?, ?> colPrimaryPassword;

    @FXML
    private TreeTableColumn<?, ?> colOption;

    @FXML
    private JFXPasswordField txtPassword;

    UserRegDao userRegDao= DaoFactory.getInstance().getDao(DaoType.USER);
    UserRegBo userRegBo= BoFactory.getInstance().getBo(BoType.USER);

    public void initialize(){
        generateId();
        loadUserRegTable();
        colId.setCellValueFactory(new TreeItemPropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        colUserEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new TreeItemPropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new TreeItemPropertyValueFactory<>("contactNumber"));
        colPrimaryPassword.setCellValueFactory(new TreeItemPropertyValueFactory<>("primaryPassword"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));

        tblUser.getSelectionModel().selectedItemProperty().addListener((observableValue, userTmTreeItem, newValue) -> {
            setData(newValue);
        });

    }

    private void setData(TreeItem<UserTm> newValue) {
        if (newValue!=null){
            lblId.setText(newValue.getValue().getUserId());
            txtName.setText(newValue.getValue().getName());
            txtEmail.setText(newValue.getValue().getEmail());
            txtAddress.setText(newValue.getValue().getAddress());
            txtNumber.setText(String.valueOf(newValue.getValue().getContactNumber()));
            txtPassword.setText(String.valueOf(newValue.getValue().getPrimaryPassword()));
        }
    }

    private void loadUserRegTable() {
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();

        try {
            List<UserDto> dtoList = userRegBo.allUsers();
            if (dtoList != null) {
                for (UserDto dto : dtoList) {
                    JFXButton btn = new JFXButton("Delete");
                    btn.setStyle("-fx-background-color: #EF6262;");
                    UserTm userTm = new UserTm(
                            dto.getUserId(),
                            dto.getName(),
                            dto.getEmail(),
                            dto.getAddress(),
                            dto.getContactNumber(),
                            dto.getPrimaryPassword(),
                            btn
                    );
                    btn.setOnAction(actionEvent -> {
                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmationAlert.setHeaderText("Confirm Deletion");
                        confirmationAlert.setContentText("Do you want to delete the user " + userTm.getUserId() + "?");

                        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                        confirmationAlert.getButtonTypes().setAll(yesButton, noButton);

                        Optional<ButtonType> result = confirmationAlert.showAndWait();

                        if (result.isPresent() && result.get() == yesButton) {
                            deleteUser(userTm.getUserId());
                        }
                    });
                    userTms.add(userTm);
                }

                RecursiveTreeItem<UserTm> treeItem = new RecursiveTreeItem<>(userTms, RecursiveTreeObject::getChildren);
                tblUser.setRoot(treeItem);
                tblUser.setShowRoot(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void deleteUser(String userId)  {
        try {
            boolean isDelete = userRegBo.deleteUser(userId);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "User Deleted Complete").show();
                loadUserRegTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"User Delete Failed").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public void dashboardBtnOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashboardForm.fxml"))));
        stage.setTitle("Dashboard - Admin");
        stage.setResizable(false);
        stage.show();
    }

    public void reportsBtnOnAction(javafx.event.ActionEvent actionEvent) {
    }

    public void regBtnOnAction(javafx.event.ActionEvent actionEvent)  {
        try {
            UserDto userDto=new UserDto(lblId.getText(), txtName.getText(),txtEmail.getText(),txtAddress.getText(),Integer.parseInt(txtNumber.getText()),txtPassword.getText());
                        boolean isSaved = userRegBo.saveUser(userDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"User Registered").show();
                loadUserRegTable();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void backBtnOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashboardForm.fxml"))));
        stage.setTitle("Dashboard - Admin");
        stage.setResizable(false);
        stage.show();
    }


    public void generateId(){
        try {
            UserDto userDto=userRegDao.lastUser();
            if (userDto!=null){
                String userId= userDto.getUserId();
                int num=Integer.parseInt(userId.split("U")[1]);
                num++;
                lblId.setText(String.format("U%03d",num));
            }else{
                lblId.setText("U001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateBtnOnAction(ActionEvent actionEvent)  {
        try {
            UserDto userDto=new UserDto(lblId.getText(), txtName.getText(),txtEmail.getText(),txtAddress.getText(),Integer.parseInt(txtNumber.getText()),txtPassword.getText());
            boolean isUpdate=userRegBo.updateUser(userDto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"User Details Updated").show();
                loadUserRegTable();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void newUserBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) pane2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserRegisterForm.fxml"))));
        stage.setTitle("User Registration Form");
        stage.setResizable(false);
        stage.show();
    }
}
