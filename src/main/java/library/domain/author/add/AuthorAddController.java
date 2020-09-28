package library.domain.author.add;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.domain.author.Author;
import library.domain.author.AuthorRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorAddController implements Initializable {

    private final AuthorRepository authorRepository = new AuthorRepository();

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surName;

    @FXML
    private StackPane rootPane;
    private Integer authorId;

    private Author editable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setEditable(Author author) {
        this.editable = author;
        this.name.setText(author.getName());
        this.surName.setText(author.getSurName());
    }

    @FXML
    private void addAuthor(ActionEvent event) {
        String authorName = name.getText();
        String authorSurName = surName.getText();
        System.out.println(authorName + " " + authorSurName);


        if (authorName.isEmpty() || authorSurName.isEmpty()) {
//            //TODO show user alert that all fields have to be filled
            System.out.println("Author fields are empty");
            return;
        }


        if (editable == null) {
            authorRepository.save(new Author(authorName, authorSurName));
        } else {
            Author author1 = authorRepository.findOne(editable.getId());
            author1.setName(authorName);
            author1.setSurName(authorSurName);
            authorRepository.update(author1);
        }
        clearEntries();
        closeStage();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        name.clear();
        surName.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
