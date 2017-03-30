package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabInvoicesController implements Initializable {

    @FXML
    public JFXButton btn_test;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btn_test_onClick(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Cogratulations!");
        a.setContentText("You are a faggot!");
        a.show();
    }
}
