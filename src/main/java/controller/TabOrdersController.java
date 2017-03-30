package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableColumn iodCol;
    @FXML
    public TableColumn dateCol;
    @FXML
    public TableColumn delDateCol;
    @FXML
    public TableColumn amntCol;
    @FXML
    public TableColumn statCol;
    @FXML
    public TableColumn custIdCol;

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
        loadTable(orders);
    }

    private void loadTable(ObservableList<Order> source){
        table_orders.getColumns().removeAll(iodCol, dateCol, delDateCol, amntCol, statCol, custIdCol);
        table_orders.setItems(source);
        iodCol.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        delDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        amntCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        table_orders.getColumns().addAll(iodCol, dateCol, delDateCol, amntCol, statCol, custIdCol);
    }
}
