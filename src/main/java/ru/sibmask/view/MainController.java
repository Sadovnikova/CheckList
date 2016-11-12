package ru.sibmask.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import lombok.Getter;
import lombok.Setter;
import ru.sibmask.MainApp;

import java.util.Optional;

public class MainController {
    @Getter
    @Setter
    MainApp mainApp;

    @FXML
    private MenuItem fileClose;

    @FXML
    private MenuItem fileNew;

    @FXML
    private void initialize() {
        fileClose.setOnAction(event -> Platform.exit());
        fileNew.setOnAction(event -> {
            String newName = questionNameDialog();
            if (newName != null && !newName.isEmpty())
                mainApp.createTab(newName);
        });
    }

    private String questionNameDialog() {
        TextInputDialog dialog = new TextInputDialog("new");
        dialog.setTitle("Create new tab");
//        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter tab name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return "";
    }
}
