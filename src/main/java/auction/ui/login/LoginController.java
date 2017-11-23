package auction.ui.login;

import auction.ui.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private JFXTextField txfUsername;

    @FXML
    private JFXPasswordField txfPassword;
    @FXML
    private Label label;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws UnirestException, JsonProcessingException {
        String username = txfUsername.getText();
        String password = txfPassword.getText();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/login")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(user)
                .asJson();

        if (jsonResponse.getStatus() == 200) {
            closeStage();
            loadMain();
        } else {
            txfUsername.getStyleClass().add("wrong-credentials");
            txfPassword.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(parent));
            stage.show();
            closeStage();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    public void closeStage() {
        ((Stage) txfUsername.getScene().getWindow()).close();
    }

    public void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {

        }
    }


}
