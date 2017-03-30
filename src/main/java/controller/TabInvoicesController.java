package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
