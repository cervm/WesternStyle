package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    public void showAssignProductDialog() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/orderAssignCustomer.fxml"));
            Stage newAssignCustomerDialog = new Stage();
            newAssignCustomerDialog.setTitle("Assign customer");
            newAssignCustomerDialog.setScene(new Scene(root));
            newAssignCustomerDialog.show();
        } catch (IOException ex) {
        } catch (Exception ex2) {
        }
    }
}
