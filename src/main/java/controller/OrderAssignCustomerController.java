package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 31.03.2017.
 */
public class OrderAssignCustomerController implements Initializable {
    @FXML
    public JFXTextField tf_assignCustomer_search;
    @FXML
    public TableView table_assignCustomer;
    @FXML
    public JFXButton btn_assignCustomer_search;
    @FXML
    public JFXButton btn_assignCustomer_confirm;
    @FXML
    public JFXButton btn_assignCustomer_cancel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void closeDialog(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_assignCustomer_cancel.getScene().getWindow();
        stage.close();
    }
}
