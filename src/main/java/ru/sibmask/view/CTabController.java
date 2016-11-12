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

    private TreeItem<Category> selectedCategory;

    @FXML
    private void initialize() {
        showCategoryCheckList(null);
        categoriesTree.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCategoryCheckList(newValue));
        contextListMenuAddCheck.setOnAction(this::addCheckListItem);
        contextTreeMenuAddCategory.setOnAction(this::addCategoryItem);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        categoriesTree.setShowRoot(false);
        categoriesTree.setRoot(mainApp.getData(tabName));
    }

    private void showCategoryCheckList(TreeItem<Category> categoryNode) {
        if (categoryNode != null) {
            selectedCategory = categoryNode;
            if (selectedCategory.getValue() != null && selectedCategory.getValue().getCheckList() != null) {
                checkList.setItems(selectedCategory.getValue().getCheckList());
            }
        }
    }

    private void addCategoryItem(ActionEvent event) {
        log.info("add to tree");
        String categoryName = addCategoryDialog();
        if (categoryName != null && !categoryName.isEmpty()) {
            Category newCategory = new Category(categoryName);
            TreeItem<Category> root = mainApp.getData(tabName);
            root.getChildren().add(new TreeItem<>(newCategory));
            root.getValue().add(newCategory);
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
            log.debug("Add to category '" + selectedCategory.getValue() + "' check '" + checkName + "'");
            selectedCategory.getValue().getCheckList().add(checkName);
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
