package ru.sibmask.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.Getter;
import ru.sibmask.MainApp;
import ru.sibmask.model.Category;

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
        showCategory(null);
        categoriesTree.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCategory(newValue));
    }

    private void showCategory(TreeItem<Category> categoryNode) {
        if (categoryNode != null) {
            Category category = categoryNode.getValue();
            if (category != null && category.getCheckList() != null) {
                checkList.setItems(category.getCheckList());
            }
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        categoriesTree.setShowRoot(false);
        categoriesTree.setRoot(mainApp.getCategoriesData());
    }
}
