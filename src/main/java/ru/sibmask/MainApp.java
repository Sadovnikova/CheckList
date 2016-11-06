package ru.sibmask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.sibmask.view.CTabController;
import ru.sibmask.view.CTabOverviewController;

import java.io.IOException;

@Slf4j
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private TabPane cTabOverview;

    @Getter
    private TreeItem<String> categoriesData;

    public MainApp(){
        categoriesData = new TreeItem<>("root");
        TreeItem<String> first = new TreeItem<>("1");
        first.getChildren().add(new TreeItem<>("1.1"));
        categoriesData.getChildren().add(first);
        categoriesData.getChildren().add(new TreeItem<>("2"));
        categoriesData.getChildren().add(new TreeItem<>("3"));
        categoriesData.getChildren().add(new TreeItem<>("4"));
        categoriesData.getChildren().add(new TreeItem<>("5"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Checklist application");
        initRootLayout();
        initCTabOverview();
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

    public void initCTabOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("view/CTabOverview.fxml"));
            cTabOverview = loader.load();
            rootLayout.setCenter(cTabOverview);
            CTabOverviewController controller = loader.getController();
            controller.setMainApp(this);
            createTab();
        } catch (IOException e) {
            log.error("Init tab panel error: ", e);
        }
    }

    private void createTab() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("view/CTab.fxml"));
            AnchorPane cTab = loader.load();
            Tab tab = new Tab();
            tab.setText("new tab");
            tab.setContent(cTab);
            cTabOverview.getTabs().add(tab);
            CTabController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            log.error("Init tab panel error: ", e);
        }
    }
}
