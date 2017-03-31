package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckTreeView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ondřej Soukup on 30.03.2017.
 */
public class TabCategoriesController implements Initializable{
    @FXML
    public TableView table_categories;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn desCol;
    @FXML
    public TableView table_properties;
    @FXML
    public JFXButton btn_categories_create;
    @FXML
    public JFXButton btn_categories_edit;
    @FXML
    public JFXButton btn_categories_delete;
    @FXML
    public JFXButton btn_properties_create;
    @FXML
    public JFXButton btn_properties_edit;
    @FXML
    public JFXButton btn_properties_delete;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO find some bitch ass nigga way to fetch dem categories bitches from deebee
    }
}
