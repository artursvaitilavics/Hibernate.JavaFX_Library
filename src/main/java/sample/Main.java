package sample;

import configuration.DbSessionHolder;
import dao.AuthorDao;
import entity.Author;

public class Main { //extends Application {

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        DbSessionHolder.getInstance();
        //   launch(args);
        Author author00001 = new Author();
        author00001.setName("Inddrikis");
        author00001.setSurName("Neredzigais");
        AuthorDao authorDao = new AuthorDao();
        authorDao.createAuthor(author00001);

        //TODO DB operations
        DbSessionHolder.shutdown();

        System.out.println("MAIN");
    }
}
