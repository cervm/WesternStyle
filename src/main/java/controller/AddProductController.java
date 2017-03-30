package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.CheckTreeView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by GRIDS I mean jakub on 30/03/2017.
 * FIXED BY ONDREJ
 * GRIDS UPON GRIDS UPON GRIDS
 * YOU LIKE GRIDS?
 * WE HAVE GRIDS.
 * GRIDS!
 */
public class AddProductController implements Initializable {
    @FXML
    public JFXComboBox cbox_products_suppliers;
    @FXML
    public CheckTreeView treeview_products_category;
    @FXML
    public JFXTextField tf_products_name;
    @FXML
    public JFXTextField tf_products_description;
    @FXML
    public JFXTextField tf_products_purchasePrice;
    @FXML
    public JFXTextField tf_products_salesPrice;
    @FXML
    public JFXTextField tf_products_rentPrice;
    @FXML
    public JFXTextField tf_products_country;
    @FXML
    public JFXTextField tf_products_minStock;
    @FXML
    public JFXButton btn_products_confirm;
    @FXML
    public JFXButton btn_products_cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
