package ru.sibmask.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.List;

public class Category {
    private StringProperty name;
    private ObjectProperty<List<Category>> subCategory;
    private ObjectProperty<List<String>> checkList;

    public Category(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public String toString() {
        return getName();
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public List<Category> getSubCategory() {
        return subCategory.get();
    }

    public ObjectProperty<List<Category>> subCategoryProperty() {
        return subCategory;
    }

    public void setSubCategory(List<Category> subCategory) {
        this.subCategory.set(subCategory);
    }

    public List<String> getCheckList() {
        return checkList.get();
    }

    public ObjectProperty<List<String>> checkListProperty() {
        return checkList;
    }

    public void setCheckList(List<String> checkList) {
        this.checkList.set(checkList);
    }
}
