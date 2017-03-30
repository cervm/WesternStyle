package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import model.exception.ModelSyncException;
import modelCollections.DBProducts;
import org.controlsfx.control.CheckTreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabProductsController implements Initializable {
    @FXML
    public CheckTreeView categories_checkTreeView;
    @FXML
    public TableView products_table;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn desCol;
    @FXML
    public TableColumn countryCol;
    @FXML
    public TableColumn costCol;
    @FXML
    public TableColumn saleCol;
    @FXML
    public TableColumn rentCol;
    @FXML
    public TableColumn minCol;
    @FXML
    public TableColumn suppCol;

    @FXML
    public TableView variants_table;
    @FXML
    public JFXButton btn_products_create;
    @FXML
    public JFXButton btn_products_edit;
    @FXML
    public JFXButton btn_products_delete;
    @FXML
    public JFXButton btn_variants_add;
    @FXML
    public JFXButton btn_variants_edit;
    @FXML
    public JFXButton btn_variants_delete;

    DBProducts dbProducts;
    ObservableList<Product> products;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dbProducts = new DBProducts();
            products = FXCollections.observableArrayList(dbProducts.getAll());
        } catch (ModelSyncException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error occured while initializing the products connection component.");
            a.setContentText(e.getMessage());
        }
        loadTable(products);

    }

    private void loadTable(ObservableList<Product> source){
        products_table.getColumns().removeAll(nameCol, desCol, countryCol, costCol, saleCol, rentCol, minCol, suppCol);
        products_table.setItems(source);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryOrigin"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        saleCol.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
        rentCol.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        minCol.setCellValueFactory(new PropertyValueFactory<>("minStock"));
        suppCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        products_table.getColumns().addAll(nameCol, desCol, countryCol, costCol,saleCol, rentCol, minCol, suppCol);
    }

    @FXML
    public void showNewProductDialog() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/addProduct.fxml"));
            Stage newProductDialog = new Stage();
            newProductDialog.setTitle("New product");
            newProductDialog.setScene(new Scene(root));
            newProductDialog.show();
        } catch (IOException ex) {
        } catch (Exception ex2) {
        }
    }
}
