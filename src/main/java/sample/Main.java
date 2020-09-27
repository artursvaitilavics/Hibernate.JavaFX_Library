package sample;

import Repository.AuthorRepository;
import Repository.CrudRepository;
import configuration.DbSessionHolder;
import entity.Author;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        DbSessionHolder.getInstance();

        launch(args);

        Author author0001 = new Author("Visvaldis", "Galkasto");
        Author author0002 = new Author("Burudukio", "Kalnieties");
        Author author0003 = new Author("Teftelu", "Dzejotajs");
        Author author0004 = new Author("Astra", "Aspazija");

        AuthorRepository authorRepository = new AuthorRepository();

        authorRepository.save(author0001);
        authorRepository.save(author0002);
        authorRepository.save(author0003);
        authorRepository.save(author0004);

        //TODO DB operations
        DbSessionHolder.shutdown();

        System.out.println("MAIN");
    }
}
