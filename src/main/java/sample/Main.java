package sample;

import configuration.DbSessionHolder;

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
        //TODO DB operations
        DbSessionHolder.shutdown();

        System.out.println("MAIN");
    }
}
