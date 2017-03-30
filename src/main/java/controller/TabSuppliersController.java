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
import model.entity.Supplier;
import model.exception.ModelSyncException;
import modelCollections.DBSuppliers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabSuppliersController implements Initializable {
    @FXML
    public JFXTextField tf_suppliers_search;
    @FXML
    public TableView table_suppliers;
    @FXML
    public JFXButton btn_suppliers_search;
    @FXML
    public JFXButton btn_suppliers_add;
    @FXML
    public JFXButton btn_suppliers_edit;
    @FXML
    public JFXButton btn_suppliers_delete;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn emailCol;
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
    @FXML
    public TableColumn regCol;

    private DBSuppliers dbSuppliers;
    private ObservableList<Supplier> suppliers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbSuppliers = new DBSuppliers();
            suppliers = FXCollections.observableArrayList(dbSuppliers.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the supplier connection component.");
            a.setContentText(e.getMessage());
        }
        loadTable(suppliers);
    }

    private void loadTable(ObservableList<Supplier> source) {
        table_suppliers.getColumns().removeAll(nameCol, emailCol, phoneCol, addressCol, postCol, cityCol, countryCol, regCol);
        table_suppliers.setItems(source);
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
        regCol.setMinWidth(50);
        regCol.setCellValueFactory(new PropertyValueFactory<>("companyRegNo"));
        table_suppliers.getColumns().addAll(nameCol, emailCol, phoneCol, addressCol, postCol, cityCol, countryCol, regCol);
    }
}
