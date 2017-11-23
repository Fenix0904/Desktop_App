package auction.ui.main;

import auction.ui.entity.Auction;
import auction.ui.entity.Contract;
import auction.ui.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private JFXButton btnApplyContract;

    @FXML
    private JFXListView<String> userJFXListView;

    /*
    @FXML
    void load(ActionEvent event) throws UnirestException, IOException {
        userJFXListView.setExpanded(true);
        userJFXListView.depthProperty().setValue(1);

        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/all")
                .asJson();

        List<User> persons = new ObjectMapper().readValue(jsonResponse.getBody().toString(), new TypeReference<List<User>>(){});

        for (User person : persons) {
            userJFXListView.getItems().add(person.getUsername());
        }
    }
*/
    @FXML
    void load(ActionEvent event) throws UnirestException, IOException {
        userJFXListView.setExpanded(true);
        userJFXListView.depthProperty().setValue(1);

        new Thread(() -> {

            HttpResponse<JsonNode> jsonResponse = null;
            try {
                jsonResponse = Unirest.get("http://localhost:8080/all")
                        .asJson();

                List<Auction> persons = new ObjectMapper().readValue(jsonResponse.getBody().toString(), new TypeReference<List<Auction>>() {
                });
                Platform.runLater(() -> {
                    for (Auction person : persons) {
                        userJFXListView.getItems().add(person.getAuctionStatus().getStatusName());
                    }
                });
            } catch (UnirestException | IOException e) {
                e.printStackTrace();
            }
        }).
                start();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnApplyContract.setOnMouseClicked(event -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/apply.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Apply contract");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
