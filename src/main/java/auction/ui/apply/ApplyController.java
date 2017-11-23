package auction.ui.apply;

import auction.ui.entity.Contract;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ApplyController {

    @FXML
    private JFXTextField txfTerm;

    @FXML
    void handleApplyButtonAction(ActionEvent event) throws UnirestException {
        Contract contract = new Contract();
        contract.setTerm(txfTerm.getText());

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/apply")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(contract)
                .asJson();

        if (jsonResponse.getStatus() == 200) {
            handleCancelButtonAction(event);
        }
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        ((Stage) txfTerm.getScene().getWindow()).close();
    }

}
