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

        table_customers.setItems(customers);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

}
