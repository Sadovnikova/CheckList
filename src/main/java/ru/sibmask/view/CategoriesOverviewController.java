package ru.sibmask.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ru.sibmask.Main;
import ru.sibmask.model.Category;
import ru.sibmask.model.Check;

public class CategoriesOverviewController {
    @FXML
    private ListView<Category> categoryList;

    @FXML
    private ListView<Check> checkList;

    private Main mainApp;

    @FXML
    private void initialize(){
        categoryList.setCellFactory(p -> new ListCell<Category>(){
            @Override
            protected void updateItem(Category t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getTitle().getValue());
                }
            }
        });
    }

    @FXML
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
        categoryList.setItems(mainApp.getCategoriesData());
    }
}
