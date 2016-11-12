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
import lombok.extern.slf4j.Slf4j;
import ru.sibmask.model.Category;
import ru.sibmask.view.CTabController;
import ru.sibmask.view.CTabOverviewController;
import ru.sibmask.view.MainController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private TabPane cTabOverview;
    private Map<String, TreeItem<Category>> tabsData;
    private String SIMPLE_EXAMPLE_TAB = "simple example tab";

    public MainApp(){
        tabsData = new HashMap<>();
        exampleFilling();
    }

    private void exampleFilling() {
        Category root = new Category("root");
        Category sub1 = new Category("1");
        Category sub11 = new Category("11");
        Category sub12 = new Category("12");
        Category sub2 = new Category("2");
        Category sub3 = new Category("3");
        root.add(sub1);
        sub1.add(sub11);
        sub1.add(sub12);
        sub1.add("1.1");
        sub1.add("1.2");
        root.add(sub2);
        sub2.add("2.1");
        root.add(sub3);

        tabsData.put(SIMPLE_EXAMPLE_TAB, fillDataToTree(root));
    }

    private TreeItem<Category> fillDataToTree(Category root) {
        TreeItem<Category> result = new TreeItem<>(root);
        for (Category category : root.getSubCategory()) {
            TreeItem<Category> least = new TreeItem<>(category);
            for (Category subCategory : category.getSubCategory()) {
                least.getChildren().add(new TreeItem<>(subCategory));
            }
            result.getChildren().add(least);
        }
        return result;
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
            loader.setLocation(MainApp.class.getClassLoader().getResource("view/Main.fxml"));
            rootLayout = loader.load();
            MainController mainController = loader.getController();
            mainController.setMainApp(this);
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
            createTab(SIMPLE_EXAMPLE_TAB);
        } catch (IOException e) {
            log.error("Init tab panel error: ", e);
        }
    }

    public void createTab(String name) {
        try {
            log.debug("Create new tab: " + name);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("view/CTab.fxml"));
            AnchorPane cTab = loader.load();
            Tab tab = new Tab();
            tab.setText(name);
            tab.setContent(cTab);
            cTabOverview.getTabs().add(tab);
            CTabController controller = loader.getController();
            controller.setTabName(name); //FIXME: set before set mainApp, else error
            controller.setMainApp(this);
        } catch (IOException e) {
            log.error("Init tab panel error: ", e);
        }
    }

    public TreeItem<Category> getData(String tabName) {
        log.debug("Get data for tab: " + tabName);
        TreeItem<Category> category = tabsData.get(tabName);
        if (category == null){
            // FIXME: tabName may be equals -> it is open other data, need unique name
            log.debug("create new data storage for tab: " + tabName);
            category = new TreeItem<>(new Category(tabName));
            tabsData.put(tabName, category);
        }
        return category;
    }
}
