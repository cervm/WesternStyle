package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerGroup;
import model.entity.Customer;
import model.exception.ModelSyncException;
import modelCollections.DBCustomers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabGroupsController implements Initializable {

    @FXML
    public TableView table_groups;
    @FXML
    public TableView table_members;
    @FXML
    public TableColumn groups_name;
    @FXML
    public TableColumn groups_discount;
    @FXML
    public TableColumn members_name;
    @FXML
    public TableColumn members_city;
    @FXML
    public TableColumn members_email;
    @FXML
    public TableColumn members_phone;
    @FXML
    public JFXButton btn_groups_create;
    @FXML
    public JFXButton btn_groups_edit;
    @FXML
    public JFXButton btn_groups_delete;
    @FXML
    public JFXButton btn_members_create;
    @FXML
    public JFXButton btn_members_edit;
    @FXML
    public JFXButton btn_members_delete;

    private DBCustomers dbCustomers;
    ObservableList<Customer> customers;
    ObservableList<CustomerGroup> groups;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbCustomers = new DBCustomers();
            customers = FXCollections.observableArrayList(dbCustomers.getAll());
            groups = FXCollections.observableArrayList(dbCustomers.getCustomerGroups());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the customer connection component.");
            a.setContentText(e.getMessage());
        }
        loadTable(groups);
    }

    private void loadTable(ObservableList<CustomerGroup> source){
        table_groups.getColumns().removeAll(groups_name, groups_discount);
        table_groups.setItems(source);
        groups_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        groups_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        table_groups.getColumns().addAll(groups_name, groups_discount);
    }
}
