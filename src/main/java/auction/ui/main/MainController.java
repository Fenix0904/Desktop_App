package auction.ui.main;

import auction.ui.entity.Contract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.neovisionaries.ws.client.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable {
    @FXML
    private JFXButton btnApplyContract;

    @FXML
    private JFXButton logOut;

    @FXML
    private JFXListView<String> userJFXListView;

    @FXML
    private JFXButton btnGetContracts;

    @FXML
    void load(ActionEvent event) throws UnirestException, IOException {
        userJFXListView.getItems().clear();
        userJFXListView.setExpanded(true);
        userJFXListView.depthProperty().setValue(1);

        new Thread(() -> {

            HttpResponse<JsonNode> jsonResponse = null;
            try {
                jsonResponse = Unirest.get("http://localhost:8080/get-all-contracts")
                        .asJson();

                List<Contract> contracts = new ObjectMapper().readValue(jsonResponse.getBody().toString(), new TypeReference<List<Contract>>() {
                });
                Platform.runLater(() -> {
                    for (Contract contract : contracts) {
                        userJFXListView.getItems().add("User " + contract.getUser().getUsername() + " Contract term - " + contract.getTerm());
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
        /*
        btnApplyContract.setOnMouseClicked(event -> {

            try {
                final WebSocket websocket = new WebSocketFactory()
                        .createSocket("ws://localhost:8080/makeBid")
                        .addListener(new WebSocketAdapter() {
                            @Override
                            public void onTextMessage(WebSocket ws, String message) {
                                System.out.println("Received msg: " + message);
                            }

                            @Override
                            public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                                super.onBinaryMessage(websocket, binary);
                            }
                        });
                websocket.connect();
                websocket.sendText("{\"id\":\"1\",\"bidValue\":\"200\",\"user\":{\"username\":\"Fenix0904\"},\"lot\":{\"id\":\"1\"}}");
                Thread.sleep(5000);
                websocket.disconnect();
            } catch (IOException | WebSocketException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

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

        logOut.setOnMouseClicked(event -> {
            HttpResponse<JsonNode> jsonResponse;
            try {
                jsonResponse = Unirest.post("http://localhost:8080/out")
                        .asJson();
                if (jsonResponse.getStatus() == 200) {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Login");
                    stage.setScene(new Scene(parent));
                    stage.show();
                    ((Stage) logOut.getScene().getWindow()).close();
                }
            } catch (UnirestException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
