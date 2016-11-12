package ru.sibmask.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.sibmask.MainApp;
import ru.sibmask.model.Category;

import java.util.Optional;

// For actual checklist
@Slf4j
public class CTabController {
    @Getter
    private MainApp mainApp;

    @Setter
    private String tabName;

    @FXML
    private TreeView<Category> categoriesTree;

    @FXML
    private ListView<String> checkList;

    @FXML
    private MenuItem contextTreeMenuAddCategory;

    @FXML
    private MenuItem contextListMenuAddCheck;

    private Category selectedCategory;

    @FXML
    private void initialize() {
        showCategory(null);
        categoriesTree.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCategory(newValue));
        contextListMenuAddCheck.setOnAction(this::addCheckListItem);
        contextTreeMenuAddCategory.setOnAction(this::addCategoryItem);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        categoriesTree.setShowRoot(false);
        categoriesTree.setRoot(mainApp.getData(tabName));
    }

    private void showCategory(TreeItem<Category> categoryNode) {
        if (categoryNode != null) {
            selectedCategory = categoryNode.getValue();
            if (selectedCategory != null && selectedCategory.getCheckList() != null) {
                checkList.setItems(selectedCategory.getCheckList());
            }
        }
    }

    private void addCategoryItem(ActionEvent event) {
        log.info("add to tree");
        String categoryName = addCategoryDialog();
        if (categoryName != null && !categoryName.isEmpty()) {
            TreeItem<Category> newCategory = new TreeItem<>(new Category(categoryName));
            TreeItem<Category> categories = mainApp.getData(tabName);
            categories.getChildren().add(newCategory);
        }
    }

    private String addCategoryDialog() {
        TextInputDialog dialog = new TextInputDialog("categoryName");
        dialog.setTitle("Create new category");
//        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter category name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return "";
    }

    private void addCheckListItem(ActionEvent event) {
        log.info("add to list");
        String checkName = addCheckListDialog();
        if (checkName != null && !checkName.isEmpty()) {
            // FIXME: does not work with no created lists
            selectedCategory.getCheckList().add(checkName);
        }
    }

    private String addCheckListDialog() {
        TextInputDialog dialog = new TextInputDialog("checkName");
        dialog.setTitle("Create new check");
//        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter check name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return "";
    }
}
