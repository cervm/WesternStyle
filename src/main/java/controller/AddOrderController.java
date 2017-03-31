package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 31.03.2017.
 */
public class AddOrderController implements Initializable {
    @FXML
    public TableView table_order_product;
    @FXML
    public TableView table_order_variant;
    @FXML
    public ListView listview_basket;
    @FXML
    public JFXButton btn_order_addCustomer;
    @FXML
    public JFXButton btn_order_addToBasket;
    @FXML
    public JFXButton btn_order_confirm;
    @FXML
    public Label lb_order_summaryPrice;
    @FXML
    public Label lb_order_summaryDate;
    @FXML
    public Label lb_order_summaryCustomerName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
