package ru.sibmask.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NonNull;

public class Category {
    @Getter @NonNull
    private ListProperty<Category> subCategories;

    @Getter @NonNull
    private ListProperty<Check> checkList;

    @Getter @NonNull
    private StringProperty title;

    public Category(StringProperty title){
        this(title, new SimpleListProperty(), new SimpleListProperty());
    }

    public Category(StringProperty title, ListProperty subCategories, ListProperty checkList) {
        this.title = title;
        this.subCategories = subCategories;
        this.checkList = checkList;
    }
}
