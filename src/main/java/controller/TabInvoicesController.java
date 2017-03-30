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
import model.Invoice;
import model.exception.ModelSyncException;
import modelCollections.DBInvoices;

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

    private DBInvoices dbInvoices;
    private ObservableList<Invoice> invoices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbInvoices = new DBInvoices();
            invoices = FXCollections.observableArrayList(dbInvoices.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the invoice connection component.");
            a.setContentText(e.getMessage());
        }
        loadTable(invoices);
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
}
