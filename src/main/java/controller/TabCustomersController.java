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
import model.entity.Customer;
import model.exception.ModelSyncException;
import modelCollections.DBCustomers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabCustomersController implements Initializable{

    @FXML
    public TableView table_customers;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn emailCol;
    @FXML
    public JFXTextField tf_customers_search;
    @FXML
    public JFXButton btn_customers_search;
    @FXML
    public JFXButton btn_customers_add;
    @FXML
    public JFXButton btn_customers_edit;
    @FXML
    public JFXButton btn_customers_delete;
    @FXML
    public JFXButton btn_customers_changeCat;
    @FXML
    public JFXButton btn_customers_assignCat;
    @FXML
    public TableColumn phoneCol;
    @FXML
    public TableColumn addressCol;
    @FXML
    public TableColumn postCol;
    @FXML
    public TableColumn cityCol;
    @FXML
    public TableColumn countryCol;

    private DBCustomers dbCustomers;
    ObservableList<Customer> customers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbCustomers = new DBCustomers();
            customers = FXCollections.observableArrayList(dbCustomers.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the customer connection component.");
            a.setContentText(e.getMessage());
        }

        loadTable(customers);
    }

    private void loadTable(ObservableList<Customer> source) {
        table_customers.getColumns().removeAll(nameCol, emailCol, phoneCol, addressCol, postCol, cityCol, countryCol);
        table_customers.setItems(source);
        nameCol.setMinWidth(50);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setMinWidth(60);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setMaxWidth(50);
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressCol.setMinWidth(80);
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postCol.setMinWidth(30);
        postCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        cityCol.setMinWidth(50);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setMinWidth(20);
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        table_customers.getColumns().addAll(nameCol, emailCol, phoneCol, addressCol, postCol, cityCol, countryCol);
    }
}
