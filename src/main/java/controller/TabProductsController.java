package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckTreeView;

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
}
