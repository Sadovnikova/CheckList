package ru.sibmask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import ru.sibmask.view.CTabOverviewController;

import java.io.IOException;

@Slf4j
public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Checklist application");
        initRootLayout();
        initCTab();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("MainApp.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            log.error("Init root layout error: ", e);
        }
    }

    public void initCTab(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("view/CTabOverview.fxml"));
            TabPane cTab = loader.load();
            rootLayout.setCenter(cTab);
            CTabOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            log.error("Init tab panel error: ", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
