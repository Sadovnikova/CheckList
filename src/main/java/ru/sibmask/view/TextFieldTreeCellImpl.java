package ru.sibmask.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import ru.sibmask.model.Category;

public class TextFieldTreeCellImpl extends TreeCell<Category> {

    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();

    public TextFieldTreeCellImpl() {
        MenuItem addMenuItem = new MenuItem("Add SubCategory ...");
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction(t -> {
            Category newCategory = new Category("New SubCategory");
            TreeItem<Category> newTreeCategory = new TreeItem<>(newCategory);
            getTreeItem().getChildren().add(newTreeCategory);
            ((Category) getItem()).add(newCategory);
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem().getName().get());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(Category item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setContextMenu(addMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ENTER) {
                ((Category) getItem()).setName(new SimpleStringProperty(textField.getText()));
                commitEdit(getItem());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

    }

    private String getString() {
        return getItem() == null ? "" : ((Category) getItem()).getName().get();
    }
}