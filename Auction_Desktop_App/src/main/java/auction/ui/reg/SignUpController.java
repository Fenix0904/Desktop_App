package auction.ui.reg;

import auction.ui.entity.User;
import auction.ui.login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{

    @FXML
    private JFXTextField txfUsername;

    @FXML
    private JFXPasswordField txfPassword;

    @FXML
    private JFXButton btnSingUp;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXPasswordField txfConfirmedPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnMouseClicked(event -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Login");
                stage.setScene(new Scene(parent));
                stage.show();
                ((Stage) btnCancel.getScene().getWindow()).close();
            } catch (IOException ignored) {}
        });

        btnSingUp.setOnMouseClicked(event -> {
            if (txfUsername.getText().equals("")) {
                txfUsername.getStyleClass().add("wrong-credentials");
                return;
            }
            if (!txfPassword.getText().equals(txfConfirmedPassword.getText())) {
                txfConfirmedPassword.getStyleClass().add("wrong-credentials");
                txfPassword.getStyleClass().add("wrong-credentials");
                return;
            }
            User user = new User();
            user.setUsername(txfUsername.getText());
            user.setPassword(txfPassword.getText());
            //user.setConfirmPassword(txfConfirmedPassword.getText());

            try {
                HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/registration")
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(user)
                        .asJson();
                if (jsonResponse.getStatus() == 200) {
                    LoginController controller = new LoginController();
                    ((Stage) btnCancel.getScene().getWindow()).close();
                    controller.loadMain();
                } else {
                    txfUsername.getStyleClass().add("wrong-credentials");
                    txfPassword.getStyleClass().add("wrong-credentials");
                    txfConfirmedPassword.getStyleClass().add("wrong-credentials");
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });
    }
}
