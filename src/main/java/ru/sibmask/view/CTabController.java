package ru.sibmask.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.Getter;
import lombok.Setter;
import ru.sibmask.MainApp;
import ru.sibmask.model.Category;

import java.util.List;

// For actual checklist
public class CTabController {
    @Getter
    private MainApp mainApp;

    @FXML
    private TreeView<Category> categoriesTree;

    @FXML
    private ListView<String> checkList;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        categoriesTree.setShowRoot(false);
        categoriesTree.setRoot(mainApp.getCategoriesData());
    }
}
