package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        new Thread((DbSessionHolder::getInstance)).start();
        DbSessionHolder.getInstance();

        launch(args);




        //TODO DB operations
        DbSessionHolder.shutdown();

    }
}
