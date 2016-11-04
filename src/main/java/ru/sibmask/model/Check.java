package ru.sibmask.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NonNull;

public class Check {
    @NonNull @Getter private StringProperty text;

    public Check(){
        this(new SimpleStringProperty("simple check content"));
    }

    public Check(StringProperty text) {
        this.text = text;
    }
}
