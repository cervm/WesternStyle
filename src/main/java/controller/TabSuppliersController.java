package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
