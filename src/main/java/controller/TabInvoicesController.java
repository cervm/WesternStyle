package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Invoice;
import model.Order;
import model.exception.ModelSyncException;
import modelCollections.DBInvoices;
import modelCollections.DBOrders;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabInvoicesController implements Initializable {

    @FXML
    public TableView table_invoices;
    @FXML
    public TableView table_invoiceItems;
    @FXML
    public JFXTextField tf_invoices_search;
    @FXML
    public JFXButton btn_invoices_search;
    @FXML
    public JFXButton btn_invoices_create;
    @FXML
    public JFXButton btn_invoices_edit;
    @FXML
    public TableColumn idCol;
    @FXML
    public TableColumn paymentCol;
    @FXML
    public TableColumn amountCol;
    @FXML
    public TableColumn ordersCol;
    @FXML
    public TableColumn oidCol;
    @FXML
    public TableColumn orderDateCol;
    @FXML
    public TableColumn deliveryDateCol;
    @FXML
    public TableColumn oamountCol;
    @FXML
    public TableColumn statusCol;
    @FXML
    public TableColumn customerCol;

    private DBInvoices dbInvoices;
    private DBOrders dbOrders;
    private ObservableList<Invoice> invoices;
    private Invoice selectedInvoice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbInvoices = new DBInvoices();
            dbOrders = new DBOrders();
            invoices = FXCollections.observableArrayList(dbInvoices.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the invoice connection component.");
            a.setContentText(e.getMessage());
            a.show();
        }
        loadTable(invoices);
        selectedInvoice = (Invoice) table_invoices.getSelectionModel().getSelectedItem();
    }

    private void loadTable(ObservableList<Invoice> source) {
        table_invoices.getColumns().removeAll(idCol, paymentCol, amountCol, ordersCol);
        table_invoices.setItems(source);
        idCol.setMinWidth(20);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        paymentCol.setMinWidth(50);
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        amountCol.setMinWidth(30);
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ordersCol.setMinWidth(30);
        ordersCol.setCellValueFactory(new PropertyValueFactory<>("numOfOrders"));
        table_invoices.getColumns().addAll(idCol, paymentCol, amountCol, ordersCol);
    }

    public void tableInvoicesOnMouseClicked(MouseEvent mouseEvent) {
        selectedInvoice = (Invoice) table_invoices.getSelectionModel().getSelectedItem();
        if (selectedInvoice != null) {
            loadOrderTable();
        }
    }

    private void loadOrderTable() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for (int orderID : selectedInvoice.getOrders()) {
            try {
                orders.add(dbOrders.getById(orderID));
            } catch (ModelSyncException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Error occured while initializing the orders table.");
                a.setContentText(e.getMessage());
                a.show();
            }
        }

        table_invoiceItems.setItems(orders);
        table_invoiceItems.getColumns().removeAll(oidCol, orderDateCol, deliveryDateCol, oamountCol, statusCol, customerCol);
        oidCol.setMinWidth(20);
        oidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderDateCol.setMinWidth(100);
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        deliveryDateCol.setMinWidth(100);
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        oamountCol.setMinWidth(40);
        oamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusCol.setMinWidth(40);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        customerCol.setMinWidth(30);
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        table_invoiceItems.getColumns().addAll(oidCol, orderDateCol, deliveryDateCol, oamountCol, statusCol, customerCol);
    }
}
