package ru.sibmask;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.sibmask.model.Category;
import ru.sibmask.view.CategoriesOverviewController;

import java.io.IOException;

@Slf4j
public class Main extends Application {

    @Getter private Stage primaryStage;
    private BorderPane rootLayout;
    @Getter private ObservableList<Category> categoriesData = FXCollections.observableArrayList();

    public Main(){
        categoriesData.add(new Category(new SimpleStringProperty("Category 1")));
        categoriesData.add(new Category(new SimpleStringProperty("Category 2")));
        categoriesData.add(new Category(new SimpleStringProperty("Category 3")));
        categoriesData.add(new Category(new SimpleStringProperty("Category 4")));
        categoriesData.add(new Category(new SimpleStringProperty("Category 5")));
    }

    @Override
    public void start(Stage primaryStage) {
        log.trace("start");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CheckListApp");
        initRootLayout();
        log.trace("end");
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("rootView.fxml"));
            rootLayout = loader.load();
            CategoriesOverviewController controller = loader.getController();
            controller.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            log.error("Init error", e);
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
