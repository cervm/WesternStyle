package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Add columns to both tables in .fxml according to data you are going to display (do it)
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
