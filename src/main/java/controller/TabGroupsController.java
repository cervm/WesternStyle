package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabGroupsController implements Initializable {

    @FXML
    public JFXTreeTableView table_groups;
    @FXML
    public JFXTreeTableView table_members;
    @FXML
    public JFXTreeTableColumn groups_name;
    @FXML
    public JFXTreeTableColumn groups_discount;
    @FXML
    public JFXTreeTableColumn members_name;
    @FXML
    public JFXTreeTableColumn members_city;
    @FXML
    public JFXTreeTableColumn members_email;
    @FXML
    public JFXTreeTableColumn members_phone;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
