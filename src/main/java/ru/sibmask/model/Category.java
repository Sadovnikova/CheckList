package ru.sibmask.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class Category {
    @Getter @Setter
    private StringProperty name;
    @Getter
    private ListProperty<Category> subCategory;
    @Getter
    private ListProperty<String> checkList;

    public Category(String name) {
        this.name = new SimpleStringProperty(name);
        this.subCategory = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.checkList = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    @Override
    public String toString() {
        return this.getName().get();
    }

    public void add(Category subCategory) {
        this.subCategory.add(subCategory);
    }

    public void add(String check) {
        this.checkList.add(check);
    }
}
