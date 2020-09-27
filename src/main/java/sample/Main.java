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


        Author author0002 = new Author();
        author0002.setName("Rainis");
        author0002.setSurName("Raininsons");

        AuthorRepository authorRepository = new AuthorRepository();
        authorRepository.save(author0002);

        //TODO DB operations
        DbSessionHolder.shutdown();

        System.out.println("MAIN");
    }
}
