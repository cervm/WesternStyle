package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.Order;
import model.exception.ModelSyncException;
import modelCollections.DBOrders;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabOrdersController implements Initializable {
    @FXML
    public TableView table_orders;
    @FXML
    public TableView table_orderDetails;
    @FXML
    public JFXTextField tf_orders_search;
    @FXML
    public JFXButton btn_orders_search;
    @FXML
    public JFXButton btn_orders_create;
    @FXML
    public JFXButton btn_orders_edit;
    @FXML
    public JFXButton btn_orders_delete;

    DBOrders dbOrders;
    ObservableList<Order> orders;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            dbOrders = new DBOrders();
            orders = FXCollections.observableArrayList(dbOrders.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the orders connection component.");
            a.setContentText(e.getMessage());
        }
        table_orders.setItems(orders);
    }
}
